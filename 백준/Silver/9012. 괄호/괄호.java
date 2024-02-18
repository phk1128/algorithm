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
        int t = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            String[] inputSplit = st.nextToken().split("");
            Stack<String> stack = new Stack<>();

            for (String s : inputSplit) {
                if (stack.isEmpty()) {
                    stack.push(s);
                    continue;
                }

                if (Objects.equals(s, ")") && Objects.equals(stack.peek(), "(")) {
                    stack.pop();
                    continue;
                }

                stack.push(s);
            }
            String answer;
            if (stack.isEmpty()) {
                answer = "YES";
            } else {
                answer = "NO";
            }
            sb.append(answer);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

}