import java.util.*;
import java.util.stream.*;

class Solution {
    
    private List<Edge>[] graph;
    private int[] dists;
    private boolean[] visited;
    
    static class Edge implements Comparable<Edge>{
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

    public int solution(int n, int[][] edge) {
        int answer = 0;
        graph = new ArrayList[n + 1];
        dists = new int[n + 1];
        visited = new boolean[n + 1];
        
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        Arrays.fill(dists, Integer.MAX_VALUE);
        
        for (int[] e : edge) {
            int start = e[0];
            int end = e[1];
            graph[start].add(new Edge(end, 1));
            graph[end].add(new Edge(start, 1));
        }
        
        dijkstra();
        
        int max = Arrays.stream(dists).skip(1).max().orElse(Integer.MIN_VALUE);
        answer = (int) Arrays.stream(dists).filter(num -> num == max).count();
        
        return answer;
    }
    
    private void dijkstra() {
        
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(1,0));
        dists[1] = 0;
        
        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            int node = edge.node;
            
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            
            for (Edge e : graph[node]) {
                if (dists[e.node] > dists[node] + e.weight) {
                    dists[e.node] = dists[node] + e.weight;
                    queue.offer(new Edge(e.node, dists[e.node]));
                }
            }
        }
    }
    
    
}