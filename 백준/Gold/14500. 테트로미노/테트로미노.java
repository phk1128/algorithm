import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[][] paper;
    private static int n;
    private static int m;
    private static int max;
    private static List<int[][]> directions;
    private static int[][] rotations;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        paper = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rotations = new int[][]{{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        directions = new ArrayList<>();
        directions.add(new int[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}}); // 긴막대기
        directions.add(new int[][]{{0, 0}, {1, 0}, {0, 1}, {1, 1}}); // 네모
        directions.add(new int[][]{{0, 0}, {0, -1}, {1, -1}, {2, -1}}); // 기억
        directions.add(new int[][]{{0, 0}, {1, 0}, {1, -1}, {2, -1}}); // 리을
        directions.add(new int[][]{{0, 0}, {1, 0}, {1, -1}, {2, 0}}); // 철
        max = 0;
        for (int[][] direction : directions) {
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < m; x++) {
                    solve(x, y, direction);
                }
            }
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }

    private static void solve(int x, int y, int[][] direction) {
        for (int[] rotation : rotations) {
            int count1 = 0;
            int count2 = 0;
            int newX;
            int newY;
            for (int[] d : direction) {
                newX = x + (d[0] * rotation[0]);
                newY = y + (d[1] * rotation[1]);
                if ((newX >= 0 && newX < m && newY >= 0 && newY < n)) {
                    count1 += paper[newY][newX];
                }
                newX = x + (d[1] * rotation[0]);
                newY = y + (d[0] * rotation[1]);
                if ((newX >= 0 && newX < m && newY >= 0 && newY < n)) {
                    count2 += paper[newY][newX];
                }
            }
            max = Math.max(max, count1);
            max = Math.max(max, count2);
        }
    }
}