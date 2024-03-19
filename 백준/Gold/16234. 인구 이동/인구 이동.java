import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[][] mapView;
    private static int[][] visited;
    private static int N;
    private static int L;
    private static int R;
    private static int[][] directions;
    private static int lineCount;
    private static int peopleSum;
    private static int answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        mapView = new int[N][N];
        directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        answer = 0;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int number = Integer.parseInt(st.nextToken());
                mapView[r][c] = number;
            }
        }
        for (int t = 1; t <= 2000; t++) {
            visited = new int[N][N];
            boolean flag = false;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (visited[r][c] == 0) {
                        peopleSum = 0;
                        lineCount = 0;
                        checkLine(r, c);
                        if (lineCount != 1) {
                            move(peopleSum / lineCount);
                            flag = true;
                        }

                        if (lineCount == 1) {
                            visited[r][c] = -1;
                        }
                    }
                }
            }
            if (flag) {
                answer++;
            } else {
                break;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void checkLine(int r, int c) {

        visited[r][c] = 1;
        lineCount++;
        peopleSum += mapView[r][c];

        for (int[] direction : directions) {
            int newR = r + direction[0];
            int newC = c + direction[1];
            if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
                continue;
            }
            if (visited[newR][newC] == 0) {
                int diff = Math.abs(mapView[r][c] - mapView[newR][newC]);
                if (diff >= L && diff <= R) {
                    checkLine(newR, newC);
                }
            }
        }
    }

    private static void move(int people) {

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (visited[r][c] == 1) {
                    visited[r][c] = -1;
                    mapView[r][c] = people;
                }
            }
        }
    }
}