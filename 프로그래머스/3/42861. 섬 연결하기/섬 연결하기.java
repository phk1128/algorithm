import java.util.*;

class Solution {
    private int[] parents;
    private List<Edge> graph;
    private int answer;
    public int solution(int n, int[][] costs) {
        parents = new int[n];
        graph = new ArrayList<>();
        answer = 0;
        
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        
        for (int[] cost : costs) {
            int start = cost[0];
            int end = cost[1];
            int weight = cost[2];
            
            graph.add(new Edge(start, end, weight));
        }
        
        Collections.sort(graph);
        
        kruskal();
        
        return answer;
    }
    
    private void kruskal() {
        
        for (Edge edge : graph) {
            int start = edge.getStart();
            int end = edge.getEnd();
            int weight = edge.getWeight();
            
            if (unionFind(start, end)) {
                answer += weight;
            }
        }
    }
    
    private int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return find(parents[x]);
    }
    
    private boolean unionFind(int x, int y) {
        x = find(x);
        y = find(y);
        
        if (x == y) {
            return false;
        }
        
        if (x < y) {
            parents[y] = x; 
        }
        
        if (x > y) {
            parents[x] = y;
        }
        
        return true;
    }
    
    static class Edge implements Comparable<Edge> {
        
        private int start;
        private int end;
        private int weight;
        
        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
        
        public int getStart() {
            return this.start;
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