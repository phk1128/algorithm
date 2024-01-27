import java.util.*;
import java.io.*;

public class Main {
    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Edge> graph;
    private static long[] cost;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        cost = new long[n + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.add(new Edge(start, end, weight));
        }

        if (bellManFord(n, 1)) {
            for (int i = 2; i <= n; i++) {
                if (cost[i] == Integer.MAX_VALUE) {
                    cost[i] = -1;
                }
                bw.write(cost[i] + "\n");
                bw.flush();
            }
        } else {
            bw.write(String.valueOf(-1));
            bw.flush();
        }

        bw.close();

    }

    private static boolean bellManFord(int n, int s) {
        cost[s] = 0;

        for (int i = 1; i <= n; i++) {
            for (Edge edge : graph) {
                int start = edge.getStart();
                int end = edge.getEnd();
                int weight = edge.getWeight();

                if (cost[start] == Integer.MAX_VALUE) {
                    continue;
                }

                if (cost[end] > cost[start] + weight) {
                    cost[end] = cost[start] + weight;

                    if (i == n) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static class Edge implements Comparable<Edge> {

        private int start;
        private int end;
        private int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }

        public int getWeight() {
            return this.weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }


    }

}