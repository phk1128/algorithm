import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dr = new int[]{-1, 1, 0, 0};
    private static final int[] dc = new int[]{0, 0, -1, 1};
    private static Character[][] mapView;
    private static boolean[][] visited;
    private static int[] start;
    private static int N;
    private static int M;
    private static int count;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        mapView = new Character[N][M];
        visited = new boolean[N][M];

        for (int r = 0; r < N; r++) {
            final String row = br.readLine();
            for (int c = 0; c < M; c++) {
                final char el = row.charAt(c);
                mapView[r][c] = el;
                if (el == 'I') {
                    start = new int[]{r, c};
                }
            }
        }
        solve();
        if (count == 0) {
            System.out.println("TT");
            return;
        }
        System.out.println(count);
    }

    private static void solve() {
        Queue<int[]> queue = new ArrayDeque();
        queue.offer(new int[]{start[0], start[1]});
        while(!queue.isEmpty()) {
            final int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                final int nR = cur[0] + dr[i];
                final int nC = cur[1] + dc[i];
                if (!(nR >= 0 && nR <= N - 1 && nC >= 0 && nC <= M - 1)) {
                    continue;
                }
                if (visited[nR][nC]) {
                    continue;
                }
                if (mapView[nR][nC] == 'X') {
                    continue;
                }
                if (mapView[nR][nC] == 'P') {
                    count++;
                }
                visited[nR][nC] = true;
                queue.offer(new int[]{nR, nC});
            }
        }
    }
}
