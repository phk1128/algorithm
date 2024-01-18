import java.util.*;

class Solution {
    private List<List<Integer>> nodes = new ArrayList<>();
    private boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        int min = Integer.MAX_VALUE;
        int answer = -1;
        for (int i = 0; i <= n; i++) {
            nodes.add(new ArrayList<>());
        }
        
        for (int[] wire : wires) {
            int start = wire[0];
            int end = wire[1];
            
            nodes.get(start).add(end);
            nodes.get(end).add(start);
        }
        
        for (int[] wire : wires) {
            int start = wire[0];
            int end = wire[1];
            
            nodes.get(start).remove(Integer.valueOf(end));
            nodes.get(end).remove(Integer.valueOf(start));
            
            visited = new boolean[n+1];
            int result = dfs(1);
            
            min = Math.min(min, Math.abs(result - (n - result)));
            
            nodes.get(start).add(end);
            nodes.get(end).add(start);
            
        }
        answer = min;
        return answer;
    }
    
    public int dfs(int start) {
        int count = 1;
        visited[start] = true;
        for (int n : nodes.get(start)) {
            if (!visited[n]) {
                count += dfs(n);
            }
        }
        return count;
    }
}