import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        String input = st.nextToken();
        int inputLen = input.length();
        st = new StringTokenizer(br.readLine());
        String bomb = st.nextToken();
        int bombLen = bomb.length();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < inputLen; i++) {
            int count = 0;
            stack.push(input.charAt(i));

            if (stack.size() >= bombLen) {

                for (int j = 0; j < bombLen; j++) {
                    if (Objects.equals(stack.get(stack.size() - bombLen + j), bomb.charAt(j))) {
                        count++;
                    }
                }

                if (count == bombLen) {
                    while (count-- > 0) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        if (stack.isEmpty()) {
            sb.append("FRULA");
        } else {
            for (Character c : stack) {
                sb.append(c);
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}