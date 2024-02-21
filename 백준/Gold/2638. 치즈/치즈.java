import java.util.*;
import java.io.*;
import java.util.Map.Entry;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int n;
    private static int m;
    private static int[][] mapView;
    private static boolean[][] visited;
    private static int[][] directions;
    private static Map<Cheese, Integer> cheeseGroup;
    private static int removeCount;
    private static int cheeseCount;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        mapView = new int[n][m];
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        cheeseGroup = new HashMap<>();
        cheeseCount = 0;
        removeCount = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                mapView[i][j] = Integer.parseInt(st.nextToken());
                if (mapView[i][j] == 1) {
                    cheeseCount++;
                    cheeseGroup.put(new Cheese(j, i), 0);
                }
            }
        }
        int day = 0;

        while (cheeseCount != removeCount) {
            for (Map.Entry<Cheese, Integer> entry : cheeseGroup.entrySet()) {
                cheeseGroup.replace(entry.getKey(), 0);
            }
            visited = new boolean[n][m];
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
                    if (!visited[newY][newX] && mapView[newY][newX] == 0) {
                        visited[newY][newX] = true;
                        findCheese(newX, newY, queue);
                    }
                }
            }
        }
    }

    private static void findCheese(int x, int y, Queue<int[]> queue) {

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                if (mapView[newY][newX] == 1) {
                    Cheese cheese = new Cheese(newX, newY);
                    int count = cheeseGroup.get(cheese);
                    if (count >= 1) {
                        mapView[newY][newX] = 0;
                        visited[newY][newX] = true;
                        cheeseGroup.remove(cheese);
                        removeCount++;
                    } else {
                        cheeseGroup.replace(cheese, cheeseGroup.get(cheese) + 1);
                    }
                }
            }
            queue.offer(new int[]{x, y});
        }
    }

    static class Cheese {

        private int x;
        private int y;

        public Cheese(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Cheese)) {
                return false;
            }
            Cheese cheese = (Cheese) o;
            return Objects.equals(this.x, cheese.x) &&
                    Objects.equals(this.y, cheese.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.x, this.y);
        }
    }
}