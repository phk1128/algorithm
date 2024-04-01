// 0. 파이어볼은 d의 방향으로 s(속력)칸 이동
// 1. 1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다. 즉, N -> 1 로 갈수 있다는 뜻
// 2. 파이어볼이 같은 칸에 있을 경우 파이어볼은 합쳐진뒤, 4개의 파이어볼로 나누어진다.
//  -> 질량 = 합쳐진 파이어볼 질량의 합 / 5
//  -> 속력 = 합쳐진 파이어볼 속력의 합 / 합쳐진 파이어볼의 개수
// 3. 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 뱡향은 0,2,4,6이 되고, 그렇지 않으면 1,3,5,7이 된다.
// 4. 질량이 0인 파이어볼은 소멸되어 없어진다.
// 5. K 번 명령한 후 , 남아있는 파이어볼 질량의 합 구하기

// - 일때 반시계 , + 시계
//int[N][N][4] 각 r,c에 {m,s,d,c} c는 해당칸의 파이볼개수
//파이어볼 그룹을 순회하면서 이동 시키기, 이동한 칸에 m,s,d 더하기
//파이어볼이 모두 이동하면 맵 전체를 돌면서 c >= 2인 곳인경우는 파이어볼 나눠주기


import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int K;
    private static int[][][] mapView;
    private static Queue<FireBall> fireBalls;
    private static Queue<Integer>[][] fireBallDirections;
    private static int[][] directions;
    private static int answer;

    static class FireBall {

        int r;
        int c;
        int m;
        int s;
        int d;

        public FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        solve();

        printAnswer();
    }

    private static void printAnswer() throws IOException {

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void solve() {

        while (K-- > 0) {
            moveFireBall();
            divideFireBall();
        }
        answer = 0;

        while (!fireBalls.isEmpty()) {
            FireBall fireBall = fireBalls.poll();
            answer += fireBall.m;
        }
    }

    private static void divideFireBall() {

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int fireCount = mapView[r][c][2];
                int m = mapView[r][c][0];
                int s = mapView[r][c][1];
                if (fireCount >= 2) {
                    int newM = m / 5;
                    int newS = s / fireCount;

                    int flagNum = fireBallDirections[r][c].peek() % 2;
                    boolean flag = true;
                    while (!fireBallDirections[r][c].isEmpty()) {
                        if (flagNum != fireBallDirections[r][c].poll() % 2) {
                            flag = false;
                        }
                    }

                    if (newM != 0) {
                        if (flag) {
                            for (int newD : new int[]{0, 2, 4, 6}) {
                                fireBalls.offer(new FireBall(r, c, newM, newS, newD));
                            }
                        } else {
                            for (int newD : new int[]{1, 3, 5, 7}) {
                                fireBalls.offer(new FireBall(r, c, newM, newS, newD));
                            }
                        }
                    }
                }
                if (fireCount == 1) {
                    fireBalls.offer(new FireBall(r, c, m, s, fireBallDirections[r][c].poll()));
                }
                mapView[r][c][0] = 0;
                mapView[r][c][1] = 0;
                mapView[r][c][2] = 0;
            }
        }
    }

    private static void moveFireBall() {

        while (!fireBalls.isEmpty()) {
            FireBall fireBall = fireBalls.poll();
            int r = fireBall.r;
            int c = fireBall.c;
            int m = fireBall.m;
            int s = fireBall.s;
            int d = fireBall.d;

            r += directions[d][0] * s;
            c += directions[d][1] * s;
            r %= N;
            c %= N;

            if (r < 0) {
                r += N;
            }

            if (c < 0) {
                c += N;
            }

            mapView[r][c][0] += m;
            mapView[r][c][1] += s;
            mapView[r][c][2] += 1;
            fireBallDirections[r][c].offer(d);
        }
    }


    private static void init() throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fireBalls = new ArrayDeque<>();
        mapView = new int[N][N][3];
        fireBallDirections = new ArrayDeque[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                fireBallDirections[r][c] = new ArrayDeque<>();
            }
        }
        directions = new int[][]{{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireBalls.offer(new FireBall(r - 1, c - 1, m, s, d));
        }
    }
}

