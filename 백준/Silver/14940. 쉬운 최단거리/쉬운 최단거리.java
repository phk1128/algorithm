import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int n;
    private static int m;
    private static int[][] mapView;
    private static int[][] dist;
    private static int[] target;
    private static boolean[][] visited;
    private static int[][] directions;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        mapView = new int[n][m];
        dist = new int[n][m];
        visited = new boolean[n][m];
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int position = Integer.parseInt(st.nextToken());
                mapView[i][j] = position;
                if (position == 2) {
                    target = new int[]{j, i};
                }

                if (position == 0) {
                    visited[i][j] = true;
                }
            }
        }

        bfsSolve(target[0], target[1], 0);

        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (!visited[y][x] && (dist[y][x] == 0)) {
                    dist[y][x] = -1;
                }
                sb.append(dist[y][x]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    private static void bfsSolve(int startX, int startY, int initCount) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(startX, startY, initCount));
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            Position position = queue.poll();
            int x = position.getX();
            int y = position.getY();
            int count = position.getCount();
            dist[y][x] = count;

            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    if (!visited[newY][newX]) {
                        visited[newY][newX] = true;
                        queue.offer(new Position(newX, newY, count + 1));
                    }
                }
            }
        }
    }


    static class Position implements Comparable<Position> {

        private int x;
        private int y;
        private int count;

        public Position(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        private int getX() {
            return this.x;
        }

        private int getY() {
            return this.y;
        }

        private int getCount() {
            return this.count;
        }

        @Override
        public int compareTo(Position p) {
            return this.count - p.count;
        }
    }
}