import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Edge> graph;
    private static long[] dists;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        int TC = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());
                graph.add(new Edge(start, end, dist));
                graph.add(new Edge(end, start, dist));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int dist = Integer.parseInt(st.nextToken());
                graph.add(new Edge(start, end, -dist));
            }

            String answer = "";
            for (int node = 1; node <= N; node++) {
                if (bellManFord(node, N)) {
                    answer = "YES";
                    break;
                }
                answer = "NO";
            }
            sb.append(answer);
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }


    private static boolean bellManFord(int node, int N) {
        dists = new long[N + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[node] = 0;

        for (int i = 1; i <= N; i++) {
            boolean flag = false;
            for (Edge edge : graph) {
                int start = edge.getStart();
                int end = edge.getEnd();
                int dist = edge.getDist();

                if (dists[start] == Integer.MAX_VALUE) {
                    continue;
                }

                if (dists[end] > dists[start] + dist) {
                    dists[end] = dists[start] + dist;
                    flag = true;
                    if (i == N) {
                        return true;
                    }
                }

            }

            if (!flag) {
                return false;
            }
        }
        return false;
    }

    static class Edge {

        private int start;
        private int end;
        private int dist;

        public Edge(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getDist() {
            return dist;
        }
    }
}