import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static String[] S;
    private static int answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        S = st.nextToken().split("");
        answer = 0;
        Stack<String> stack = new Stack<>();

        for (String s : S) {
            if (stack.isEmpty()) {
                if (Objects.equals(s, "I")) {
                    stack.push("I");
                }
                continue;
            }

            if (Objects.equals(stack.peek(), "I") && Objects.equals(s, "O")) {
                stack.push("O");
                if (stack.size() == (n * 2) + 1) {
                    answer++;
                    stack.pop();
                    stack.pop();
                }
                continue;
            }

            if (Objects.equals(stack.peek(), "O") && Objects.equals(s, "I")) {
                stack.push("I");
                if (stack.size() == (n * 2) + 1) {
                    answer++;
                    stack.pop();
                    stack.pop();
                }
                continue;
            }
            stack.clear();

            if (Objects.equals(s, "I")) {
                stack.push("I");
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }
}