import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static boolean[][] visited;
    private static int[][] directions;
    private static int[][] points;
    private static int count;
    private static int cabbage;
    private static List<Integer> results;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());

        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        results = new ArrayList<>();

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            visited = new boolean[n][m];
            points = new int[k][2];
            count = 0;
            cabbage = 1;
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                visited[y][x] = true;
                points[j][0] = x;
                points[j][1] = y;
            }

            for (int[] point : points) {
                if (visited[point[1]][point[0]]) {
                    visited[point[1]][point[0]] = false;
                    findCabbage(point[0], point[1], m, n);
                    count++;
                }
                if (cabbage == k) {
                    break;
                }
            }

            results.add(count);
        }

        for (int result : results) {
            bw.write(result + "\n");
            bw.flush();
        }
        bw.close();
    }

    private static void findCabbage(int x, int y, int m, int n) {

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (newX >= 0 && newX <= m - 1 && newY >= 0 && newY <= n - 1) {
                if (visited[newY][newX]) {
                    visited[newY][newX] = false;
                    cabbage++;
                    findCabbage(newX, newY, m, n);
                }
            }
        }
    }
}