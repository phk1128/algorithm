import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[][] directions;
    private static String[] colors;
    private static int n;
    private static int answer1; // 정상
    private static int answer2; // 색약

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        String[][] mapView1 = new String[n][n]; // 정상
        String[][] mapView2 = new String[n][n]; // 색약

        boolean[][] visited1 = new boolean[n][n];
        boolean[][] visited2 = new boolean[n][n];

        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        colors = new String[]{"R", "G", "B"};
        answer1 = 0; // 정상
        answer2 = 0; // 색약

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String[] view = st.nextToken().split("");
            for (int j = 0; j < n; j++) {
                mapView1[i][j] = view[j]; // 정상
                mapView2[i][j] = view[j];
                if (Objects.equals(view[j], "G")) {
                    mapView2[i][j] = "R";
                }
            }
        }

        for (String color : colors) {
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (!visited1[y][x] && Objects.equals(color, mapView1[y][x])) {
                        recursiveSolve(color, x, y, mapView1, visited1);
                        answer1++;
                    }

                    if (!visited2[y][x] && Objects.equals(color, mapView2[y][x])) {
                        recursiveSolve(color, x, y, mapView2, visited2);
                        answer2++;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        sb.append(answer1);
        sb.append(" ");
        sb.append(answer2);

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    private static void recursiveSolve(String color, int x, int y, String[][] mapView, boolean[][] visited) {

        visited[y][x] = true;

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (newX >= 0 && newX < n && newY >= 0 && newY < n) {
                if (!visited[newY][newX] && Objects.equals(color, mapView[newY][newX])) {
                    recursiveSolve(color, newX, newY, mapView, visited);
                }
            }
        }
    }
}