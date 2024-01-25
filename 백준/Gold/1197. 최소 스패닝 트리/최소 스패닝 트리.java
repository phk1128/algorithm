import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Edge>[] graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        graph = new ArrayList[v + 1]; // v + 1 개의 ArrayList 타입을 가질 수 있음, 초기값은 null 이므로 반드시 초기화 작업이 필요함
        visited = new boolean[v + 1]; // v + 1 개의 boolean 타입을 가질 수 있음, 초기값은 false로 되어 있음

        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start].add(new Edge(weight, end));
            graph[end].add(new Edge(weight, start));

        }
        int answer = prim();

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
    }

    private static int prim() {
        int result = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(0, 1));
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int vertex = edge.getVertex();
            if (visited[vertex]) {
                continue;
            }
            visited[vertex] = true;
            result += edge.getWeight();
            for (Edge e : graph[vertex]) {
                if (!visited[e.getVertex()]) {
                    queue.offer(e);
                }
            }
        }

        return result;
    }

    static class Edge implements Comparable<Edge> {
        private int vertex;
        private int weight;

        public Edge(int weight, int vertex) {
            this.vertex = vertex;
            this.weight = weight;
        }

        private int getVertex() {
            return this.vertex;
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