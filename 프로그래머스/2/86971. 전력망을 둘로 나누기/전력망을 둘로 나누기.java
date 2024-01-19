import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private List<List<Integer>> graph;
    private boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        int answer = -1;
        int min = Integer.MAX_VALUE;
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] wire : wires) {
            int start = wire[0];
            int end = wire[1];
            
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
        
        for (int[] wire : wires) {
            int start = wire[0];
            int end = wire[1];
            
            visited = new boolean[n+1];
            
            graph.get(start).remove(Integer.valueOf(end));
            graph.get(end).remove(Integer.valueOf(start));
            
            int result = bfs();
            
            min = Math.min(min, Math.abs(result - (n - result)));
            
            graph.get(start).add(end);
            graph.get(end).add(start);
            
        }
        answer = min;
        
        return answer;
    }
    
    private int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        int count = 1;
        
        while(!queue.isEmpty()) {
            int start = queue.poll();
            for (int node : graph.get(start)) {
                if (!visited[node]) {
                    visited[node] = true;
                    queue.add(node);
                    count++;
                }
            }
        }
        return count;
        
    }
}