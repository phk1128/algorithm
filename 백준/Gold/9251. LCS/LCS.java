import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static String[] sequence;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sequence = new String[2];
        st = new StringTokenizer(br.readLine());
        sequence[0] = st.nextToken();
        st = new StringTokenizer(br.readLine());
        sequence[1] = st.nextToken();

        dp = new int[sequence[1].length()][sequence[0].length()];

        for (int i = 0; i < sequence[1].length(); i++) {
            Arrays.fill(dp[i], -1);
        }

        bw.write(String.valueOf(lcs(sequence[1].length() - 1, sequence[0].length() - 1)));
        bw.flush();
        bw.close();
    }

    private static int lcs(int r, int c) {

        if (r < 0 || c < 0) {
            return 0;
        }

        if (dp[r][c] == -1) {
            dp[r][c] = 0;
            // sequence[0] = ACAYKP 열 / squence[1] = CAPCAK 행
            if (Objects.equals(sequence[0].charAt(c), sequence[1].charAt(r))) {
                dp[r][c] = lcs(r - 1, c - 1) + 1;
            } else {

                dp[r][c] = Math.max(lcs(r - 1, c), lcs(r, c - 1));
            }

        }
        return dp[r][c];
    }
}