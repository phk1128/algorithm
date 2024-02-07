import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static boolean[][] visited;
    private static int[][] directions;
    private static int n;
    private static int mapCount;
    private static List<Integer> result;
    private static StringBuilder sb;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        visited = new boolean[n][n];
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        int i = -1;
        while (i++ < n - 1) {
            st = new StringTokenizer(br.readLine());
            String map = st.nextToken();
            for (int j = 0; j < n; j++) {
                int point = Integer.parseInt(map.split("")[j]);
                if (point == 1) {
                    visited[i][j] = true;
                    continue;
                }
                visited[i][j] = false;
            }
        }
        mapCount = 0;
        result = new ArrayList<>();
        sb = new StringBuilder();

        for (int k = 0; k < n; k++) {
            for (int l = 0; l < n; l++) {
                if (visited[k][l]) {
                    visited[k][l] = false;
                    result.add(dfs(k, l, 1));
                    mapCount++;
                }
            }
        }
        Collections.sort(result);
        sb.append(mapCount);
        sb.append("\n");

        for (int r : result) {
            sb.append(r);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

    private static int dfs(int y, int x, int count) {
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (newX >= 0 && newX < n && newY >= 0 && newY < n) {
                if (visited[newY][newX]) {
                    visited[newY][newX] = false;
                    count = dfs(newY, newX, count + 1);
                }
            }
        }
        return count;
    }
}