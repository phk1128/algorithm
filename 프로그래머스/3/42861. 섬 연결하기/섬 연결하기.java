import java.util.*;

class Solution {
    private int[] parents;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parents = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
        Arrays.sort(costs, (c1,c2) -> c1[2] - c2[2]);
        
        for (int[] cost : costs) {
            if (union(cost[0], cost[1])) {
                answer += cost[2];
            }
        }
        
        return answer;
    }
    private int find(int x) {
        if (x == parents[x]) {
            return x;
        }
        return find(parents[x]);
    }
    
    private boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        
        if (x == y) {
            return false;
        }
        
        if (x > y) {
            parents[x] = y;
        }
        
        if (x < y) {
            parents[y] = x;
        }
        
        return true;
    }
}