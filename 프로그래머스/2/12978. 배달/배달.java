import java.util.*;

class Solution {
    
    private static PriorityQueue<Edge>[] graph;
    private static boolean[] visited;
    private static int[] dist;
    
    static class Edge implements Comparable<Edge> {
        
        int node;
        int weight;
        
        public Edge(int node, int weight) {
        
            this.node = node;
            this.weight = weight;
        }
        
        @Override
        public int compareTo(Edge e) {
            return this.weight - e.weight;
        }
    }
    
    
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        graph = new PriorityQueue[N + 1];
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new PriorityQueue<>();
        }
        
        for (int[] r : road) {
            
            int start = r[0];
            int end = r[1];
            int weight = r[2];
            
            graph[start].offer(new Edge(end, weight));
            graph[end].offer(new Edge(start, weight));
        }
        
        searchRoute();
        
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
    
    private static void searchRoute() {
        
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(1,0));
        dist[1] = 0;
        
        while (!queue.isEmpty()) {
            
            Edge edge = queue.poll();
            int node = edge.node;
            
            if (visited[node]) {
                continue;
            }
            
            visited[node] = true;
            
            for (Edge e : graph[node]) {
                if (dist[e.node] > dist[node] + e.weight) {
                    dist[e.node] = dist[node] + e.weight;
                    queue.offer(new Edge(e.node, dist[e.node]));
                }
            }
        }
    }
}