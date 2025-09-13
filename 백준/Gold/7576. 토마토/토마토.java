import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static StringTokenizer st;

    private static int m;
    private static int n;
    private static boolean[][] unripe;
    private static List<int[]> ripeTomatoes;
    private static int[][] directions;
    private static int emptyCells;
    private static int days;
    private static int ripenedCount;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        unripe = new boolean[n][m];
        ripeTomatoes = new ArrayList<>();
        emptyCells = 0;
        days = 0;
        ripenedCount = 0;
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1) {
                    emptyCells++;
                }
                if (v == 0 || v == 1) {
                    unripe[i][j] = true;
                    if (v == 1) {
                        unripe[i][j] = false;
                        ripeTomatoes.add(new int[]{j, i});
                    }
                }
            }
        }
        bfs();
        if ((n * m) - emptyCells != ripenedCount) {
            days = -1;
        }

        System.out.println(days);
    }

    private static void bfs() {
        Queue<List<int[]>> queue = new LinkedList<>();
        queue.offer(ripeTomatoes);
        while (!queue.isEmpty()) {
            List<int[]> nextRipe = new ArrayList<>();
            for (int[] tomato : queue.poll()) {
                ripenedCount++;
                nextRipe.addAll(spread(tomato[1], tomato[0]));
            }
            if (!nextRipe.isEmpty()) {
                queue.offer(nextRipe);
                days++;
            }
        }
    }

    private static List<int[]> spread(int y, int x) {
        List<int[]> next = new ArrayList<>();
        unripe[y][x] = false;
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                if (unripe[newY][newX]) {
                    unripe[newY][newX] = false;
                    next.add(new int[]{newX, newY});
                }
            }
        }
        return next;
    }
}
