import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static StringTokenizer st;
    private static int[][] directions;
    private static int n;
    private static int normalCount;
    private static int weakCount;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        String[][] normalMap = new String[n][n];
        String[][] colorWeakMap = new String[n][n];

        boolean[][] visitedNormal = new boolean[n][n];
        boolean[][] visitedWeak = new boolean[n][n];

        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        normalCount = 0;
        weakCount = 0;

        for (int i = 0; i < n; i++) {
            String[] row = br.readLine().split("");
            for (int j = 0; j < n; j++) {
                normalMap[i][j] = row[j];
                colorWeakMap[i][j] = row[j];
                if (row[j].equals("G")) {
                    colorWeakMap[i][j] = "R";
                }
            }
        }

        String[] colors = {"R", "G", "B"};

        for (String color : colors) {
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (!visitedNormal[y][x] && normalMap[y][x].equals(color)) {
                        dfs(color, x, y, normalMap, visitedNormal);
                        normalCount++;
                    }
                    if (!visitedWeak[y][x] && colorWeakMap[y][x].equals(color)) {
                        dfs(color, x, y, colorWeakMap, visitedWeak);
                        weakCount++;
                    }
                }
            }
        }

        System.out.println(normalCount + " " + weakCount);
    }

    private static void dfs(String color, int x, int y, String[][] map, boolean[][] visited) {
        visited[y][x] = true;

        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                if (!visited[ny][nx] && map[ny][nx].equals(color)) {
                    dfs(color, nx, ny, map, visited);
                }
            }
        }
    }
}
