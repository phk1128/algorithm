// 물고기의 방향은 상하좌우, 대각선 총 8 가지
// (0,0) 물고기를 먹고 시작하고, 처음 방향은 (0,0)에 있던 물고기의 방향이 된다.
// 물고기는 번호가 작은 물고기부터 순서대로 이동
// 이동가능 : 빈칸, 다른 물고기가 있는칸 / 이동불가능 : 상어가 있는칸
// **물고기는 이동할 수 있는 칸을 향할때 까지 45도 반시계 회전 시킨다.
// 만약 이동할 수 있는 칸이 다른 물고기가 있는 칸이라면 그 물고기와 "서로" 위치를 바꾼다.
// 이동할 수 있는 칸이 없으면 이동하지 않는다.
// ** 물고기의 이동이 끝나면 상어가 이동
// ** 상어는 여러칸을 이동할 수 있다.
// 이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다.
// 물고기가 없는 칸으로는 이동할 수 없다.
// 상어가 이동할 수 없으면 게임 종료

// 방향 배열
// Fish객체로 맵을 표현 -> 번호, 방향(인덱스)
// 물고기 이동 전, 이동 후 맵 두가지 필요
// 물고기 이동 시키는 함수 필요


import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[][][] mapView;
    private static List<Fish> fishGroup;
    private static int[][] directions;
    private static int max;

    private static int N = 4;

    static class Fish {

        int number;
        int d;
        int r;
        int c;

        public Fish(int number, int d, int r, int c) {
            this.number = number;
            this.d = d;
            this.r = r;
            this.c = c;
        }

        public void updateD(int d) {
            this.d = d;
        }

        public void updateR(int r) {
            this.r = r;
        }

        public void updateC(int c) {
            this.c = c;
        }

        public void updateNumber(int number) {
            this.number = number;
        }

    }

    public static void main(String[] args) throws IOException {

        init();

        solve();

        printResult();

    }

    private static void printResult() throws IOException {

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();

    }

    private static void solve() {

        int number = mapView[0][0][0];
        int d = mapView[0][0][1];
        mapView[0][0][0] = 0;
        mapView[0][0][1] = 0;
        fishGroup.get(number - 1).updateNumber(0);

        moveShark(0, 0, d, number);

    }

    private static void moveShark(int sharkR, int sharkC, int sharkD, int count) {

        moveFish(sharkR, sharkC);

        // 기존 물고기 그룹, 물고기 위치 저장해놓기
        List<Fish> tmpFishGroup = listCopyOf(fishGroup);
        int[][][] tmpMapView = arrCopyOf(mapView);

        for (int i = 1; i < N; i++) {
            int newR = sharkR + (directions[sharkD][0] * i);
            int newC = sharkC + (directions[sharkD][1] * i);

            if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
                continue;
            }

            if (mapView[newR][newC][0] == 0) {
                continue;
            }

            int fishNumber = mapView[newR][newC][0];
            int fishD = mapView[newR][newC][1];
            mapView[newR][newC][0] = 0;
            mapView[newR][newC][1] = 0;
            fishGroup.get(fishNumber - 1).updateNumber(0);

            moveShark(newR, newC, fishD, count + fishNumber);

            //물고기 위치, 물고기 그룹 원래대로 되돌리기
            mapView = arrCopyOf(tmpMapView);
            fishGroup = listCopyOf(tmpFishGroup);
        }

        max = Math.max(max, count);
    }

    private static List<Fish> listCopyOf(List<Fish> list) {

        List<Fish> tmpList = new ArrayList<>();

        for (Fish fish : list) {
            int number = fish.number;
            int d = fish.d;
            int r = fish.r;
            int c = fish.c;

            tmpList.add(new Fish(number, d, r, c));
        }

        return tmpList;
    }

    private static int[][][] arrCopyOf(int[][][] arr) {
        int[][][] tmpArr = new int[N][N][2];

        for (int i = 0; i < N; i++) {
            tmpArr[i] = new int[N][2];
            for (int j = 0; j < N; j++) {
                tmpArr[i][j] = Arrays.copyOf(arr[i][j], 2);
            }
        }
        return tmpArr;
    }


    private static void moveFish(int sharkR, int sharkC) {

        for (Fish fish : fishGroup) {
            int d = fish.d;
            int r = fish.r;
            int c = fish.c;

            if (fish.number == 0) {
                continue;
            }

            for (int i = 0; i < 8; i++) {
                int newD = (d + i) % 8;
                int newR = r + directions[newD][0];
                int newC = c + directions[newD][1];

                if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
                    continue;
                }

                if (newR == sharkR && newC == sharkC) {
                    continue;
                }

                //물고기 자리 바꾸기
                int nextFishNumber = mapView[newR][newC][0];
                int nextFishD = mapView[newR][newC][1];

                fish.updateD(newD);
                fish.updateR(newR);
                fish.updateC(newC);
                if (nextFishNumber != 0) {
                    fishGroup.get(nextFishNumber - 1).updateR(r);
                    fishGroup.get(nextFishNumber - 1).updateC(c);
                }
                mapView[newR][newC][0] = fish.number;
                mapView[newR][newC][1] = fish.d;
                mapView[r][c][0] = nextFishNumber;
                mapView[r][c][1] = nextFishD;

                break;
            }

        }

    }

    private static void init() throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        mapView = new int[N][N][2];
        fishGroup = new ArrayList<>();
        directions = new int[][]{{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
        max = 0;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int number = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                Fish fish = new Fish(number, d - 1, r, c);
                mapView[r][c][0] = number;
                mapView[r][c][1] = d - 1;
                fishGroup.add(fish);
            }
        }
        Collections.sort(fishGroup, (f1, f2) -> (f1.number - f2.number));
    }
}