import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Edge>[] graph;
    private static int maxIdx;
    private static int max;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[start].add(new Edge(end, dist));
            graph[end].add(new Edge(start, dist));
        }

        maxIdx = 0;
        max = 0;
        visited = new boolean[n + 1];
        recursiveSolve(1, 0);
        visited = new boolean[n + 1];
        max = 0;
        recursiveSolve(maxIdx, 0);

        bw.write(String.valueOf(max));
        bw.flush();
        bw.close();

    }

    private static void recursiveSolve(int start, int sum) {

        visited[start] = true;

        if (sum > max) {
            max = sum;
            maxIdx = start;
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