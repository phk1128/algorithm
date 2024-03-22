import java.util.*;
import java.io.*;


public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int[][] mapView;
    private static int[][] rotatedMapView;
    private static int[][] meltedMapView;
    private static boolean[][] visited;
    private static int[][] directions;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        mapView = new int[(int) Math.pow(2, N)][(int) Math.pow(2, N)];
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int r = 0; r < Math.pow(2, N); r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < Math.pow(2, N); c++) {
                mapView[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        while (Q-- > 0) {
            int command = Integer.parseInt(st.nextToken());
            visited = new boolean[(int) Math.pow(2, N)][(int) Math.pow(2, N)];
            rotatedMapView = new int[(int) Math.pow(2, N)][(int) Math.pow(2, N)];

            rotate(0, 0, (int) Math.pow(2, command));
            meltIce();

            for (int i = 0; i < Math.pow(2, N); i++) {
                mapView[i] = Arrays.copyOf(meltedMapView[i], (int) Math.pow(2, N));
            }

        }

        int max = 0;
        visited = new boolean[(int) Math.pow(2, N)][(int) Math.pow(2, N)];
        for (int r = 0; r < Math.pow(2, N); r++) {
            for (int c = 0; c < Math.pow(2, N); c++) {
                if (mapView[r][c] == 0 || visited[r][c]) {
                    continue;
                }
                max = Math.max(max, calculateMaxIce(r, c, 1));
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(iceSum());
        sb.append("\n");
        sb.append(max);

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    private static int iceSum() {
        int sum = 0;

        for (int r = 0; r < Math.pow(2, N); r++) {
            for (int c = 0; c < Math.pow(2, N); c++) {
                sum += mapView[r][c];
            }
        }
        return sum;
    }

    private static int calculateMaxIce(int r, int c, int count) {

        visited[r][c] = true;

        for (int[] direction : directions) {
            int newR = r + direction[0];
            int newC = c + direction[1];

            if (!(newR >= 0 && newR < Math.pow(2, N) && newC >= 0 && newC < Math.pow(2, N))) {
                continue;
            }

            if (mapView[newR][newC] == 0 || visited[newR][newC]) {
                continue;
            }
            count = calculateMaxIce(newR, newC, count + 1);
        }
        return count;
    }

    private static void meltIce() {
        meltedMapView = new int[(int) Math.pow(2, N)][(int) Math.pow(2, N)];

        for (int r = 0; r < Math.pow(2, N); r++) {
            for (int c = 0; c < Math.pow(2, N); c++) {
                if (rotatedMapView[r][c] == 0) {
                    continue;
                }
                int count = 0;
                int number = rotatedMapView[r][c];
                for (int[] direction : directions) {
                    int newR = r + direction[0];
                    int newC = c + direction[1];

                    if (!(newR >= 0 && newR < Math.pow(2, N) && newC >= 0 && newC < Math.pow(2, N))) {
                        continue;
                    }

                    if (rotatedMapView[newR][newC] > 0) {
                        count++;
                    }
                }
                if (count < 3) {
                    number--;
                }
                meltedMapView[r][c] = number;
            }
        }
    }

    private static void rotate(int r, int c, int bound) {

        visited[r][c] = true;

        for (int i = 0; i < bound; i++) {
            for (int j = 0; j < bound; j++) {
                int newR = j;
                int newC = bound - (i + 1);
                rotatedMapView[newR + r][newC + c] = mapView[i + r][j + c];
            }
        }

        if (r + bound < Math.pow(2, N)) {
            if (!visited[r + bound][c]) {
                rotate(r + bound, c, bound);
            }
        }

        if (c + bound < Math.pow(2, N)) {
            if (!visited[r][c + bound]) {
                rotate(r, c + bound, bound);
            }
        }
    }
}