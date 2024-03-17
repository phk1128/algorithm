import java.util.*;
import java.io.*;


public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int L;
    private static int[][] mapView;
    private static boolean[][] visited;
    private static int answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        mapView = new int[N][N];
        answer = 0;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                mapView[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            visited = new boolean[N][N];
            solveR(i);
            visited = new boolean[N][N];
            solveC(i);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void solveR(int idx) {
        int count = 1;
        for (int r = 0; r < N - 1; r++) {
            int diff = mapView[r][idx] - mapView[r + 1][idx];
            if (diff == 0) {
                count++;
                continue;
            }
            if (diff == 1) {
                if ((r + L) >= N) {
                    return;
                }
                for (int i = 0; i < L; i++) {
                    if (mapView[r + 1][idx] != mapView[r + 1 + i][idx]) {
                        return;
                    }
                    count++;
                    visited[r + 1 + i][idx] = true;
                }
                r += (L - 1);
            }
            if (diff == -1) {
                if (count < L) {
                    return;
                }

                for (int i = 0; i < L; i++) {
                    if (visited[r - i][idx]) {
                        return;
                    }
                    visited[r - i][idx] = true;
                }

                count++;
            }
        }
        if (count == N) {
            answer++;
        }
    }

    private static void solveC(int idx) {
        int count = 1;
        for (int c = 0; c < N - 1; c++) {
            int diff = mapView[idx][c] - mapView[idx][c + 1];
            if (diff == 0) {
                count++;
                continue;
            }
            if (diff == 1) {
                if ((c + L) >= N) {
                    return;
                }
                for (int i = 0; i < L; i++) {
                    if (mapView[idx][c + 1] != mapView[idx][c + 1 + i]) {
                        return;
                    }
                    count++;
                    visited[idx][c + 1 + i] = true;
                }
                c += (L - 1);
            }
            if (diff == -1) {
                if (count < L) {
                    return;
                }
                for (int i = 0; i < L; i++) {
                    if (visited[idx][c - i]) {
                        return;
                    }
                    visited[idx][c - i] = true;
                }
                count++;
            }
        }
        if (count == N) {
            answer++;
        }
    }
}
