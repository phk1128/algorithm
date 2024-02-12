import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static String[][] mapView;
    private static boolean[][] visited;
    private static int n;
    private static int m;
    private static int[] start;
    private static int[][] directions;
    private static int answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        mapView = new String[n][m];
        visited = new boolean[n][m];
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        answer = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String[] positionSplit = st.nextToken().split("");
            for (int j = 0; j < m; j++) {
                mapView[i][j] = positionSplit[j];
                if (Objects.equals(positionSplit[j], "X")) {
                    visited[i][j] = true;
                }
                if (Objects.equals(positionSplit[j], "I")) {
                    start = new int[]{j, i};
                }
            }
        }
        recursiveSolve(start[0], start[1]);

        if (answer == 0) {
            bw.write("TT");
            bw.flush();
        } else {
            bw.write(String.valueOf(answer));
            bw.flush();
        }
        bw.close();
    }

    private static void recursiveSolve(int x, int y) {
        visited[y][x] = true;
        if (Objects.equals(mapView[y][x], "P")) {
            answer++;
        }

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                if (!visited[newY][newX]) {
                    recursiveSolve(newX, newY);
                }
            }
        }
    }
}