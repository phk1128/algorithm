import java.util.*;
import java.io.*;
public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static int[] dp;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        dp = new int[1001];
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i <= n; i++) {
            dp[i] = ((dp[i-2] * 2) + dp[i-1]) % 10_007;
        }
        System.out.println(dp[n]);
    }
}