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