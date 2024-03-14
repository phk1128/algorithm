import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] mapView;
    private static List<int[][][]> cctvGroup;
    private static List<int[]> cctvPosition;
    private static int[][][] tmpCctv;
    private static int min;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        mapView = new int[N][M];
        int wallCount = 0;
        int cctvCount = 0;
        cctvPosition = new ArrayList<>();
        min = Integer.MAX_VALUE;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                int number = Integer.parseInt(st.nextToken());

                if (number == 6) {
                    wallCount++;
                }
                if (number != 0 && number != 6) {
                    cctvCount++;
                    cctvPosition.add(new int[]{r, c});
                }
                mapView[r][c] = number;
            }
        }
        int safeZone = (N * M) - (wallCount + cctvCount);

        tmpCctv = new int[cctvCount][][];
        cctvGroup = new ArrayList<>();
        cctvGroup.add(new int[][][]{{{0, 1}}, {{-1, 0}}, {{0, -1}}, {{1, 0}}});
        cctvGroup.add(new int[][][]{{{0, 1}, {0, -1}}, {{-1, 0}, {1, 0}}});
        cctvGroup.add(new int[][][]{{{0, 1}, {-1, 0}}, {{-1, 0}, {0, -1}}, {{0, -1}, {1, 0}}, {{0, 1}, {1, 0}}});
        cctvGroup.add(new int[][][]{{{0, 1}, {0, -1}, {-1, 0}}, {{-1, 0}, {0, -1}, {1, 0}}, {{0, -1}, {1, 0}, {0, 1}},
                {{-1, 0}, {1, 0}, {0, 1}}});
        cctvGroup.add(new int[][][]{{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}});

        recursiveSolve(0, safeZone);

        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(int depth, int safeZone) {

        if (depth == cctvPosition.size()) {
            min = Math.min(min, safeZone - calculateCount());
            return;
        }

        int r = cctvPosition.get(depth)[0];
        int c = cctvPosition.get(depth)[1];

        for (int[][] cctv : cctvGroup.get(mapView[r][c] - 1)) {
            tmpCctv[depth] = cctv;
            recursiveSolve(depth + 1, safeZone);
        }
    }

    private static int calculateCount() {

        visited = new boolean[N][M];
        int count = 0;
        for (int i = 0; i < cctvPosition.size(); i++) {
            for (int[] direction : tmpCctv[i]) {
                int r = cctvPosition.get(i)[0];
                int c = cctvPosition.get(i)[1];
                while (true) {
                    int nr = r + direction[0];
                    int nc = c + direction[1];

                    if (!(nr >= 0 && nr < N && nc >= 0 && nc < M)) {
                        break;
                    }

                    if (mapView[nr][nc] == 6) {
                        break;
                    }

                    if (mapView[nr][nc] == 0 && !visited[nr][nc]) {
                        visited[nr][nc] = true;
                        count++;
                    }

                    r = nr;
                    c = nc;
                }
            }
        }
        return count;
    }
}