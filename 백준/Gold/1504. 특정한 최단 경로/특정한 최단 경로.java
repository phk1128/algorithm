import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Edge>[] graph;
    private static int[] dists;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[start].add(new Edge(end, dist));
            graph[end].add(new Edge(start, dist));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[][] roots = new int[][]{{1, v1, v2, n}, {1, v2, v1, n}};

        int min = Integer.MAX_VALUE;
        for (int[] root : roots) {
            int sum = 0;
            boolean flag = true;
            for (int i = 0; i < root.length - 1; i++) {
                int dist = dijkstra(root[i], root[i + 1], n);
                if (dist == Integer.MAX_VALUE) {
                    flag = false;
                    break;
                }
                sum += dist;
            }

            if (flag) {
                min = Math.min(min, sum);
            }
        }

        if (min == Integer.MAX_VALUE) {
            min = -1;
        }

        bw.write(String.valueOf(min));
        bw.flush();
        bw.close();

    }

    private static int dijkstra(int start, int end, int n) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        dists = new int[n + 1];
        visited = new boolean[n + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[start] = 0;
        queue.offer(new Edge(start, 0));

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int node = edge.getNode();
            int dist = edge.getDist();

            if (visited[node]) {
                continue;
            }
            visited[node] = true;

            for (Edge e : graph[node]) {
                int next = e.getNode();
                if (dists[next] > dist + e.getDist()) {
                    dists[next] = dist + e.getDist();
                    queue.offer(new Edge(next, dist + e.getDist()));
                }
            }
        }
        return dists[end];
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