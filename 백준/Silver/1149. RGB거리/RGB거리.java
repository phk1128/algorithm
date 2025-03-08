import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 5][5];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            dp[i][0] = R;
            dp[i][1] = G;
            dp[i][2] = B;
        }

        for (int i = 1; i <= N; i++) {
            dp[i + 1][0] = Math.min(dp[i + 1][0] + dp[i][1], dp[i + 1][0] + dp[i][2]);
            dp[i + 1][1] = Math.min(dp[i + 1][1] + dp[i][0], dp[i + 1][1] + dp[i][2]);
            dp[i + 1][2] = Math.min(dp[i + 1][2] + dp[i][1] , dp[i + 1][2] + dp[i][0]);
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            answer = Math.min(dp[N][i], answer);
        }
        System.out.println(answer);
    }
}
