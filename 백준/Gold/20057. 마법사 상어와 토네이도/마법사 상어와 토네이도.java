import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int[][] mapView;
    private static int[][][] tornado;
    private static int[][] directions;
    private static int r;
    private static int c;
    private static int answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        tornado = new int[][][]{
                {
                        {1, 0, 7}, {-1, 0, 7}, {-1, -1, 10},
                        {1, -1, 10}, {-1, 1, 1}, {1, 1, 1},
                        {-2, 0, 2}, {2, 0, 2}, {0, -2, 5}
                },
                {
                        {0, -1, 7}, {0, 1, 7}, {1, -1, 10},
                        {1, 1, 10}, {-1, -1, 1}, {-1, 1, 1},
                        {0, -2, 2}, {0, 2, 2}, {2, 0, 5}
                },
                {
                        {1, 0, 7}, {-1, 0, 7}, {-1, 1, 10},
                        {1, 1, 10}, {-1, -1, 1}, {1, -1, 1},
                        {-2, 0, 2}, {2, 0, 2}, {0, 2, 5}
                },
                {
                        {0, 1, 7}, {0, -1, 7}, {-1, -1, 10},
                        {-1, 1, 10}, {1, -1, 1}, {1, 1, 1},
                        {0, -2, 2}, {0, 2, 2}, {-2, 0, 5}
                }
        };

        directions = new int[][]{{0, -1}, {1, 0}};
        mapView = new int[N][N];
        answer = 0;
        r = (N - 1) / 2;
        c = (N - 1) / 2;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                mapView[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        recursiveSolve(1, 1, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(int count, int d, int td) {
        for (int[] direction : directions) {
            for (int i = 0; i < count; i++) {
                r += (direction[0] * d);
                c += (direction[1] * d);
                if (!(r >= 0 && r < N && c >= 0 && c < N)) {
                    return;
                }
                int sand = mapView[r][c];
                int movedSandSum = 0;
                for (int[] t : tornado[td]) {
                    int movedSand = (sand * t[2]) / 100;
                    movedSandSum += movedSand;
                    int tmpR = (r + t[0]);
                    int tmpC = (c + t[1]);
                    if (!(tmpR >= 0 && tmpR < N && tmpC >= 0 && tmpC < N)) {
                        answer += movedSand;
                        continue;
                    }
                    mapView[tmpR][tmpC] += movedSand;
                }
                int remain = mapView[r][c] - movedSandSum;
                mapView[r][c] = 0;
                int newR = r + (direction[0] * d);
                int newC = c + (direction[1] * d);
                if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
                    answer += remain;
                    continue;
                }
                mapView[newR][newC] += remain;
            }

            td = (td + 1) % 4;
        }

        recursiveSolve(count + 1, -d, td);
    }
}