import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int minTime;
    private static int count;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        minTime = Integer.MAX_VALUE;
        count = 0;
        bfsSolve(N, M);

        StringBuilder sb = new StringBuilder();
        sb.append(minTime);
        sb.append(System.lineSeparator());
        sb.append(count);

        bw.flush();
        bw.write(sb.toString());
        bw.close();

    }

    private static void bfsSolve(int N, int M) {
        int[] visited = new int[100_001];
        PriorityQueue<int[]> queue = new PriorityQueue<>((c1, c2) -> c1[0] - c2[0]);
        queue.offer(new int[]{0, N});
        visited[N] = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curTime = cur[0];
            int curPos = cur[1];

            if (curTime > minTime) {
                continue;
            }

            if (curPos == M) {
                minTime = curTime;
                count++;
            }

            int nextPos1 = curPos - 1;
            int nextPos2 = curPos + 1;
            int nextPos3 = 2 * curPos;

            if (nextPos1 >= 0 && nextPos1 <= 100_000) {
                if (visited[nextPos1] == 0 || visited[nextPos1] == curTime + 1) {
					visited[nextPos1] = curTime + 1;
                    queue.offer(new int[]{curTime + 1, nextPos1});
                }
            }

            if (nextPos2 >= 0 && nextPos2 <= 100_000) {
                if (visited[nextPos2] == 0 || visited[nextPos2] == curTime + 1) {
					visited[nextPos2] = curTime + 1;
                    queue.offer(new int[]{curTime + 1, nextPos2});
                }
            }

            if (nextPos3 >= 0 && nextPos3 <= 100_000) {
                if (visited[nextPos3] == 0 || visited[nextPos3] == curTime + 1) {
					visited[nextPos3] = curTime + 1;
                    queue.offer(new int[]{curTime + 1, nextPos3});
                }
            }

        }
    }
}