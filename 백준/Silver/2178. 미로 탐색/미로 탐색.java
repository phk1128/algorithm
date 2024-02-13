import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static boolean[][] visited;
    private static int[][] directions;
    private static int answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1][m + 1];
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            String[] inputSplit = st.nextToken().split("");
            for (int j = 1; j <= m; j++) {
                if (Objects.equals(inputSplit[j-1], "0")) {
                    visited[i][j] = true;
                }
            }
        }
        bfsSolve(n, m);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void bfsSolve(int n, int m) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(1, 1, 1));

        while (!queue.isEmpty()) {
            Position position = queue.poll();
            int x = position.getX();
            int y = position.getY();
            int count = position.getCount();

            if (x == m && y == n) {
                answer = count;
                return;
            }
            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (newX >= 1 && newX <= m && newY >= 1 && newY <= n) {
                    if (!visited[newY][newX]) {
                        visited[newY][newX] = true;
                        queue.offer(new Position(newX, newY, count + 1));
                    }
                }
            }
        }
    }

    static class Position {

        private int x;
        private int y;
        private int count;

        public Position(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
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
    }

}