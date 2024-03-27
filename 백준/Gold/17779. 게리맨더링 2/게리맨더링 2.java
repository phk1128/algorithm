import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int[][] mapView;
    private static int[][] area;
    private static int[] combi;
    private static int min;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        mapView = new int[N + 1][N + 1];
        area = new int[N + 1][N + 1];
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
                    generateFive(r, c, d1, d2);
                    generateAnother(r, c, d1, d2);
                    min = Math.min(min, calculateDiff());
                    area = new int[N + 1][N + 1];
                }
            }
            return;
        }

        for (int i = 1; i <= N; i++) {
            combi[depth] = i;
            recursiveSolve(depth + 1);
        }
    }

    private static int calculateDiff() {

        int tmpMin = Integer.MAX_VALUE;
        int tmpMax = 0;

        for (int i = 1; i <= 5; i++) {
            int sum = 0;
            for (int r = 1; r <= N; r++) {
                for (int c = 1; c <= N; c++) {
                    if (area[r][c] == i) {
                        sum += mapView[r][c];
                    }
                }
            }
            tmpMin = Math.min(tmpMin, sum);
            tmpMax = Math.max(tmpMax, sum);
        }

        return Math.abs(tmpMax - tmpMin);
    }

    private static void generateFive(int r, int c, int d1, int d2) {

        int newR = r;
        int newC = c;
        while (newR <= r + d1 && newC >= c - d1) {
            area[newR][newC] = 5;
            newR++;
            newC--;
        }

        newR = r;
        newC = c;
        while (newR <= r + d2 && newC <= c + d2) {
            area[newR][newC] = 5;
            newR++;
            newC++;
        }

        newR = r + d1;
        newC = c - d1;
        while (newR <= r + d1 + d2 && newC <= c - d1 + d2) {
            area[newR][newC] = 5;
            newR++;
            newC++;
        }

        newR = r + d2;
        newC = c + d2;
        while (newR <= r + d2 + d1 && newC >= c + d2 - d1) {
            area[newR][newC] = 5;
            newR++;
            newC--;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (area[i][j] == 5) {
                    int tmpC = j + 1;
                    boolean flag = false;
                    while (tmpC <= N) {
                        if (area[i][tmpC] == 5) {
                            flag = true;
                            break;
                        }
                        tmpC++;
                    }

                    if (flag) {
                        tmpC = j + 1;
                        while (area[i][tmpC] != 5) {
                            area[i][tmpC] = 5;
                            tmpC++;
                        }
                    }
                }
            }
        }
    }

    private static void generateAnother(int r, int c, int d1, int d2) {

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {

                if (i >= 1 && i <= r + d1 && j >= 1 && j <= c) {
                    if (area[i][j] != 5) {
                        area[i][j] = 1;
                    }
                }

                if (i >= 1 && i <= r + d2 && j > c && j <= N) {
                    if (area[i][j] != 5) {
                        area[i][j] = 2;
                    }
                }

                if (i >= r + d1 && i <= N && j >= 1 && j < c - d1 + d2) {
                    if (area[i][j] != 5) {
                        area[i][j] = 3;
                    }
                }

                if (i > r + d2 && i <= N && j >= c - d1 + d2 && j <= N) {
                    if (area[i][j] != 5) {
                        area[i][j] = 4;
                    }
                }
            }
        }
    }
}