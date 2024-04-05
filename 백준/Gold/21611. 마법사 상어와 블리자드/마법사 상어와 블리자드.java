// 1. N은 항상 홀수
// 2. 마법사 상어는 (N+1)/2 , (N+1)/2 위치에 있다. (중앙)
// 3. 블리자드는 4가지 방향 d와 거리 s를 가지고 있다. 상,하,좌,우 => 1,2,3,4
// 4. 상어는 d 방향으로 거리가 s이하인 모든 구슬을 파괴하여 빈칸으로 만든다.
// 5. 파괴된 구슬 자리로 이동한다. (주변은 벽을 통과 X)
// 6. 동일한 숫자의 구슬이 4개 이상 붙어 있으면 폭발한다. 또 있으면 연속으로 폭발가능
// 7. 연속하는 구슬은 하나의 그룹이 되고, 이는 구슬의 개수 A 구슬의 번호 B로 하여 두개의 구슬로 나누어진다.
// 8. 나누어진 구슬은 1번칸에서 A,B 순서대로 다시 들어간다.
// 9. 1 * 폭발한 1번구슬개수 + 2 * 폭발한 2번구슬개수 + 3 * 폭발한 3번 구슬 개수를 구하자

// 가운데에서 회전하며 맵을 조회하는 로직 필요
// 구슬 폭파 로직 필요
// 가운데부터 맵을 회전하며 구슬을 그룹화 시키고 이를 바로 A,B로 나누어 큐에 담는다.


import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int powN;
    private static int[][] mapView;
    private static int[][] rotateMapView;
    private static int[] snail;
    private static int[][] blizzardD;
    private static int sharkR;
    private static int sharkD;
    private static Queue<Integer>[] marblesBomb;
    private static int[] bomb;
    private static int[][] rotateD;
    private static boolean bombFlag;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        powN = N * N;
        bomb = new int[4];
        rotateD = new int[][]{{0, -1}, {1, 0}};
        rotateMapView = new int[N + 1][N + 1];
        snail = new int[powN];
        blizzardD = new int[][]{{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        sharkR = (N + 1) / 2;
        sharkD = (N + 1) / 2;
        mapView = new int[N + 1][N + 1];
        rotateMapView[0] = new int[]{sharkR, sharkD};
        marblesBomb = new ArrayDeque[4];

        for (int i = 0; i < 4; i++) {
            marblesBomb[i] = new ArrayDeque<>();
        }

        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                mapView[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        generateRotateMapView(1, sharkR, sharkD, 1, 1);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            bombFlag = true;

            blizzard(d, s);
            rotateShift();

            while (bombFlag) {
                rotateBomb();
                rotateShift();
            }
            generateMarblesGroup();
        }

        bw.write(String.valueOf(getAnswer()));
        bw.flush();
        bw.close();
    }

    private static int getAnswer() {

        int answer = 0;
        answer += bomb[1] * 1;
        answer += bomb[2] * 2;
        answer += bomb[3] * 3;

        return answer;
    }

    private static void generateMarblesGroup() {

        int[] tmp = new int[powN];
        int idx = 1;

        for (int i = 1; i < powN; i++) {

            int number = snail[i];
            int count = 1;

            if (number == 0) {
                break;
            }

            while (true) {
                int tmpNum = snail[i + count];
                if (number == tmpNum) {
                    count++;
                } else {
                    break;
                }
            }
            tmp[idx++] = count;
            tmp[idx++] = number;
            if (idx == powN) {
                break;
            }
            i += (count - 1);
        }

        snail = tmp;
    }

    private static void blizzard(int d, int s) {

        int r = sharkR;
        int c = sharkD;
        for (int i = 0; i < s; i++) {
            r += blizzardD[d][0];
            c += blizzardD[d][1];

            if (!(r >= 1 && r <= N && c >= 1 && c <= N)) {
                return;
            }
            int num = rotateMapView[r][c];
            snail[num] = 0;
        }

    }

    private static void generateRotateMapView(int num, int r, int c, int d, int depth) {

        for (int[] rd : rotateD) {
            for (int i = 0; i < depth; i++) {
                r += rd[0] * d;
                c += rd[1] * d;

                if (!(r >= 1 && r <= N && c >= 1 && c <= N)) {
                    return;
                }
                snail[num] = mapView[r][c];
                rotateMapView[r][c] = num;
                num++;
            }
        }
        generateRotateMapView(num, r, c, -d, depth + 1);

    }

    private static void rotateShift() {

        // 앞으로 당기기
        int start = 1;
        for (int i = 1; i < powN; i++) {
            if (snail[i] == 0) {
                continue;
            }
            snail[start++] = snail[i];
        }

        // 앞으로 당겨진 얘들 0으로 초기화
        for (int i = start; i < powN; i++) {
            snail[i] = 0;
        }
    }

    private static void rotateBomb() {
        bombFlag = false;
        int number = 0;
        for (int i = 1; i < powN; i++) {
            int tmpNum = snail[i];
            marblesBomb[tmpNum].offer(i);
            if (tmpNum != number) {
                bombMarbles(number);
                number = tmpNum;
            }
            if (tmpNum == 0) {
                break;
            }
        }
    }

    private static void bombMarbles(int num) {

        Queue<Integer> marbles = marblesBomb[num];
        if (marbles.size() >= 4) {
            while (!marbles.isEmpty()) {
                int n = marbles.poll();
                snail[n] = 0;
                bomb[num]++;
            }
            bombFlag = true;
        } else {
            marbles.clear();
        }
    }
}