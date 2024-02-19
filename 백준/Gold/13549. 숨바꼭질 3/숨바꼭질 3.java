import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static int answer;
    private static boolean[] visited;

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

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, n, 0});
        visited[n] = true;

        while (!queue.isEmpty()) {

            int[] status = queue.poll();
            int time = status[0];
            int position = status[1];
            int jump = status[2];

            if (position == k) {
                answer = time - jump;
                if (answer < 0) {
                    answer = 0;
                }
                return;
            }

            int newPosition = position * 2;

            if (isMove(newPosition)) {
                if (!visited[newPosition]) {
                    visited[newPosition] = true;
                    queue.offer(new int[]{time + 1, newPosition, jump + 1});
                }
            }

            newPosition = position - 1;

            if (isMove(newPosition)) {
                if (!visited[newPosition]) {
                    visited[newPosition] = true;
                    queue.offer(new int[]{time + 1, newPosition, jump});
                }
            }

            newPosition = position + 1;

            if (isMove(newPosition)) {
                if (!visited[newPosition]) {
                    visited[newPosition] = true;
                    queue.offer(new int[]{time + 1, newPosition, jump});
                }
            }

        }

    }

    private static boolean isMove(int position) {
        return position >= 0 &&
                position <= 100_000;
    }
}
