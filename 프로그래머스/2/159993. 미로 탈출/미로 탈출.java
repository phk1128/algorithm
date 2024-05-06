import java.util.*;

// 레버를 찾고, 레버에서 탈출구로

class Solution {
    
    private int[][] mapView;
    private int R;
    private int C;
    
    public int solution(String[] maps) {
        int answer = 0;
        R = maps.length;
        C = maps[0].length();
        mapView = new int[R][C];
        int[] start = new int[2];
        int[] lever = new int[2];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                char el = maps[r].charAt(c);
                if (Objects.equals(el, 'S')) {
                    mapView[r][c] = 1;
                    start = new int[]{r,c};
                }
                if (Objects.equals(el, 'L')) {
                    mapView[r][c] = 2;
                    lever = new int[]{r,c};
                }
                if (Objects.equals(el, 'E')) {
                    mapView[r][c] = 3;
                }
                if (Objects.equals(el, 'X')) {
                    mapView[r][c] = -1;
                }
            }
        }
        
        int leverTime = getTime(start[0], start[1], 2);
        int exitTime = getTime(lever[0], lever[1], 3);
        
        answer = leverTime + exitTime;
        if (leverTime == -1 || exitTime == -1) {
            answer = -1;
        }
        
        return answer;
    }
    
    private int getTime(int r, int c, int target) {
        boolean[][] visited = new boolean[R][C];
        int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r,c,0});
        visited[r][c] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cR = current[0];
            int cC = current[1];
            int time = current[2];
            
            if (mapView[cR][cC] == target) {
                return time;
            }
            
            for (int[] direction : directions) {
                int newR = cR + direction[0];
                int newC = cC + direction[1];
                
                if (!(newR >= 0 && newR < R && newC >= 0 && newC < C)) {
                    continue;
                }
                
                if (visited[newR][newC] || mapView[newR][newC] == -1) {
                    continue;
                }
                
                visited[newR][newC] = true;
                queue.offer(new int[]{newR, newC, time + 1});
            }
        }
        
        return -1;
    }
}