import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int n;
    private static int m;
    private static int h;
    private static boolean[][] visited;
    private static List<int[]> initTomatos;
    private static int emptyTomato;
    private static int tomatoCount;
    private static int day;
    private static int[][] directions;
    private static int[][] upFloor;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        visited = new boolean[m * h][n];
        initTomatos = new ArrayList<>();
        emptyTomato = 0;
        tomatoCount = 0;
        day = 0;
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        upFloor = new int[][]{{0, -m}, {0, m}};

        for (int i = 0; i < m * h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int number = Integer.parseInt(st.nextToken());
                if (number == -1) {
                    emptyTomato++;
                    visited[i][j] = true;
                }
                if (number == 1) {
                    visited[i][j] = true;
                    tomatoCount++;
                    initTomatos.add(new int[]{j, i});
                }
            }
        }

        if ((n * m * h) - emptyTomato != tomatoCount) {
            bfsSolve();
        }

        if ((n * m * h) - emptyTomato != tomatoCount) {
            day = -1;
        }

        bw.write(String.valueOf(day));
        bw.flush();
        bw.close();
    }

    private static void bfsSolve() {
        Queue<List<int[]>> queue = new LinkedList<>();
        queue.offer(initTomatos);

        while (!queue.isEmpty()) {
            List<int[]> tomatos = queue.poll();
            if (!tomatos.isEmpty()) {
                List<int[]> tmpTomatos = new ArrayList<>();
                for (int[] t : tomatos) {
                    if (visited[t[1]][t[0]]) {
                        findTomato(t[0], t[1], tmpTomatos);
                    }
                }
                if (!tmpTomatos.isEmpty()) {
                    queue.offer(tmpTomatos);
                    day++;
                }
            }
        }
    }

    private static void findTomato(int x, int y, List<int[]> tomatos) {

        for (int[] up : upFloor) {
            int newX = (x + up[0]);
            int newY = (y + up[1]);
            if (newX >= 0 && newX < n && newY >= 0 && newY < m * h) {
                if (!visited[newY][newX]) {
                    visited[newY][newX] = true;
                    tomatos.add(new int[]{newX, newY});
                    tomatoCount++;
                }
            }
        }

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (newX >= 0 && newX < n && newY >= (y / m) * m && newY < ((y / m) * m) + m) {
                if (!visited[newY][newX]) {
                    visited[newY][newX] = true;
                    tomatoCount++;
                    tomatos.add(new int[]{newX, newY});
                }
            }
        }
    }
}