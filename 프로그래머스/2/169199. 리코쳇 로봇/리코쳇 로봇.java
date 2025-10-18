import java.util.*;

class Solution {
    
    private int[][] mapView;
    private boolean[][] visited;
    private int R;
    private int C;
    
    public int solution(String[] board) {
        int answer = 0;
        R = board.length;
        C = board[0].length();
        mapView = new int[R][C];
        visited = new boolean[R][C];
        int[] start = new int[2];
        
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                char el = board[r].charAt(c);
                if (Objects.equals(el, 'D')) {
                    mapView[r][c] = -1;
                }
                if (Objects.equals(el, 'G')) {
                    mapView[r][c] = 1;
                }
                if (Objects.equals(el, 'R')) {
                    start[0] = r;
                    start[1] = c;
                }
            }
        }
        
        answer = getResult(start[0], start[1]);
        
        return answer;
    }
    
    private int getResult(int sR, int sC) {
        
        int[][] directions = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sR, sC, 0}); // 위치, 카운트
        visited[sR][sC] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cR = current[0];
            int cC = current[1];
            int count = current[2];
            
            if (mapView[cR][cC] == 1) {
                return count;
            }
            
            for (int[] direction : directions) {
                int[] newPosition = getNewPosition(cR, cC, direction);
                int newR = newPosition[0];
                int newC = newPosition[1];
                if (visited[newR][newC]) {
                    continue;
                }
                visited[newR][newC] = true;
                queue.offer(new int[]{newR, newC, count + 1});
            }
        }
        return -1;
    }
    
    private int[] getNewPosition(int r, int c, int[] direction) {
        
        int[] newPosition = new int[]{r, c};
        
        if (direction[0] != 0) {
            while (true) {
                int newR = r + direction[0];
                
                if (!(newR >= 0 && newR < R)) {
                    newPosition[0] = r;
                    break;
                }
                if (mapView[newR][c] == -1) {
                    newPosition[0] = r;
                    break;
                }
                
                r = newR;
            }
        }
        
        if (direction[1] != 0) {
            while (true) {
                int newC = c + direction[1];
                if (!(newC >= 0 && newC < C)) {
                    newPosition[1] = c;
                    break;
                }
                if (mapView[r][newC] == -1) {
                    newPosition[1] = c;
                    break;
                }
                c = newC;
            }
        }
        return newPosition;
    }
}