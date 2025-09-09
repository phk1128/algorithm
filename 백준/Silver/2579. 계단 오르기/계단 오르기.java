import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[] steps;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        steps = new int[n + 1];
        dp = new int[n + 1]; // 해당 계단으로 올 수 있는 최대값이 저장되어 있다.

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            steps[i] = Integer.parseInt(st.nextToken());
        }
        dp[1] = steps[1];

        if (n >= 2) {
            dp[2] = steps[1] + steps[2];
        }

        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 3] + steps[i - 1], dp[i - 2]) + steps[i];
        }

        bw.write(String.valueOf(dp[n]));
        bw.flush();
        bw.close();


    }
}