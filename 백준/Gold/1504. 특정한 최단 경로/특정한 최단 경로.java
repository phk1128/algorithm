import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        dist = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            dist[i][i] = 0;
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dist[start][end] = cost;
            dist[end][start] = cost;

        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        floydSolve(n);

        int[][] root = new int[][]{{1, v1, v2, n}, {1, v2, v1, n}};

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 2; i++) {
            int sum = 0;
            boolean flag = true;
            for (int j = 0; j < 3; j++) {
                if (dist[root[i][j]][root[i][j + 1]] == Integer.MAX_VALUE) {
                    flag = false;
                    break;
                }
                sum += dist[root[i][j]][root[i][j + 1]];
            }
            if (flag) {
                min = Math.min(sum, min);
            }
        }

        if (min == Integer.MAX_VALUE) {
            min = -1;
        }

        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();

    }

    private static void floydSolve(int n) {

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        if (dist[i][j] > dist[i][k] + dist[k][j]) {
                            dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }
                }
            }
        }

    }
}