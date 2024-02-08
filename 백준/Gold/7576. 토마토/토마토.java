import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;

    private static int m;
    private static int n;
    private static boolean[][] visited;
    private static List<int[]> initTomato;
    private static int[][] directions;
    private static int emptyCount;
    private static int day;
    private static int tomatoCount;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        visited = new boolean[n][m];
        initTomato = new ArrayList<>();
        emptyCount = 0;
        day = 0;
        tomatoCount = 0;
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int v = Integer.parseInt(st.nextToken());
                if (v == -1) {
                    emptyCount++;
                }
                if (v == 0 || v == 1) {
                    visited[i][j] = true;
                    if (v == 1) {
                        visited[i][j] = false;
                        initTomato.add(new int[]{j, i});
                    }
                }
            }
        }
        solve();
        if ((n * m) - emptyCount != tomatoCount) {
            day = -1;
        }

        bw.write(String.valueOf(day));
        bw.flush();
        bw.close();
    }

    private static void solve() {
        Queue<List<int[]>> queue = new LinkedList<>();
        queue.offer(initTomato);
        while (!queue.isEmpty()) {
            List<int[]> tomato = new ArrayList<>();
            for (int[] target : queue.poll()) {
                tomatoCount++;
                tomato.addAll(findTomato(target[1], target[0]));
            }
            if (!tomato.isEmpty()) {
                queue.offer(tomato);
                day++;
            }
        }
    }

    private static List<int[]> findTomato(int y, int x) {
        List<int[]> tomato = new ArrayList<>();
        visited[y][x] = false;
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                if (visited[newY][newX]) {
                    visited[newY][newX] = false;
                    tomato.add(new int[]{newX, newY});
                }
            }
        }
        return tomato;
    }
}