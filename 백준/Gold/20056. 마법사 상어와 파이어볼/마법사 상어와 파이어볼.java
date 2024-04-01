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
    private static int[][][][] mapView;
    private static List<FireBall> fireBalls;
    private static Set<FireBall> movedFireBalls;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof FireBall)) {
                return false;
            }

            FireBall fireBall = (FireBall) o;
            return Objects.equals(this.r, fireBall.r) &&
                    Objects.equals(this.c, fireBall.c);
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
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

        for (FireBall fireBall : fireBalls) {
            answer += fireBall.m;
        }
    }

    private static void divideFireBall() {

        for (FireBall fireBall : movedFireBalls) {
            int r = fireBall.r;
            int c = fireBall.c;
            int fireCount = mapView[r][c][0][2] + mapView[r][c][1][2];
            if (fireCount == 0) {
                continue;
            }
            if (fireCount >= 2) {
                int newM = (mapView[r][c][0][0] + mapView[r][c][1][0]) / 5;
                int newS = (mapView[r][c][0][1] + mapView[r][c][1][1]) / fireCount;

                if (newM == 0) {
                    continue;
                }

                if (mapView[r][c][0][2] == 0 || mapView[r][c][1][2] == 0) {
                    for (int newD : new int[]{0, 2, 4, 6}) {
                        fireBalls.add(new FireBall(r, c, newM, newS, newD));
                    }
                }
                if (mapView[r][c][0][2] != 0 && mapView[r][c][1][2] != 0) {
                    for (int newD : new int[]{1, 3, 5, 7}) {
                        fireBalls.add(new FireBall(r, c, newM, newS, newD));
                    }
                }
                continue;
            }
            fireBalls.add(new FireBall(r, c, fireBall.m, fireBall.s, fireBall.d));
        }
    }

    private static void moveFireBall() {

        mapView = new int[N][N][2][3];
        movedFireBalls = new HashSet<>();

        for (FireBall fireBall : fireBalls) {
            int r = fireBall.r;
            int c = fireBall.c;
            int m = fireBall.m;
            int s = fireBall.s;
            int d = fireBall.d;

            if (directions[d][0] < 0) {
                r = (N + (r + directions[d][0] * s)) % N;
                if (r < 0) {
                    r += N;
                }
            }

            if (directions[d][0] > 0) {
                r = (r + directions[d][0] * s) % N;
            }

            if (directions[d][1] < 0) {
                c = (N + (c + directions[d][1] * s)) % N;
                if (c < 0) {
                    c += N;
                }
            }

            if (directions[d][1] > 0) {
                for (int i = 0; i < s; i++) {
                    c = (c + directions[d][1]) % N;
                }
            }
            int idx = d % 2; // 홀수 짝수 판별
            mapView[r][c][idx][0] += m;
            mapView[r][c][idx][1] += s;
            mapView[r][c][idx][2] += 1;
            movedFireBalls.add(new FireBall(r, c, m, s, d));
        }
        fireBalls.clear();
    }


    private static void init() throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        fireBalls = new ArrayList<>();
        directions = new int[][]{{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireBalls.add(new FireBall(r - 1, c - 1, m, s, d));
        }
    }
}

