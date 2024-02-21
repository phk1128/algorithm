import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int n;
    private static int[][] mapView;
    private static int answer;
    private static int[][] directions;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        mapView = new int[n][n];
        answer = 0;
        directions = new int[][]{{0, 1}, {1, 1}, {1, 0}};

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                mapView[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 1, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void solve(int status, int x, int y) {

        if (x == n - 1 && y == n - 1) {
            answer++;
            return;
        }
        boolean flag = true;
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (newX >= 0 && newX < n && newY >= 0 && newY < n) {
                if (mapView[newY][newX] == 1) {
                    flag = false;
                    break;
                }
            } else {
                flag = false;
                break;
            }
        }
        if (flag) {
            solve(2, x + 1, y + 1);
        }

        if (status == 0 || status == 2) {
            int newX = x + 1;
            int newY = y;
            if (newX >= 0 && newX < n && newY >= 0 && newY < n) {
                if (mapView[newY][newX] == 0) {
                    solve(0, newX, newY);
                }
            }
        }

        if (status == 1 || status == 2) {
            int newX = x;
            int newY = y + 1;
            if (newX >= 0 && newX < n && newY >= 0 && newY < n) {
                if (mapView[newY][newX] == 0) {
                    solve(1, newX, newY);
                }
            }
        }
    }
}