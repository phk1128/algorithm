import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int[] mapView;
    private static boolean[] visited;
    private static int answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[101];

        mapView = new int[101];
        for (int i = 1; i <= 100; i++) {
            mapView[i] = i;
        }

        for (int i = 1; i <= n + m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            mapView[start] = end;
        }

        bfsSolve();

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void bfsSolve() {
        PriorityQueue<SnakeGame> queue = new PriorityQueue<>();
        queue.offer(new SnakeGame(1, 0));
        while (!queue.isEmpty()) {
            SnakeGame snakeGame = queue.poll();
            int number = snakeGame.getNumber();
            int count = snakeGame.getCount();
            if (number == 100) {
                answer = count;
                return;
            }
            while (mapView[number] != number && number != 1) {
                number = mapView[number];
            }
            for (int i = 1; i <= 6; i++) {
                int newNumber = number + i;
                if (newNumber <= 100) {
                    if (!visited[newNumber]) {
                        visited[newNumber] = true;
                        queue.offer(new SnakeGame(newNumber, count + 1));
                    }
                }
            }
        }
    }

    static class SnakeGame implements Comparable<SnakeGame> {

        private int number;
        private int count;

        public SnakeGame(int number, int count) {
            this.number = number;
            this.count = count;
        }

        public int getNumber() {
            return number;
        }

        public int getCount() {
            return count;
        }

        @Override
        public int compareTo(SnakeGame s) {
            return this.count - s.count;
        }
    }
}