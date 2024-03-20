import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[][] mapView;
    private static int N;
    private static int M;
    private static List<Integer> totalVirus;
    private static int[] combi;
    private static int[][] directions;
    private static boolean[][] visited;
    private static int emptyZone;
    private static int min;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        mapView = new int[N][N];
        totalVirus = new ArrayList<>();
        directions = new int[][]{{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        emptyZone = 0;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int number = Integer.parseInt(st.nextToken());
                if (number == 2) {
                    totalVirus.add((r * N) + c);
                }
                if (number == 0) {
                    emptyZone++;
                }
                mapView[r][c] = number;
            }
        }
        combi = new int[M];
        min = Integer.MAX_VALUE;

        if (emptyZone == 0) {
            min = 0;
        } else {
            recursiveSolve(0, 0);
        }

        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(int start, int depth) {

        if (depth == M) {
            List<Integer> combiVirus = new ArrayList<>();
            for (int c : combi) {
                combiVirus.add(c);
            }
            diffusion(combiVirus, emptyZone);
            return;
        }

        for (int i = start; i < totalVirus.size(); i++) {
            combi[depth] = totalVirus.get(i);
            recursiveSolve(i + 1, depth + 1);
        }
    }

    private static void diffusion(List<Integer> combiVirus, int count) {
        int day = 0;
        visited = new boolean[N][N];
        for (int c : combiVirus) {
            visited[c / N][c % N] = true;
        }
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.offer(combiVirus);

        while (!queue.isEmpty()) {
            List<Integer> virus = queue.poll();
            List<Integer> tmpVirus = new ArrayList<>();

            for (int v : virus) {
                int r = v / N;
                int c = v % N;
                for (int[] direction : directions) {
                    int newR = r + direction[0];
                    int newC = c + direction[1];
                    if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
                        continue;
                    }
                    if (visited[newR][newC] || mapView[newR][newC] == 1) {
                        continue;
                    }
                    if (mapView[newR][newC] == 0) {
                        count--;
                    }
                    if (count == 0) {
                        min = Math.min(min, day + 1);
                        return;
                    }
                    visited[newR][newC] = true;
                    tmpVirus.add(newR * N + newC);
                }
            }
            if (!tmpVirus.isEmpty()) {
                day++;
                queue.offer(tmpVirus);
            }
        }
    }
}