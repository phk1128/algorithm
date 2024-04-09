import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static boolean[][] visited;
    private static boolean[][] crushedVisited;
    private static int[][] mapView;
    private static int answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visited = new boolean[n][m];
        crushedVisited = new boolean[n][m];

        mapView = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String[] inputSplit = st.nextToken().split("");
            for (int j = 0; j < m; j++) {
                mapView[i][j] = Integer.parseInt(inputSplit[j]);
            }
        }
        bfsSolve(n, m);
        System.out.println(answer);
    }

    private static void bfsSolve(int n, int m) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(0, 0, 1, false));
        visited[0][0] = true;
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            Position position = queue.poll();
            int x = position.getX();
            int y = position.getY();
            int count = position.getCount();
            boolean crushed = position.isCrushed();

            if (x == m - 1 && y == n - 1) {
                answer = count;
                return;
            }

            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    if (mapView[newY][newX] == 0) {
                        if (crushed && !crushedVisited[newY][newX]) {
                            crushedVisited[newY][newX] = true;
                            queue.offer(new Position(newX, newY, count + 1, crushed));
                        }
                        if (!crushed && !visited[newY][newX]) {
                            visited[newY][newX] = true;
                            queue.offer(new Position(newX, newY, count + 1, crushed));
                        }
                    }
                    if (!visited[newY][newX] && !crushed && mapView[newY][newX] == 1) {
                        visited[newY][newX] = true;
                        queue.offer(new Position(newX, newY, count + 1, true));
                    }

                }
            }
        }

        answer = -1;
    }

    static class Position {
        private int x;
        private int y;
        private int count;
        private boolean isCrushed;

        public Position(int x, int y, int count, boolean isCrushed) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.isCrushed = isCrushed;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getCount() {
            return count;
        }

        public boolean isCrushed() {
            return isCrushed;
        }
    }
}