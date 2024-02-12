import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static boolean[] visited;
    private static int answer;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        visited = new boolean[100_001];
        bfsSolve(n, k);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static void bfsSolve(int n, int k) {
        PriorityQueue<Position> queue = new PriorityQueue<>();
        queue.offer(new Position(n, 0));
        visited[n] = true;
        int[] directions = new int[]{-1, 1};
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            int number = position.getNumber();
            int count = position.getCount();
            if (number == k) {
                answer = count;
                return;
            }
            for (int direction : directions) {
                offerNewNumber(number + direction, count, queue);
            }
            offerNewNumber(number * 2, count, queue);
        }
    }

    private static void offerNewNumber(int newNumber, int count, PriorityQueue<Position> queue) {
        if (newNumber >= 0 && newNumber <= 100_000) {
            if (!visited[newNumber]) {
                visited[newNumber] = true;
                queue.offer(new Position(newNumber, count + 1));
            }
        }
    }

    static class Position implements Comparable<Position> {

        private int number;
        private int count;

        public Position(int number, int count) {
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
        public int compareTo(Position p) {
            return this.count - p.count;
        }
    }
}