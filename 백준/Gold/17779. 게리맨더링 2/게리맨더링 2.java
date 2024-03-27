import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int[][] mapView;
    private static int[][] area;
    private static int[] peopleSum;
    private static int[] combi;
    private static int min;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        mapView = new int[N + 1][N + 1];
        combi = new int[4]; // 0 : r , 1: c , 2 : d1 , 3 : d2
        min = Integer.MAX_VALUE;

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                mapView[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        recursiveSolve(0);
        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(int depth) {

        if (depth == 4) {
            int r = combi[0];
            int c = combi[1];
            int d1 = combi[2];
            int d2 = combi[3];

            if (r <= r + d1 + d2 && r + d1 + d2 <= N) {
                if (c - d1 >= 1 && c > c - d1 && c < c + d2 && c + d2 <= N) {
                    area = new int[N + 1][N + 1];
                    peopleSum = new int[5];
                    generateFive(r, c, d1, d2);
                    generateAnother(r, c, d1, d2);
                    sumFiveZone();

                    Arrays.sort(peopleSum);
                    min = Math.min(min, peopleSum[4] - peopleSum[0]);
                }
            }
            return;
        }

        for (int i = 1; i <= N; i++) {
            combi[depth] = i;
            recursiveSolve(depth + 1);
        }
    }

    private static void sumFiveZone() {

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (area[r][c] == 5 || area[r][c] == 0) {
                    peopleSum[4] += mapView[r][c];
                }
            }
        }
    }

    private static void generateFive(int r, int c, int d1, int d2) {

        area[r][c] = 5;

        for (int i = 1; i <= d1; i++) {
            area[r + i][c - i] = 5;
            area[r + d2 + i][c + d2 - i] = 5;
        }

        for (int i = 1; i <= d2; i++) {
            area[r + i][c + i] = 5;
            area[r + d1 + i][c - d1 + i] = 5;
        }
    }

    private static void generateAnother(int r, int c, int d1, int d2) {

        for (int i = 1; i < r + d1; i++) {
            for (int j = 1; j <= c; j++) {
                if (area[i][j] == 5) {
                    break;
                }
                peopleSum[0] += mapView[i][j];
                area[i][j] = 1;
            }
        }

        for (int i = 1; i <= r + d2; i++) {
            for (int j = N; j > c; j--) {
                if (area[i][j] == 5) {
                    break;
                }
                peopleSum[1] += mapView[i][j];
                area[i][j] = 2;
            }
        }

        for (int i = r + d1; i <= N; i++) {
            for (int j = 1; j < c - d1 + d2; j++) {
                if (area[i][j] == 5) {
                    break;
                }
                peopleSum[2] += mapView[i][j];
                area[i][j] = 3;
            }
        }

        for (int i = N; i > r + d2; i--) {
            for (int j = N; j >= c - d1 + d2; j--) {
                if (area[i][j] == 5) {
                    break;
                }
                peopleSum[3] += mapView[i][j];
                area[i][j] = 4;
            }
        }
    }
}