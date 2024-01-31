import java.util.*;

class Solution {
    private int answer;
    private List<Edge>[] graph;
    private boolean[] visited;
    
    public int solution(int n, int[][] costs) {
        answer = 0;
        graph = new ArrayList[n];
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] cost : costs) {
            int start = cost[0];
            int end = cost[1];
            int weight = cost[2];
        
            graph[start].add(new Edge(end, weight));
            graph[end].add(new Edge(start,weight));
        }
        
        prim();
        
        return answer;
    }
    
    private void prim() {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.offer(new Edge(0,0));
        
        while(!queue.isEmpty()) {
            Edge edge = queue.poll();
            int end = edge.getEnd();
            int weight = edge.getWeight();
            
            if (visited[end]) {
                continue;
            }
            visited[end] = true;
            answer += weight;
            
            for (Edge e : graph[end]) {
                queue.offer(e);
            }
        }
    }
    
    static class Edge implements Comparable<Edge> {
        private int end;
        private int weight;
        
        public Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
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