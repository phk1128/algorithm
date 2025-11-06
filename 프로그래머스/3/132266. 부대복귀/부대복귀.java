import java.util.*;
import java.util.stream.*;

class Solution {

    private List<Edge>[] graph;
    private int[] cost;
    private boolean[] visited;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = {};
        graph = new ArrayList[n + 1];
        visited = new boolean[n + 1];
        cost = new int[n + 1];
        Arrays.fill(cost, Integer.MAX_VALUE);
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] road : roads) {
            int start = road[0];
            int end = road[1];
            int weight = 1;
            graph[start].add(new Edge(end, weight));
            graph[end].add(new Edge(start, weight));
        }
        
        dijkstra(destination);
        
        List<Integer> result = new ArrayList<>();
        
        for (int s : sources) {
            result.add(cost[s] == Integer.MAX_VALUE ? -1 : cost[s]);
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    
    private void dijkstra(int start) {
        PriorityQueue<Edge> queue = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
        cost[start] = 0;
        queue.offer(new Edge(start, 0));
        
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int node = edge.node;
            int weight = edge.weight;
            
            if (visited[node]) {
                continue;
            }
            
            visited[node] = true;
            
            for (Edge e : graph[node]) {
                if (cost[e.node] > cost[node] + e.weight) {
                    cost[e.node] = cost[node] + e.weight;
                    queue.offer(new Edge(e.node, cost[e.node]));
                }
            }
        }
    }
    
    
    static class Edge {
        int node;
        int weight;
        
        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}