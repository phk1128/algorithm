import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N, M, K;
    private static int[] candies;
    private static List<List<Integer>> graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candies = new int[N + 1];
        graph = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        visited = new boolean[N + 1];

        List<Group> groups = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                groups.add(getGroup(i));
            }
        }

        int[] dp = new int[K];
        for (Group group : groups) {
            for (int i = K -1; i >= group.kid; i--) {
                dp[i] = Math.max(dp[i], dp[i - group.kid] + group.candy);
            }
        }

        Arrays.sort(dp);
        System.out.println(dp[K -1]);
    }

    private static Group getGroup(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true;

        int kid = 0;
        int candy = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            kid++;
            candy += candies[cur];

            for (int next : graph.get(cur)) {
                if (!visited[next]) {
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }
        return new Group(kid, candy);
    }

    static class Group {
        int kid;
        int candy;

        Group(int kid, int candy) {
            this.kid = kid;
            this.candy = candy;
        }
    }
}
