import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= (int) Math.pow(i, 0.5); j++) {
                min = Math.min(min, dp[i - (j * j)]);
            }
            dp[i] = min + 1;
        }
        bw.write(String.valueOf(dp[n]));
        bw.flush();
        bw.close();
    }
}