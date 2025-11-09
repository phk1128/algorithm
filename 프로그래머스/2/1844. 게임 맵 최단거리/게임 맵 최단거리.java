import java.util.*;

class Solution {
    
    private static int[] dR = new int[]{-1, 1, 0, 0};
    private static int[] dC = new int[]{0, 0, -1, 1};
    
    public int solution(int[][] maps) {
        int answer = 0;
        answer = getAnswer(maps);
        return answer;
    }
    
    private int getAnswer(int[][] maps) {
        boolean[][] visited = new boolean[maps.length][maps[0].length];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0,1});
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cR = cur[0];
            int cC = cur[1];
            int cnt = cur[2];
            
            if (cR == maps.length - 1 && cC == maps[0].length - 1) {
                return cnt;
            }
            
            for (int i = 0; i < 4; i++) {
                int nR = cR + dR[i];
                int nC = cC + dC[i];
                
                if (!isIn(nR, nC, maps.length, maps[0].length)) {
                    continue;
                }
                if (visited[nR][nC] || maps[nR][nC] == 0) {
                    continue;
                }
                visited[nR][nC] = true;
                queue.offer(new int[]{nR, nC, cnt + 1});
            }   
        }        
        return -1;
    }
    
    private boolean isIn(int r, int c, int R, int C) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
}