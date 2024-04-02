// 1. 비바라기를 시전하면 (N,1) / (N,2) / (N-1, 1) / (N-1, 2)에 비구름이 생긴다.
// 2. 방향은 총 8가지가 있고 1 ~ 8 정수로 표현 ←, ↖, ↑, ↗, →, ↘, ↓, ↙
// 3. 구름은 다음과 같은 규칙으로 이동
// 1) 모든 구름은 d방향으로 s칸 이동한다.
// 2) 각 구름에서 비가 내려 구름이 있는 칸의 물의 양이 1 증가한다.
// 3) 구름이 모두 사라진다.
// 4) "물이 증가한 칸"에 대각선 방향으로 거리가 1인 칸에 물이 있는 칸의 개수 만큼 물의 양이 증가한다.
// 5) 대각선의 방향으로 거리가 1인 칸은 경계를 넘어서지 않는 곳이어야 한다.
// 6) 즉, 구름은 경계를 넘어 이동할 수 있으나, 대각선 방향의 칸을 셀때는 경계를 넘어가면 안된다.
// 7) 모든 격자에서 물의 양이 2 이상인 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3)의 구름이 사라진 칸이 아니어야 한다.
// 4. M번의 이동이 모두 끝난 후 맵 전체의 물의 양을 구해보자

// 구름 객체, 구름들을 저장
// 저장된 구름들을 명령에 맞게 이동 수행
// 이동된 곳은 방문 처리

import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int[][] mapView;
    private static int[][] tmpMapView;
    private static boolean[][] visited;
    private static Queue<Cloud> clouds;
    private static Queue<Cloud> movedClouds;
    private static int[][] directions;
    private static int[][] commandD;

    static class Cloud {

        int r;
        int c;

        public Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mapView = new int[N][N];
        clouds = new ArrayDeque<>();
        movedClouds = new ArrayDeque<>();
        directions = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        commandD = new int[][]{{0, 0}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                mapView[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        clouds.offer(new Cloud(N - 1, 0));
        clouds.offer(new Cloud(N - 1, 1));
        clouds.offer(new Cloud(N - 2, 0));
        clouds.offer(new Cloud(N - 2, 1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            solve(d, s);
        }

        bw.write(String.valueOf(getAnswer()));
        bw.flush();
        bw.close();
    }

    private static int getAnswer() {
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                answer += mapView[r][c];
            }
        }
        return answer;
    }

    private static void solve(int d, int s) {

        visited = new boolean[N][N];

        while (!clouds.isEmpty()) {
            Cloud cloud = clouds.poll();
            int r = cloud.r;
            int c = cloud.c;

            r += commandD[d][0] * s;
            c += commandD[d][1] * s;

            r %= N;
            c %= N;

            if (r < 0) {
                r += N;
            }
            if (c < 0) {
                c += N;
            }
            visited[r][c] = true;
            mapView[r][c]++;
            movedClouds.offer(new Cloud(r, c));
        }

        increase();
        mapView = arrCopyOf(tmpMapView);
        generateCloud();
    }

    private static void generateCloud() {

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!visited[r][c] && mapView[r][c] >= 2) {
                    int newWater = mapView[r][c] - 2;
                    mapView[r][c] = newWater;
                    clouds.offer(new Cloud(r, c));
                }
            }
        }
    }

    private static int[][] arrCopyOf(int[][] arr) {

        int[][] tmpArr = new int[N][N];

        for (int i = 0; i < N; i++) {
            tmpArr[i] = Arrays.copyOf(arr[i], N);
        }
        return tmpArr;
    }


    private static void increase() {

        tmpMapView = arrCopyOf(mapView);

        while (!movedClouds.isEmpty()) {
            Cloud cloud = movedClouds.poll();
            int r = cloud.r;
            int c = cloud.c;
            int count = 0;
            for (int[] direction : directions) {
                int newR = r + direction[0];
                int newC = c + direction[1];

                if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
                    continue;
                }

                if (mapView[newR][newC] > 0) {
                    count++;
                }
            }
            tmpMapView[r][c] += count;
        }
    }
}
