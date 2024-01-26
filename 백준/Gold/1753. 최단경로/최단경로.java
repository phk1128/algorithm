import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Edge>[] graph;
    private static int[] cost;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        cost = new int[v+1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        visited = new boolean[v+1];


        graph = new ArrayList[v + 1];

        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[start].add(new Edge(end,weight));
        }

        dijkstra(k);

        for (int i = 1; i <= v; i++) {
            if (cost[i] == Integer.MAX_VALUE) {
                bw.write("INF"+ "\n");
                bw.flush();
                continue;
            }
            bw.write(cost[i] + "\n");
            bw.flush();
        }

        bw.close();
    }

    private static void dijkstra(int k) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(k,0));
        cost[k] = 0;
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int node = edge.getNode();
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            for (Edge e : graph[node]) {
                if (cost[e.getNode()] > cost[node] + e.getWeight()) {
                    cost[e.getNode()] = cost[node] + e.getWeight();
                    queue.offer(new Edge(e.getNode(), cost[node] + e.getWeight()));
                }
            }
        }
    }

    static class Edge implements Comparable<Edge>{
        private int node;
        private int weight;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        private int getNode() {
            return this.node;
        }

        private int getWeight() {
            return this.weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
}