import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String input = st.nextToken();

        String answer = String.valueOf(getAnswer(input));
        bw.flush();
        bw.write(answer);
        bw.close();

    }

    private static int getAnswer(String input) {
        int answer = 0;
        int tmp = 1;
        Stack<Character> stack = new Stack<>();
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char cur = charArray[i];
            if (cur == '(') {
                tmp *= 2;
                stack.push(cur);
                continue;
            }
            if (cur == '[') {
                tmp *= 3;
                stack.push(cur);
                continue;
            }

            if (!stack.isEmpty() && stack.peek() == '(' && cur == ')') {
                if (charArray[i - 1] == '(') {
                    answer += tmp;
                }
                tmp /= 2;
                stack.pop();
                continue;
            }

            if (!stack.isEmpty() && stack.peek() == '[' && cur == ']') {
                if (charArray[i - 1] == '[') {
                    answer += tmp;
                }
                tmp /= 3;
                stack.pop();
                continue;
            }
            stack.push(cur);
            break;
        }
        if (!stack.isEmpty()) {
            answer = 0;
        }
        return answer;
    }
}

/*
1. 요구 사항
- () 괄호열의 값은 2
- [] 괄호열의 값은 3
- (X) 의 괄호값은 2 x 값(X)
- [X] 의 괄호값은 3 x 값(X)
- 올바르지 못한 괄호열이면 0을 출력

2. 자료구조
- 스택

 */