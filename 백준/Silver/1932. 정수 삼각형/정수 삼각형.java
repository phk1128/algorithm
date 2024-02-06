import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Long>[] dp;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        dp = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            dp[i] = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                dp[i].add(Long.parseLong(st.nextToken()));
            }
        }

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j <= i + 1; j++) {
                if (j == 0) {
                    dp[i + 1].set(j, dp[i].get(j) + dp[i + 1].get(j));
                } else if (j == i + 1) {
                    dp[i + 1].set(j, dp[i].get(i) + dp[i + 1].get(i + 1));
                } else {
                    long max = Math.max(dp[i + 1].get(j) + dp[i].get(j - 1), dp[i + 1].get(j) + dp[i].get(j));
                    dp[i + 1].set(j, max);
                }
            }
        }

        bw.write(String.valueOf(Collections.max(dp[n - 1])));
        bw.flush();
        bw.close();
    }
}