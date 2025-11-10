import java.util.*;

class Solution {
    private int[] parents;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) {
                    union(i,j);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (parents[i] == i) {
                answer++;
            }
        }
        return answer;
    }
    
    private void union(int x, int y) {
        x = find(x);
        y = find(y);
        
        if (x > y) {
            parents[x] = y;
        }
        if (x < y) {
            parents[y] = x;
        }     
    }
    
    private int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return find(parents[x]);
    }
}