import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static String sequence1;
    private static String sequence2;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        sequence1 = st.nextToken();
        st = new StringTokenizer(br.readLine());
        sequence2 = st.nextToken();

        dp = new int[sequence1.length() + 1][sequence2.length() + 1];

        for (int i = 1; i <= sequence1.length(); i++) {
            for (int j = 1; j <= sequence2.length(); j++) {
                if (Objects.equals(sequence1.charAt(i - 1), sequence2.charAt(j - 1))) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        bw.write(String.valueOf(dp[sequence1.length()][sequence2.length()]));
        bw.flush();
        bw.close();
    }
}