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
// 구슬을 하나씩 추가하며 방문 배열에 방문처리한다.

import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int limitN;
    private static int[][] mapView;
    private static int[][] rotateMapView;
    private static int[][] blizzardD;
    private static int sharkR;
    private static int sharkD;
    private static Queue<Integer> marblesGroup;
    private static Queue<Integer>[] marblesPosition;
    private static int[] bomb;
    private static int[][] rotateD;
    private static boolean bombFlag;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        bomb = new int[4];
        rotateD = new int[][]{{0, -1}, {1, 0}};
        rotateMapView = new int[N * N][2];
        limitN = N * N;
        blizzardD = new int[][]{{0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        sharkR = (N + 1) / 2;
        sharkD = (N + 1) / 2;
        mapView = new int[N + 1][N + 1];
        rotateMapView[0] = new int[]{sharkR, sharkD};

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
            marblesGroup = new ArrayDeque<>();
            marblesPosition = new ArrayDeque[4];
            bombFlag = true;

            for (int j = 0; j <= 3; j++) {
                marblesPosition[j] = new ArrayDeque<>();
            }

            blizzard(d, s);
            rotateShift();

            while (bombFlag) {
                rotateBomb();
                rotateShift();
            }

            generateMarblesGroup();
            marblesUpdate();
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

    private static void marblesUpdate() {

        int start = 1;
        while (start <= limitN - 1) {
            int r = rotateMapView[start][0];
            int c = rotateMapView[start][1];
            if (marblesGroup.isEmpty()) {
                break;
            }
            mapView[r][c] = marblesGroup.poll();
            start++;
        }
    }

    private static void generateMarblesGroup() {

        for (int i = 1; i < limitN - 1; i++) {

            int r = rotateMapView[i][0];
            int c = rotateMapView[i][1];
            int number = mapView[r][c];
            int count = 1;

            if (number == 0) {
                return;
            }

            while (true) {

                int tmpR = rotateMapView[i + count][0];
                int tmpC = rotateMapView[i + count][1];
                int tmpNum = mapView[tmpR][tmpC];

                if (number == tmpNum) {
                    count++;
                } else {
                    break;
                }
            }
            marblesGroup.offer(count);
            marblesGroup.offer(number);
            i += (count - 1);
        }
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
            mapView[r][c] = 0;
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

                rotateMapView[num] = new int[]{r, c};
                num++;
            }
        }
        generateRotateMapView(num, r, c, -d, depth + 1);

    }

    private static void rotateShift() {

        for (int i = 2; i < limitN; i++) {
            int r = rotateMapView[i][0];
            int c = rotateMapView[i][1];
            int number = mapView[r][c];
            int count = 0;
            if (number == 0) {
                continue;
            }

            int tmpIdx = i - 1;

            while (true) {
                int tmpR = rotateMapView[tmpIdx][0];
                int tmpC = rotateMapView[tmpIdx][1];
                int tmpNumber = mapView[tmpR][tmpC];
                if (tmpNumber == 0 && tmpIdx != 0) {
                    tmpIdx--;
                    count++;
                } else {
                    tmpR = rotateMapView[i - count][0];
                    tmpC = rotateMapView[i - count][1];
                    mapView[r][c] = 0;
                    mapView[tmpR][tmpC] = number;
                    break;
                }
            }
        }
    }

    private static void rotateBomb() {
        bombFlag = false;
        int number = 0;
        for (int i = 1; i < limitN; i++) {

            int r = rotateMapView[i][0];
            int c = rotateMapView[i][1];
            int tmpNum = mapView[r][c];

            marblesPosition[tmpNum].offer(i);
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

        Queue<Integer> positions = marblesPosition[num];
        if (positions.size() >= 4) {
            while (!positions.isEmpty()) {
                int position = positions.poll();
                int r = rotateMapView[position][0];
                int c = rotateMapView[position][1];
                mapView[r][c] = 0;
                bomb[num]++;
            }
            bombFlag = true;
        } else {
            positions.clear();
        }
    }
}



