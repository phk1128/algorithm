import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] mapView;
    private static List<int[]> safeZone;
    private static int safeZoneCount;
    private static int virusCount;
    private static int[][] combination;
    private static boolean[][] visited;
    private static int[][] directions;
    private static int max;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        mapView = new int[N][M];
        visited = new boolean[N][M];
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        safeZone = new ArrayList<>();
        combination = new int[3][2];
        safeZoneCount = 0;
        max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int number = Integer.parseInt(st.nextToken());
                mapView[i][j] = number;

                if (number == 0) {
                    safeZone.add(new int[]{i, j});
                    safeZoneCount++;
                }

            }
        }
        recursiveSolve(0, 0);

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(int start, int depth) {

        if (depth == 3) {
            for (int[] combi : combination) {
                mapView[combi[0]][combi[1]] = 1;
            }
            visited = new boolean[N][M];
            virusCount = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    if (mapView[r][c] == 2 && !visited[r][c]) {
                        diffuseVirus(r, c);
                    }
                }
            }

            max = Math.max(max, (safeZoneCount - virusCount - 3));

            for (int[] combi : combination) {
                mapView[combi[0]][combi[1]] = 0;
            }
            return;
        }

        for (int i = start; i < safeZone.size(); i++) {
            combination[depth] = safeZone.get(i);
            recursiveSolve(i + 1, depth + 1);
        }
    }

    private static void diffuseVirus(int r, int c) {
        visited[r][c] = true;

        for (int[] direction : directions) {
            int nr = r + direction[0];
            int nc = c + direction[1];

            if (!(nc >= 0 && nc < M && nr >= 0 && nr < N)) {
                continue;
            }

            if (visited[nr][nc] || mapView[nr][nc] != 0) {
                continue;
            }
            virusCount++;
            diffuseVirus(nr, nc);
        }
    }
}