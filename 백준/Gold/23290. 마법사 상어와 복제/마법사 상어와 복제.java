/*
0. 아래 4번에서 물고기가 복제 되어 칸에 나타난다.
1. 모든 물고기가 한칸 이동한다.
    1) 상어가 있는칸, 물고기의 냄새가 있는칸, 격자의 범위를 벗어나는 칸으로 이동 불가
    2) 이동할 수 있는 칸을 향할 때까지 현재 자신의 방향에서 45도 반시계로 방향을 회전한다.
2. 상어가 연속해서 3칸이동한다.
    1) 상어는 현재칸에서 상하좌우로 인접한 칸으로 이동할 수 있다.
    2) 3칸 모두 격자에서 벗어 나지 않아야한다.
    3) 이동하는 중에 해당칸에 물고기가 있으면 물고기는 격자에서 제외된다.
    4) 제외되는 모든 물고기는 물고기 냄새를 남긴다.
    5) 가능한 이동 방법 중 제외되는 물고기의 수가 가장 많은 방법으로 이동한다.
    6) 그러한 방법이 여러가지인 경우 사전 순으로 가장 앞서는 방법을 이용한다.
3. 두 번 전 물고기의 냄새가 격자에서 사라진다. (즉, 시도횟수가 3이상일 경우에만 냄새가 사라지는 경우가 생긴다.)
4. 모든 복제된 물고기는 0에서의 위치와 방향을 그대로 갖게 된다.

* 상,좌,하,우 => 1,2,3,4 로 표현하고 이를 이어 붙여 정수를 만들고, 이를 오름차순으로 하는게 사전순이 된다.

*/

import java.util.*;
import java.io.*;

public class Main {


    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int M;
    private static int S;
    private static int N;
    private static int[][] fishDirections;
    private static int[][] sharkDirections;
    private static int[] sharkDirectionLog;
    private static int[] sharkRoute;
    private static int[][][] mapView;
    private static int[][][] copyMapView;
    private static int maxCount;
    private static int[][] smells;
    private static int sharkR;
    private static int sharkC;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        N = 4;
        //←, ↖, ↑, ↗, →, ↘, ↓, ↙
        fishDirections = new int[][]{{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
        sharkDirections = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        smells = new int[N + 1][N + 1];
        sharkDirectionLog = new int[3];
        mapView = new int[N + 1][N + 1][8];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            mapView[r][c][d - 1]++;

        }

        st = new StringTokenizer(br.readLine());
        sharkR = Integer.parseInt(st.nextToken());
        sharkC = Integer.parseInt(st.nextToken());

        while (S-- > 0) {
            solve();
        }

        bw.write(String.valueOf(getAnswer()));
        bw.flush();
        bw.close();
    }

    private static int getAnswer() {

        int answer = 0;

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                for (int d = 0; d < 8; d++) {
                    answer += mapView[r][c][d];
                }
            }
        }

        return answer;
    }

    private static void solve() {

        moveFishes();

        moveShark();

        reduceSmell();

        copyFish();

    }


    private static void copyFish() {

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                for (int d = 0; d < 8; d++) {
                    mapView[r][c][d] += copyMapView[r][c][d];
                }
            }
        }
    }

    private static void reduceSmell() {

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {

                if (smells[r][c] == 0) {
                    continue;
                }
                smells[r][c]--;
            }
        }
    }

    private static void moveShark() {

        maxCount = Integer.MIN_VALUE;
        sharkRoute = new int[3];
        searchSharkRoute(sharkR, sharkC, 0, 0);

        for (int sr : sharkRoute) {
            sharkR += sharkDirections[sr][0];
            sharkC += sharkDirections[sr][1];

            for (int i = 0; i < 8; i++) {
                if (copyMapView[sharkR][sharkC][i] > 0) {
                    copyMapView[sharkR][sharkC][i] = 0;
                    smells[sharkR][sharkC] = 3;
                }
            }
        }
    }

    private static void searchSharkRoute(int sharkR, int sharkC, int count, int depth) {

        if (depth == 3) {
            if (maxCount < count) {
                maxCount = count;
                sharkRoute = Arrays.copyOf(sharkDirectionLog, 3);
            }
            return;
        }

        for (int i = 0; i < 4; i++) {

            int newR = sharkR + sharkDirections[i][0];
            int newC = sharkC + sharkDirections[i][1];

            if (!(newR >= 1 && newR <= N && newC >= 1 && newC <= N)) {
                continue;
            }

            int[] tmpArr = Arrays.copyOf(copyMapView[newR][newC], 8);

            int newCount = count;
            for (int j = 0; j < 8; j++) {
                newCount += copyMapView[newR][newC][j];
                copyMapView[newR][newC][j] = 0;
            }
            sharkDirectionLog[depth] = i;
            searchSharkRoute(newR, newC, newCount, depth + 1);
            copyMapView[newR][newC] = tmpArr;
        }
    }

    private static void moveFishes() {

        copyMapView = new int[N + 1][N + 1][8];

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                for (int d = 0; d < 8; d++) {
                    if (mapView[r][c][d] > 0) {
                        moveFish(r, c, d);
                    }
                }
            }
        }
    }

    private static void moveFish(int r, int c, int d) {

        int newD = d;
        boolean flag = false;
        for (int i = 0; i < 8; i++) {

            int newR = r + fishDirections[newD][0];
            int newC = c + fishDirections[newD][1];

            if (newR >= 1 && newR <= N && newC >= 1 && newC <= N) {
                if (newR != sharkR || newC != sharkC) {
                    if (smells[newR][newC] == 0) {
                        copyMapView[newR][newC][newD] += mapView[r][c][d];
                        flag = true;
                        break;
                    }
                }
            }
            newD = (8 + (newD - 1)) % 8;
        }

        if (!flag) {
            copyMapView[r][c][d] += mapView[r][c][d];
        }
    }
}
