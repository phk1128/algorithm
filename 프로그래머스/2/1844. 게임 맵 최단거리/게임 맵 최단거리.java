import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int answer = -1;
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] visited = new boolean[n][m];
        int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0,1});
        visited[0][0] = true;
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int cR = current[0];
            int cC = current[1];
            int count = current[2];
            
            if (cR == n - 1 && cC == m - 1) {
                answer = count;
            }
            
            for (int[] direction : directions) {
                int nR = cR + direction[0];
                int nC = cC + direction[1];
                
                if (!(nR >= 0 && nR < n && nC >= 0 && nC < m)) {
                    continue;
                }
                
                if (visited[nR][nC] || maps[nR][nC] == 0) {
                    continue;
                }
                
                queue.offer(new int[]{nR, nC, count + 1});
                visited[nR][nC] = true;
            }
        }
        
        return answer;
    }
}