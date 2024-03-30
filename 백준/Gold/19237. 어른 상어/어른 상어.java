// 1 상어가 모두 쫓아낼수 있음
// 모든 상어는 자신의 위치에 냄새를 뿌리고, 냄새는 상어가 k번 이동하면 사라짐
// 상어가 이동할때는 냄새가 없는 칸을 우선으로하고 , 없으면 자신의 냄새가 있는 칸을 다음 순위로 한다.
// 각 상어마다 이동 우서순위가 있고, 이동한 방향이 바라보는 방향으로 갱신된다.
//  여러마리 상어가 남아 있으면, 가장 작은 번호를 가진 상어만 남고 나머지는 전부 격자 밖으로 쫓겨난다.

// 1,2,3,4 -> 상, 하 , 좌, 우
// 먼저 주변에 냄새가 없는지 탐색 (for) 없으면, 자기 냄새가 있는칸 탐색 (for)
// 격자는 smell 객체로 상어 번호와 냄새를 저장
// shark 객체로 번호와 방향을 저장
// 1번 상어만 격자에 남게 되기까지 시간을 출력한다. 단, 1000초가 넘어도 다른 상어가 격자에 남아있으면 -1을 출력한다.

import java.util.*;
import java.io.*;


public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int K;
    private static Smell[][] mapView;
    private static List<Shark> sharks;
    private static int[][] directions;
    private static int result;

    static class Smell {

        int number;
        int count;

        public Smell(int number, int count) {

            this.number = number;
            this.count = count;
        }

        public void updateCount(int count) {
            this.count = count;
        }

        public void updateNumber(int number) {
            this.number = number;
        }
    }

    static class Shark implements Comparable<Shark> {

        int number;
        int k;
        int d;
        int[][] directions;
        int r;
        int c;
        boolean status;

        public Shark(int number, int k, int r, int c) {
            this.number = number;
            this.k = k;
            this.r = r;
            this.c = c;
            this.status = true;
        }

        public void updateStatus(boolean status) {
            this.status = status;
        }

        public void updateR(int r) {
            this.r = r;
        }

        public void updateC(int c) {
            this.c = c;
        }

        public void updateD(int d) {
            this.d = d;
        }

        public void setDirections(int[][] directions) {
            this.directions = directions;
        }

        @Override
        public int compareTo(Shark s) {
            return this.number - s.number;
        }
    }


    public static void main(String[] args) throws IOException {

        init();

        solve();

        printResult();

    }

    private static void printResult() throws IOException {

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();

    }

    private static void solve() {
        // 상어 이동 -> 전체 맵 냄새 줄이기 -> 냄새 마킹
        for (int time = 1; time <= 1000; time++) {

            move();
            reduceSmellCount();
            markSmell();
            boolean flag = true;

            for (int i = 1; i < M; i++) {
                Shark shark = sharks.get(i);
                if (shark.status) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                result = time;
                return;
            }
        }
        result = -1;
    }

    // 냄새 남기기
    private static void markSmell() {

        for (Shark shark : sharks) {
            if (!shark.status) {
                continue;
            }
            int r = shark.r;
            int c = shark.c;
            Smell smell = mapView[r][c];
            if (smell.number != 0 && smell.number < shark.number) {
                shark.updateStatus(false);
                continue;
            }
            smell.updateNumber(shark.number);
            smell.updateCount(K);
        }
    }

    // 냄새 줄이기
    private static void reduceSmellCount() {

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                Smell smell = mapView[r][c];
                int count = smell.count;
                if (count > 0) {
                    smell.updateCount(count - 1);
                }
                if (count == 0) {
                    smell.updateNumber(0);
                }
            }
        }
    }

    private static void move() {

        for (Shark shark : sharks) {

            if (!isMoveEmpty(shark)) {
                moveSmellZone(shark);
            }
        }
    }

    // 냄새가 없는 곳을 탐색
    private static boolean isMoveEmpty(Shark shark) {

        int r = shark.r;
        int c = shark.c;
        int d = shark.d;

        for (int idx : shark.directions[d]) {
            int newR = r + directions[idx][0];
            int newC = c + directions[idx][1];

            if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
                continue;
            }

            Smell smell = mapView[newR][newC];
            int count = smell.count;

            if (count == 0) {
                shark.updateR(newR);
                shark.updateC(newC);
                shark.updateD(idx);
                return true;
            }
        }
        return false;
    }

    // 자신의 냄새가 있는 곳으로 이동
    private static void moveSmellZone(Shark shark) {

        int r = shark.r;
        int c = shark.c;
        int d = shark.d;

        for (int idx : shark.directions[d]) {
            int newR = r + directions[idx][0];
            int newC = c + directions[idx][1];

            if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
                continue;
            }

            Smell smell = mapView[newR][newC];

            if (shark.number == smell.number) {
                shark.updateR(newR);
                shark.updateC(newC);
                shark.updateD(idx);
                return;
            }
        }
    }

    private static void init() throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        mapView = new Smell[N][N];
        sharks = new ArrayList<>();
        directions = new int[][]{{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        result = 0;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int number = Integer.parseInt(st.nextToken());
                if (number != 0) {
                    sharks.add(new Shark(number, K, r, c));
                    mapView[r][c] = new Smell(number, K);
                    continue;
                }
                mapView[r][c] = new Smell(0, 0);
            }
        }

        Collections.sort(sharks);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int d = Integer.parseInt(st.nextToken());
            sharks.get(i).updateD(d);
        }

        for (Shark shark : sharks) {
            int[][] tmpDirections = new int[5][4];
            for (int i = 1; i <= 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    tmpDirections[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            shark.setDirections(tmpDirections);
        }
    }
}
