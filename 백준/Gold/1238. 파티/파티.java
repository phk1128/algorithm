import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Edge>[] graph1;
    private static List<Edge>[] graph2;
    private static int dists1[];
    private static int dists2[];
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        graph1 = new ArrayList[N + 1];
        graph2 = new ArrayList[N + 1];
        dists1 = new int[N + 1];
        dists2 = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            graph1[i] = new ArrayList<>();
            graph2[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph1[start].add(new Edge(end, dist));
            graph2[end].add(new Edge(start, dist));
        }

        dijkstra(X, graph1, dists1, N);
        dijkstra(X, graph2, dists2, N);

        int max = 0;

        for (int start = 1; start <= N; start++) {
            max = Math.max(max, dists1[start] + dists2[start]);
        }

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }

    private static void dijkstra(int start, List<Edge>[] graph, int[] dists, int N) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(start, 0));
        Arrays.fill(dists, Integer.MAX_VALUE);
        visited = new boolean[N + 1];
        dists[start] = 0;

        while (!queue.isEmpty()) {
            Edge now = queue.poll();
            int nowNode = now.getNode();
            int nowDist = now.getDist();

            if (visited[nowNode]) {
                continue;
            }
            visited[nowNode] = true;

            for (Edge edge : graph[nowNode]) {
                int next = edge.getNode();
                int nextDist = edge.getDist();
                if (dists[next] > nowDist + nextDist) {
                    dists[next] = nowDist + nextDist;
                    queue.offer(new Edge(next, dists[next]));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge> {

        private int node;
        private int dist;

        public Edge(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        public int getNode() {
            return this.node;
        }

        public int getDist() {
            return this.dist;
        }

        @Override
        public int compareTo(Edge e) {
            return this.dist - e.dist;
        }

    }
}