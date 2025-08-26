import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int R;
    private static int C;
    private static int T;
    private static int[][] mapView;
    private static int[] cleaners;
    private static int[][] diffMapView;
    private static int[] dr;
    private static int[] dc;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        mapView = new int[R][C];
        cleaners = new int[2];
        int cleanerIdx = 0;
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                int el = Integer.parseInt(st.nextToken());
                mapView[r][c] = el;
                if (el == -1) {
                    cleaners[cleanerIdx++] = r;
                }
            }
        }

        dr = new int[]{-1, 1, 0, 0};
        dc = new int[]{0, 0, -1, 1};
        int upCleanerR = cleaners[0];
        int downCleanerR = cleaners[1];
        while (T-- > 0) {
            diffMapView = new int[R][C];
            diffuse();
            moveDust(upCleanerR, downCleanerR);
            mapView = diffMapView;
            mapView[upCleanerR][0] = 0;
            mapView[downCleanerR][0] = 0;
        }

        int ans = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                ans += mapView[r][c];
            }
        }
        System.out.println(ans);
    }

    private static void moveDust(int upCleanerR, int downCleanerR) {

        for (int r = upCleanerR - 1; r > 0; r--) {
            diffMapView[r][0] = diffMapView[r - 1][0];
        }

        for (int r = downCleanerR + 1; r < R - 1; r++) {
            diffMapView[r][0] = diffMapView[r + 1][0];
        }

        for (int c = 0; c < C - 1; c++) {
            diffMapView[0][c] = diffMapView[0][c + 1];
            diffMapView[R - 1][c] = diffMapView[R - 1][c + 1];
        }

        for (int r = 0; r < upCleanerR; r++) {
            diffMapView[r][C - 1] = diffMapView[r + 1][C - 1];
        }

        for (int r = R - 1; r > downCleanerR; r--) {
            diffMapView[r][C - 1] = diffMapView[r - 1][C - 1];
        }

        for (int c = C - 1; c > 0; c--) {
            diffMapView[upCleanerR][c] = diffMapView[upCleanerR][c - 1];
            diffMapView[downCleanerR][c] = diffMapView[downCleanerR][c - 1];
        }
    }

    private static void diffuse() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (mapView[r][c] > 0 &&!isCleaner(r, c)) {
                    diffuseFromCell(r, c);
                }
            }
        }
    }

    private static void diffuseFromCell(int r, int c) {
        int curDust = mapView[r][c];
        int diffDust = mapView[r][c] / 5;
        for (int i = 0; i < 4; i++) {
            int nR = r + dr[i];
            int nC = c + dc[i];
            if ((isIn(nR, nC)) && !isCleaner(nR, nC)) {
                diffMapView[nR][nC] += diffDust;
                curDust -= diffDust;
            }
        }
        diffMapView[r][c] += curDust;
    }

    private static boolean isCleaner(int r, int c) {
        return (r == cleaners[0] && c == 0) || (r == cleaners[1] && c == 0);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && r <= R - 1 && c >= 0 && c <= C - 1;
    }
}
