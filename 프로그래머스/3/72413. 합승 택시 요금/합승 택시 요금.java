import java.util.*;

class Solution {
    
    private int[][] costs;
    private int min;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        costs = new int[n + 1][n + 1];
        min = Integer.MAX_VALUE;
        
        for (int i = 0; i < n + 1; i++) {
            Arrays.fill(costs[i], Integer.MAX_VALUE);
            costs[i][i] = 0;
        }
        
        for (int[] fare : fares) {
            int start = fare[0];
            int end = fare[1];
            int cost = fare[2];
            costs[start][end] = cost;
            costs[end][start] = cost;
        }
        
        floyd(n);
        
        for (int i = 1; i < n + 1; i++) {
            min = Math.min(costs[s][i] + costs[i][a] + costs[i][b], min);
        }
        answer = min;
        
        return answer;
    }
    
    
    private void floyd(int n) {
        
        for (int i = 1; i < n + 1; i++) {
            for (int start = 1; start < n + 1; start++) {
                for (int end = 1; end < n + 1; end++) {
                    
                    if (costs[start][i] == Integer.MAX_VALUE || costs[i][end] == Integer.MAX_VALUE) {
                        continue;
                    }
                    
                    if (costs[start][end] > costs[start][i] + costs[i][end]) {
                        costs[start][end] = costs[start][i] + costs[i][end];
                    }
                }
            }
        }
    }
}