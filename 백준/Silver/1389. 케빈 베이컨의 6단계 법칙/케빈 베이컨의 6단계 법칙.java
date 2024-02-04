import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static long[][] kevin;
    private static int min;
    private static int answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        kevin = new long[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(kevin[i], Integer.MAX_VALUE);
            kevin[i][i] = 0;
            kevin[i][0] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            kevin[start][end] = 1;
            kevin[end][start] = 1;
        }

        floyd(n);

        min = Integer.MAX_VALUE;
        answer = n;

        for (int i = 1; i <= n; i++) {
            int sum = (int) Arrays.stream(kevin[i]).sum();
            min = Math.min(min, sum);
        }

        for (int i = 1; i <= n; i++) {
            int sum = (int) Arrays.stream(kevin[i]).sum();
            if (sum == min) {
                answer = Math.min(answer, i);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void floyd(int n) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (kevin[j][k] >= kevin[j][i] + kevin[i][k]) {
                        kevin[j][k] = kevin[j][i] + kevin[i][k];
                    }
                }
            }
        }
    }
}