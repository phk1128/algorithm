import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static int R;
    private static int C;
    private static String[][] mapView;
    private static int[][] ds;
    private static Map<String, Boolean> visited;
    private static int max;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        mapView = new String[R + 1][C + 1];
        ds = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
        visited = new HashMap<>();
        max = Integer.MIN_VALUE;

        for (int r = 1; r <= R; r++) {
            st = new StringTokenizer(br.readLine());
            String[] inputs = st.nextToken().split("");
            for (int c = 1; c <= C; c++ ) {
                mapView[r][c] = inputs[c -1];
            }
        }
        recursiveSolve(1,1 ,1);
        bw.flush();
        bw.write(String.valueOf(max));
        bw.close();
    }

    private static void recursiveSolve(int cR, int cC, int count) {

        max = Math.max(max, count);
        visited.put(mapView[cR][cC], true);

        for (int[] d : ds) {
            int nR = cR  + d[0];
            int nC = cC + d[1];

            if (!(nR >= 1 && nR <= R && nC >= 1 && nC <= C)) {
                continue;
            }

            if (visited.getOrDefault(mapView[nR][nC], false)) {
                continue;
            }

            recursiveSolve(nR, nC, count + 1);
            visited.put(mapView[nR][nC], false);
        }
    }
}
