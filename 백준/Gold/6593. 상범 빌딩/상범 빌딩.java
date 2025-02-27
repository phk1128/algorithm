import java.util.*;
import java.io.*;

public class Main {

    private static int[][][] mapView;
    private static int L;
    private static int R;
    private static int C;
    private static boolean[][][] visited;
    private static int[] start;
    private static int[][] ds;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ds = new int[][]{
                {-1, 0, 0},
                {1, 0, 0},
                {0, -1, 0},
                {0, 1, 0},
                {0, 0, -1},
                {0, 0, 1}
        };
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C == 0) {
                // 종료 조건
                break;
            }
            mapView = new int[L + 1][R + 1][C + 1];
            visited = new boolean[L + 1][R + 1][C + 1];
            for (int l = 1; l <= L; l++) {
                for (int r = 1; r <= R; r++) {
                    String els = br.readLine();
                    for (int c = 1; c <= C; c++) {
                        final char el = els.charAt(c - 1);
                        if (el == 'S') {
                            mapView[l][r][c] = 1;
                            start = new int[]{l, r, c};
                        } else if (el == '#') {
                            mapView[l][r][c] = -1;
                        } else if (el == 'E') {
                            mapView[l][r][c] = 2;
                        }
                    }
                }
                // 각 층 사이의 빈 줄 처리
                br.readLine();
            }

            // BFS로 최단 경로 찾기
            int result = bfs();
            if (result == -1) {
                bw.write("Trapped!\n");
            } else {
                bw.write("Escaped in " + result + " minute(s).\n");
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        // 시작점 추가 [l, r, c, 시간]
        queue.offer(new int[]{start[0], start[1], start[2], 0});
        visited[start[0]][start[1]][start[2]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int curL = current[0];
            int curR = current[1];
            int curC = current[2];
            int time = current[3];

            // 목적지에 도달한 경우
            if (mapView[curL][curR][curC] == 2) {
                return time;
            }

            // 6방향으로 이동
            for (int[] d : ds) {
                int nextL = curL + d[0];
                int nextR = curR + d[1];
                int nextC = curC + d[2];

                // 범위 체크
                if (nextL < 1 || nextL > L || nextR < 1 || nextR > R || nextC < 1 || nextC > C) {
                    continue;
                }

                // 벽이 아니고 아직 방문하지 않은 곳만 이동
                if (mapView[nextL][nextR][nextC] != -1 && !visited[nextL][nextR][nextC]) {
                    visited[nextL][nextR][nextC] = true;
                    queue.offer(new int[]{nextL, nextR, nextC, time + 1});
                }
            }
        }

        // 탈출할 수 없는 경우
        return -1;
    }
}