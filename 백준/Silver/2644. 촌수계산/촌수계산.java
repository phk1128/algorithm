import java.util.*;
import java.io.*;

public class Main {

    private static List<Integer>[] graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        visited = new boolean[n + 1];

        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int parents = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            graph[parents].add(child);
            graph[child].add(parents);
        }

        bw.write(String.valueOf(recursiveSolve(start, end, 0)));
        bw.flush();
        bw.close();
    }

    private static int recursiveSolve(int start, int end, int depth) {
        if (start == end) {
            return depth;
        }

        visited[start] = true;

        for (int i = 0; i < graph[start].size(); i++) {
            int next = graph[start].get(i);
            if (!visited[next]) {
                int count = recursiveSolve(next, end, depth + 1);
                if (count != -1) {
                    return count;
                }
            }
        }
        return -1;
    }
}
