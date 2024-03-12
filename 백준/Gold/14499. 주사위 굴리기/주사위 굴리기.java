import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[] dice;
    private static int[][] mapView;
    private static int[] commands;
    private static int[][] directions;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dice = new int[6];
        mapView = new int[N][M];
        commands = new int[K];
        directions = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int number = Integer.parseInt(st.nextToken());
                mapView[i][j] = number;
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(solve(r, c, commands));
        bw.flush();
        bw.close();
    }

    private static String solve(int r, int c, int[] commands) {
        int tmpNumber = 0;
        StringBuilder sb = new StringBuilder();
        for (int command : commands) {
            int nr = r + directions[command - 1][0];
            int nc = c + directions[command - 1][1];

            if (!(nr >= 0 && nr < N && nc >= 0 && nc < M)) {
                continue;
            }

            r = nr;
            c = nc;

            tmpNumber = dice[5];
            if (command == 1) {
                dice[5] = dice[2];
                dice[2] = dice[0];
                dice[0] = dice[3];
                dice[3] = tmpNumber;
            }

            if (command == 2) {
                dice[5] = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[2];
                dice[2] = tmpNumber;
            }

            if (command == 3) {
                dice[5] = dice[1];
                dice[1] = dice[0];
                dice[0] = dice[4];
                dice[4] = tmpNumber;
            }

            if (command == 4) {
                dice[5] = dice[4];
                dice[4] = dice[0];
                dice[0] = dice[1];
                dice[1] = tmpNumber;
            }

            if (mapView[r][c] == 0) {
                mapView[r][c] = dice[5];
            } else {
                dice[5] = mapView[r][c];
                mapView[r][c] = 0;
            }

            sb.append(dice[0]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
