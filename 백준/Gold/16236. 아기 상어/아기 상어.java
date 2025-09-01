import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static StringTokenizer st;
    private static int[][] mapView;
    private static boolean[][] visited;
    private static int[][] directions;
    private static int n;
    private static int answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        mapView = new int[n][n];
        directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        BabyShark babyShark = null;
        answer = 0;

        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < n; c++) {
                int number = Integer.parseInt(st.nextToken());
                mapView[r][c] = number;
                if (number == 9) {
                    babyShark = new BabyShark(r, c, 2, 0);
                    mapView[r][c] = 0;
                }
            }
        }

        recursiveSolve(babyShark);

        System.out.println(answer);
    }

    private static void recursiveSolve(BabyShark babyShark) {
        int[] fishPosition = searchFish(babyShark);
        int r = fishPosition[0];
        int c = fishPosition[1];
        int time = fishPosition[2];
        if (!isFish(r, c, time)) {
            return;
        }

        answer += time;
        int size = babyShark.getSize();
        int newCount = babyShark.getCount() + 1;
        if (newCount >= size) {
            size++;
            newCount = 0;
        }
        mapView[r][c] = 0;
        BabyShark newBabyShark = new BabyShark(r, c, size, newCount);
        recursiveSolve(newBabyShark);
    }

    private static boolean isFish(int r, int c, int time) {
        return r != Integer.MAX_VALUE &&
                c != Integer.MAX_VALUE &&
                time != Integer.MAX_VALUE;
    }

    private static int[] searchFish(BabyShark babyShark) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{babyShark.getR(), babyShark.getC(), 0});
        visited = new boolean[n][n];
        visited[babyShark.getR()][babyShark.getC()] = true;
        int size = babyShark.getSize();
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        int minTime = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] status = queue.poll();
            int r = status[0];
            int c = status[1];
            int time = status[2];
            if (mapView[r][c] < size && mapView[r][c] != 0) {
                if (minTime > time) {
                    minTime = time;
                    minR = r;
                    minC = c;
                }
                if (minTime == time) {
                    if (minR > r) {
                        minR = r;
                        minC = c;
                    }

                    if (minR == r) {
                        if (minC > c) {
                            minR = r;
                            minC = c;
                        }
                    }
                }
            }
            for (int[] direction : directions) {
                int newR = r + direction[0];
                int newC = c + direction[1];
                if (newR >= 0 && newR < n && newC >= 0 && newC < n) {
                    if (mapView[newR][newC] <= size && !visited[newR][newC]) {
                        visited[newR][newC] = true;
                        queue.offer(new int[]{newR, newC, time + 1});
                    }
                }
            }
        }
        return new int[]{minR, minC, minTime};
    }

    static class BabyShark {
        private int r;
        private int c;
        private int size;
        private int count;

        public BabyShark(int r, int c, int size, int count) {
            this.r = r;
            this.c = c;
            this.size = size;
            this.count = count;
        }

        public int getR() {
            return r;
        }

        public int getC() {
            return c;
        }

        public int getSize() {
            return size;
        }

        public int getCount() {
            return count;
        }
    }
}