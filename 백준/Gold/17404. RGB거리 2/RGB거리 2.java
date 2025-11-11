import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] RGB = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            RGB[i][0] = Integer.parseInt(st.nextToken());
            RGB[i][1] = Integer.parseInt(st.nextToken());
            RGB[i][2] = Integer.parseInt(st.nextToken());
        }

        long[][] dp0 = new long[N + 1][3];
        long[][] dp1 = new long[N + 1][3];
        long[][] dp2 = new long[N + 1][3];

        dp0[1][0] = RGB[1][0];
        dp0[1][1] = Integer.MAX_VALUE;
        dp0[1][2] = Integer.MAX_VALUE;

        dp1[1][0] = Integer.MAX_VALUE;
        dp1[1][1] = RGB[1][1];
        dp1[1][2] = Integer.MAX_VALUE;

        dp2[1][0] = Integer.MAX_VALUE;
        dp2[1][1] = Integer.MAX_VALUE;
        dp2[1][2] = RGB[1][2];

        for (int i = 2; i <= N; i++) {
            dp0[i][0] = Math.min(dp0[i - 1][1], dp0[i - 1][2]) + RGB[i][0];
            dp0[i][1] = Math.min(dp0[i - 1][0], dp0[i - 1][2]) + RGB[i][1];
            dp0[i][2] = Math.min(dp0[i - 1][0], dp0[i - 1][1]) + RGB[i][2];

            dp1[i][0] = Math.min(dp1[i - 1][1], dp1[i - 1][2]) + RGB[i][0];
            dp1[i][1] = Math.min(dp1[i - 1][0], dp1[i - 1][2]) + RGB[i][1];
            dp1[i][2] = Math.min(dp1[i - 1][0], dp1[i - 1][1]) + RGB[i][2];

            dp2[i][0] = Math.min(dp2[i - 1][1], dp2[i - 1][2]) + RGB[i][0];
            dp2[i][1] = Math.min(dp2[i - 1][0], dp2[i - 1][2]) + RGB[i][1];
            dp2[i][2] = Math.min(dp2[i - 1][0], dp2[i - 1][1]) + RGB[i][2];
        }

        long answer = Math.min(
                Math.min(dp0[N][1], dp0[N][2]),
                Math.min(dp1[N][0], dp1[N][2])
        );
        answer = Math.min(answer, Math.min(dp2[N][0], dp2[N][1]));
        System.out.println(answer);
    }
}
