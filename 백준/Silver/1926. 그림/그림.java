import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int[] dr = new int[]{-1, 1, 0, 0};
    private static int[] dc = new int[]{0, 0 ,-1, 1};
    private static int[][] mapView;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        mapView = new int[n][m];
        visited = new boolean[n][m];

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                mapView[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int total = 0;
        int max = 0;

        for (int r  = 0; r < n; r++) {
            for  (int c = 0; c < m; c++) {
                if (visited[r][c] || mapView[r][c] == 0) {
                    continue;
                }
                total++;
                max = Math.max(max,count(r, c, n, m));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(total);
        sb.append("\n");
        sb.append(max);
        System.out.println(sb);
    }

    private static int count(int r, int c, int n, int m) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c});
        int count = 1;
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cR = cur[0];
            int cC = cur[1];

            for (int i = 0; i < 4; i++) {
                int nR = cR + dr[i];
                int nC = cC + dc[i];

                if (!(nR >= 0 && nR < n && nC >= 0 && nC < m)) {
                    continue;
                }

                if (mapView[nR][nC] == 0) {
                    continue;
                }

                if (visited[nR][nC]) {
                    continue;
                }
                visited[nR][nC] = true;
                count++;
                queue.offer(new int[]{nR, nC});
            }
        }
        return count;
    }

}
