import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Edge>[] graph;
    private static int max;
    private static int maxIdx;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        graph = new ArrayList[V + 1];
        max = 0;
        maxIdx = 0;

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int v = 1; v <= V; v++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            while (true) {
                int end = Integer.parseInt(st.nextToken());
                if (end == -1) {
                    break;
                }
                int dist = Integer.parseInt(st.nextToken());
                graph[start].add(new Edge(end, dist));
                graph[end].add(new Edge(start, dist));
            }
        }

        visited = new boolean[V + 1];
        recursiveSolve(1, 0);
        max = 0;
        visited = new boolean[V + 1];
        recursiveSolve(maxIdx, 0);

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();
    }

    private static void recursiveSolve(int start, int sum) {

        visited[start] = true;

        if (sum > max) {
            maxIdx = start;
            max = sum;
        }

        for (Edge edge : graph[start]) {
            int node = edge.getNode();
            if (!visited[node]) {
                recursiveSolve(node, sum + edge.getDist());
            }
        }
    }

    static class Edge {

        private int node;
        private int dist;

        public Edge(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        public int getNode() {
            return node;
        }

        public int getDist() {
            return dist;
        }
    }
}