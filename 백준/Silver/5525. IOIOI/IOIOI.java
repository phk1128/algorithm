import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static String[] S;
    private static List<Stack<String>> stacks;
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
        stacks = new ArrayList<>();
        answer = 0;
        solve(n, m);

        List<List<Integer>> test = new ArrayList<>();

        test.add(new ArrayList<>());
        test.add(new ArrayList<>());

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void solve(int n, int m) {
        for (int i = 0; i < m - 1; i++) {
            if (Objects.equals(S[i], "I")) {
                Stack<String> newStack = new Stack<>();
                newStack.push("I");
                stacks.add(newStack);
            }
            for (Stack<String> stack : stacks) {
                if (stack.isEmpty()) {
                    continue;
                }
                String tmpStr = stack.peek();
                if (Objects.equals(tmpStr, "I") && Objects.equals(S[i + 1], "O")) {
                    stack.push("O");
                } else if (Objects.equals(tmpStr, "O") && Objects.equals(S[i + 1], "I")) {
                    stack.push("I");
                } else {
                    stack.clear();
                }
                if (stack.size() == (n * 2) + 1) {
                    answer++;
                    stack.clear();
                }
            }
        }
    }
}