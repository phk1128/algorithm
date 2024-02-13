import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static String[] S;
    private static int[] dp;
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
        dp = new int[m];
        answer = 0;
        solve(n, m);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void solve(int n, int m) {
        for (int i = 1; i < m - 1; i++) {
            if (Objects.equals(S[i], "O") && Objects.equals(S[i+1], "I")) {
                dp[i + 1] = dp[i - 1] + 1;

                if (dp[i + 1] >= n && Objects.equals(S[i - (n * 2) + 1], "I"))
                    answer++;
            }
        }
    }
}