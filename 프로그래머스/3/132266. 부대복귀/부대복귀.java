import java.util.*;
import java.util.stream.*;

class Solution {
    private static List<int[]>[] graph;
    private static int[] costs;
    private static boolean[] visited;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = {};
        graph = new ArrayList[n + 1];
        costs = new int[n + 1];
        visited = new boolean[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] r : roads) {
            int s = r[0];
            int e = r[1];
            int w = 1;
            graph[s].add(new int[]{e, w});
            graph[e].add(new int[]{s, w});
        }
        
        dijkstra(destination);
        
        return Arrays.stream(sources).map(s -> {
            int cost = costs[s];
            if (cost == Integer.MAX_VALUE) {
                cost = -1;
            }
            return cost;
        }).toArray();
    }
    
    private void dijkstra(int d) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((edge1, edge2) -> edge1[1] - edge2[1]);
        queue.offer(new int[]{d, 0});
        costs[d] = 0;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int start = cur[0];
            
            if (visited[start]) {
                continue;
            }
            visited[start] = true;
            
            for (int[] edge : graph[start]) {
                int end = edge[0];
                if (costs[end] > costs[start] + edge[1]) {
                    costs[end] = costs[start] + edge[1];
                    queue.offer(new int[]{end, costs[end]});
                }
            }
        }
    }
}