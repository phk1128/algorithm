import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    private static List<int[]>[] graph;
    private static int[] dists;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        dists = new int[N + 1];
        visited = new boolean[N + 1];
        graph = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        Arrays.fill(dists, Integer.MAX_VALUE);

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            graph[start].add(new int[]{end, dist});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        bw.flush();
        bw.write(String.valueOf(getAnswer(start, end)));
        bw.close();
    }

    private static int getAnswer(int start, int end) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
        queue.offer(new int[]{start, 0});
        dists[start] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0];
            
            if (node == end) {
                break;
            }

            if (visited[node]) {
                continue;
            }
            visited[node] = true;

            for (int[] edge : graph[node]) {
                if (dists[edge[0]] > dists[node] + edge[1]) {
                    dists[edge[0]] = dists[node] + edge[1];
                    queue.offer(new int[]{edge[0], dists[edge[0]]});
                }
            }
        }
        return dists[end];
    }
}
