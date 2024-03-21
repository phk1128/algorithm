import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int N;
    private static int M;
    private static int emptyZone;
    private static int[][] mapView;
    private static List<Virus> virusGroup;
    private static Virus[] combi;
    private static boolean[][] visited;
    private static int[][] directions;
    private static int min;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mapView = new int[N][N];
        combi = new Virus[M];
        virusGroup = new ArrayList<>();
        emptyZone = 0;
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        min = Integer.MAX_VALUE;

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                int number = Integer.parseInt(st.nextToken());
                mapView[r][c] = number;

                if (number == 0) {
                    emptyZone++;
                }

                if (number == 2) {
                    virusGroup.add(new Virus(r, c, 0));
                }
            }
        }

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
            diffusion(emptyZone);
            return;
        }

        for (int i = start; i < virusGroup.size(); i++) {
            combi[depth] = virusGroup.get(i);
            recursiveSolve(i + 1, depth + 1);
        }
    }

    private static void diffusion(int count) {
        Queue<Virus> queue = new ArrayDeque<>();
        visited = new boolean[N][N];
        for (Virus virus : combi) {
            queue.offer(virus);
            visited[virus.r][virus.c] = true;
        }

        while (!queue.isEmpty()) {
            Virus virus = queue.poll();
            int r = virus.r;
            int c = virus.c;

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
                    min = Math.min(min, virus.day + 1);
                    return;
                }
                visited[newR][newC] = true;
                queue.add(new Virus(newR, newC, virus.day + 1));
            }
        }
    }

    static class Virus {

        int r;
        int c;
        int day;

        public Virus(int r, int c, int day) {
            this.r = r;
            this.c = c;
            this.day = day;
        }
    }
}