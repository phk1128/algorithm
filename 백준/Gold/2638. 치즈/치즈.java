import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int n;
    private static int m;
    private static int[][] mapView;
    private static int[][] removeMapview;
    private static boolean[][] visited;
    private static int[][] directions;
    private static int cheeseCount;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        mapView = new int[n][m];
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        cheeseCount = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                mapView[i][j] = Integer.parseInt(st.nextToken());
                if (mapView[i][j] == 1) {
                    cheeseCount++;
                }
            }
        }
        int day = 0;

        while (cheeseCount != 0) {
            visited = new boolean[n][m];
            removeMapview = new int[n][m];
            bfsSolve();
            day++;
        }

        bw.write(String.valueOf(day));
        bw.flush();
        bw.close();
    }

    private static void bfsSolve() {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int x = position[0];
            int y = position[1];

            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    if (mapView[newY][newX] == 1) {
                        if (removeMapview[newY][newX] >= 1) {
                            mapView[newY][newX] = 0;
                            visited[newY][newX] = true;
                            cheeseCount--;
                        } else {
                            removeMapview[newY][newX]++;
                        }
                    }
                    if (!visited[newY][newX] && mapView[newY][newX] == 0) {
                        visited[newY][newX] = true;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }
    }
}