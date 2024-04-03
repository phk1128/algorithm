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

물고기 객체 -> mapView로 사용한다.
상어 객체 -> 이동 마다 상어 객체를 만들어 저장한다.
냄새 배열 -> 냄새를 카운팅한다.

물고기 복제를 위해 기존 mapView를 저장해둬야한다
상어 객체에 조건에 맞게 compareTo를 오버라이딩한다.
재귀를 통해 갈 수 있는 모든 방향을 탐색한 뒤 3칸 이동이 완료 되면, 그 동안 먹은 물고기와 방향들을 저장한다.
물고기를 복제후 기존 mapView를 copyMapView로 갱신한다.
*/

import java.util.*;
import java.io.*;

public class Main {

    static class Shark implements Comparable<Shark> {

        int r;
        int c;
        int count;
        int directions;

        public Shark(int r, int c, int count, int directions) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.directions = directions;
        }

        @Override
        public int compareTo(Shark s) {

            if (this.count != s.count) {
                return s.count - this.count;
            }

            return this.directions - s.directions;
        }

    }

    static class Fish {

        int r;
        int c;
        int d;

        public Fish(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int M;
    private static int S;
    private static int N;
    private static int[][] fishDirections;
    private static int[][] sharkDirections;
    private static List<Shark> sharks;
    private static int[] sharkDirectionLog;
    private static int[][] mapView;
    private static Queue<Fish>[][] movedFishes;
    private static List<Fish> fishes;
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

        mapView = new int[N + 1][N + 1];
        fishes = new ArrayList<>();
        sharks = new ArrayList<>();
        movedFishes = new ArrayDeque[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                movedFishes[r][c] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fishes.add(new Fish(r, c, d - 1));
            mapView[r][c]++;
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
                answer += mapView[r][c];
            }
        }

        return answer;
    }

    private static void solve() {

        moveFish();

        moveShark(sharkR, sharkC, 0, 0);

        update();

        reduceSmell();

        copyFish();

        addFish();
    }

    private static void addFish() {

        fishes.clear();
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                Queue<Fish> fishQueue = movedFishes[r][c];
                while (!fishQueue.isEmpty()) {
                    Fish fish = fishQueue.poll();
                    fishes.add(new Fish(fish.r, fish.c, fish.d));
                }
            }
        }
    }

    private static void copyFish() {

        for (Fish fish : fishes) {
            int r = fish.r;
            int c = fish.c;
            int d = fish.d;
            mapView[r][c]++;
            movedFishes[r][c].offer(new Fish(r, c, d));
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

    private static void update() {

        Collections.sort(sharks);
        Shark shark = sharks.get(0);
        int directions = shark.directions;
        String[] strD = String.valueOf(directions).split("");
        int newR = sharkR;
        int newC = sharkC;
        for (String s : strD) {
            int d = Integer.parseInt(s);
            newR += sharkDirections[d - 1][0];
            newC += sharkDirections[d - 1][1];

            if (mapView[newR][newC] == 0) {
                continue;
            }

            movedFishes[newR][newC].clear();
            mapView[newR][newC] = 0;
            smells[newR][newC] = 3;

        }

        sharkR = shark.r;
        sharkC = shark.c;
        sharks.clear();
    }

    private static void moveShark(int sharkR, int sharkC, int count, int depth) {

        if (depth == 3) {

            String numStr = "";

            for (int num : sharkDirectionLog) {
                numStr += num;
            }

            int directions = Integer.parseInt(numStr);

            sharks.add(new Shark(sharkR, sharkC, count, directions));

            return;
        }

        for (int i = 0; i < 4; i++) {

            int newR = sharkR + sharkDirections[i][0];
            int newC = sharkC + sharkDirections[i][1];

            if (!(newR >= 1 && newR <= N && newC >= 1 && newC <= N)) {
                continue;
            }
            int fishCount = mapView[newR][newC];
            mapView[newR][newC] = 0;
            sharkDirectionLog[depth] = i + 1;
            int newCount = count + fishCount;
            moveShark(newR, newC, newCount, depth + 1);
            mapView[newR][newC] = fishCount;
        }
    }

    private static void moveFish() {

        for (Fish fish : fishes) {
            int r = fish.r;
            int c = fish.c;
            int d = fish.d;
            boolean flag = false;

            for (int i = 0; i < 8; i++) {

                int newR = r + fishDirections[d][0];
                int newC = c + fishDirections[d][1];

                if (newR >= 1 && newR <= N && newC >= 1 && newC <= N) {
                    if (!(newR == sharkR && newC == sharkC)) {
                        if (smells[newR][newC] == 0) {
                            movedFishes[newR][newC].offer(new Fish(newR, newC, d));
                            mapView[newR][newC]++;
                            mapView[r][c]--;
                            flag = true;
                            break;
                        }
                    }
                }
                d = (8 + (d - 1)) % 8;
            }

            if (!flag) {
                movedFishes[fish.r][fish.c].offer(new Fish(fish.r, fish.c, fish.d));
            }
        }
    }
}
