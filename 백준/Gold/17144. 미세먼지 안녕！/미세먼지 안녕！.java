import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int R;
    private static int C;
    private static int T;
    private static int[][] mapView;
    private static int upAirR;
    private static int downAirR;
    private static int upAirC;
    private static int downAirC;
    private static boolean[][] visited;
    private static int[][] directions;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        mapView = new int[R][C];
        List<int[]> airCleaners = new ArrayList<>();
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                int number = Integer.parseInt(st.nextToken());
                mapView[r][c] = number;
                if (number == -1) {
                    airCleaners.add(new int[]{r, c});
                }
            }
        }
        upAirR = airCleaners.get(0)[0];
        downAirR = airCleaners.get(1)[0];
        upAirC = airCleaners.get(0)[1];
        downAirC = airCleaners.get(1)[1];

        while (T-- > 0) {
            int[][] diffusionMapView = new int[R][C];
            int[][] moveMapView = new int[R][C];
            visited = new boolean[R][C];
            recursiveSolve(0, 0, diffusionMapView);
            visited = new boolean[R][C];
            moveDust(0, 0, diffusionMapView, moveMapView);
            mapView = moveMapView;
            mapView[upAirR][upAirC] = -1;
            mapView[downAirR][downAirC] = -1;
        }
        int answer = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                answer += mapView[r][c];
            }
        }
        answer += 2;

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(int r, int c, int[][] diffusionMapView) {
        visited[r][c] = true;
        if (mapView[r][c] != 0 && mapView[r][c] != -1) {
            diffusion(r, c, diffusionMapView);
        }

        for (int[] direction : directions) {
            int newR = r + direction[0];
            int newC = c + direction[1];
            if (newR >= 0 && newR < R && newC >= 0 && newC < C) {
                if (!visited[newR][newC]) {
                    recursiveSolve(newR, newC, diffusionMapView);
                }
            }
        }
    }

    private static void diffusion(int r, int c, int[][] diffusionMapView) {

        int dust = mapView[r][c] / 5;
        int count = 0;
        for (int[] direction : directions) {
            int newR = r + direction[0];
            int newC = c + direction[1];
            if (newR >= 0 && newR < R && newC >= 0 && newC < C) {
                if (mapView[newR][newC] != -1) {
                    diffusionMapView[newR][newC] += dust;
                    count++;
                }
            }
        }
        int number = mapView[r][c] - (dust * count);
        diffusionMapView[r][c] += number;
    }

    private static void moveDust(int r, int c, int[][] diffusionMapView, int[][] moveMapView) {

        visited[r][c] = true;
        if (r > 0 && r < R - 1 && c > 0 && c < C - 1) {
            if (!(r == upAirR || r == downAirR)) {
                moveMapView[r][c] = diffusionMapView[r][c];
            }
        }
        if (r == upAirR || r == downAirR) {
            if (c > 1 && c < C) {
                moveMapView[r][c] = diffusionMapView[r][c - 1];
            }
        }

        if (c == C - 1) {
            if (r < upAirR && r >= 0) {
                moveMapView[r][c] = diffusionMapView[r + 1][c];
            }

            if (r > downAirR && r < R) {
                moveMapView[r][c] = diffusionMapView[r - 1][c];
            }
        }

        if (r == R - 1 || r == 0) {
            if (c < C - 1 && c >= 0) {
                moveMapView[r][c] = diffusionMapView[r][c + 1];
            }
        }

        if (c == 0) {
            if (r < upAirR && r > 0) {
                moveMapView[r][c] = diffusionMapView[r - 1][c];
            }

            if (r > downAirR && r < R - 1) {
                moveMapView[r][c] = diffusionMapView[r + 1][c];
            }
        }

        for (int[] direction : directions) {
            int newR = r + direction[0];
            int newC = c + direction[1];

            if (newR >= 0 && newR < R && newC >= 0 && newC < C) {
                if (!visited[newR][newC]) {
                    moveDust(newR, newC, diffusionMapView, moveMapView);
                }
            }
        }

    }
}