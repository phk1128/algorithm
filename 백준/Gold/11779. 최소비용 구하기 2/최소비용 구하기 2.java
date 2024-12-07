import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int M;
    private static List<int[]>[] mapView;
    private static int[] dists;
    private static int[] route;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        dists = new int[N + 1];
        route = new int[N + 1];
        mapView = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);

        for (int i = 1; i <= N; i++) {
            mapView[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            mapView[start].add(new int[]{end, dist});
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int answer = getAnswer(start, end);
        List<String> routeResult = new ArrayList<>();

        while (end != 0) {
            routeResult.add(String.valueOf(end));
            end = route[end];
        }
        Collections.reverse(routeResult);
        StringBuilder sb = new StringBuilder();
        sb.append(answer)
                .append("\n")
                .append(routeResult.size())
                .append("\n")
                .append(String.join(" ", routeResult));

        bw.flush();
        bw.write(sb.toString());
        bw.close();
    }

    private static int getAnswer(int start, int end) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((n1, n2) -> n1[1] - n2[1]);
        queue.offer(new int[]{start, 0});
        dists[start] = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int node = cur[0];

            if (visited[end]) {
                break;
            }

            if (visited[node]) {
                continue;
            }
            visited[node] = true;

            for (int[] edge : mapView[node]) {
                if (dists[edge[0]] > dists[node] + edge[1]) {
                    dists[edge[0]] = dists[node] + edge[1];
                    route[edge[0]] = node;
                    queue.offer(new int[]{edge[0], dists[edge[0]]});
                }
            }
        }
        return dists[end];
    }
}
