import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader br;
    private static BufferedWriter bw;
    private static StringTokenizer st;
    private static List<Integer>[] graph;
    private static boolean[] visited;
    private static String dfsResult;
    private static String bfsResult;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        dfsResult = "";
        bfsResult = "";

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
            graph[end].add(start);
        }

        for (int i = 1; i <= n; i++) {
            Collections.sort(graph[i]);
        }
        visited = new boolean[n + 1];
        dfs(v);
        visited = new boolean[n + 1];
        bfs(v);

        bw.write(dfsResult + "\n");
        bw.write(bfsResult);
        bw.flush();
        bw.close();
    }

    private static void dfs(int v) {
        if (!visited[v]) {
            visited[v] = true;
            dfsResult += (v + " ");
            for (int node : graph[v]) {
                dfs(node);
            }
        }
    }

    private static void bfs(int v) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(v);
        visited[v] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            bfsResult += (node + " ");
            for (int n : graph[node]) {
                if (!visited[n]) {
                    visited[n] = true;
                    queue.offer(n);
                }
            }
        }
    }
}
