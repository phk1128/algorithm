import java.util.*;

class Solution {
    
    private static char[][] mapView;
    private static int m;
    private static int n;
    private static int[][] directions;
    private static boolean[][] visited;
    private static int answer;
    private static boolean isRemove;
    
    public int solution(int m, int n, String[] board) {
        answer = 0;
        this.m = m;
        this.n = n;
        directions = new int[][]{{1,0}, {0,1}, {1,1}};
        
        mapView = new char[m][n];
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                mapView[r][c] = board[r].charAt(c);
            }
        }
        
        isRemove = true;
        
        while (isRemove) {
            searchBlock();
            removeBlock();
            moveBlock();
        }
        
        
        return answer;
    }
    
    private static void moveBlock() {
        
        for (int r = m - 2; r >= 0; r--) {
            for (int c = 0; c < n; c++) {
                char target = mapView[r][c];
                if (Objects.equals(target, '0')) {
                    continue;
                }
                int tmpR = r;
                while(tmpR != (m - 1)) {
                    if (Objects.equals(mapView[tmpR + 1][c], '0')) {
                        tmpR++;
                    } else {
                        break;
                    }
                }
                
                mapView[r][c] = '0';
                mapView[tmpR][c] = target;
            }
        }
    }
    
    private static void removeBlock() {
        isRemove = false;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (visited[r][c]) {
                    isRemove = true;
                    mapView[r][c] = '0';
                    answer++;
                }
            }
        }
    }
       
    private static void searchBlock() {
        
        visited = new boolean[m][n];
        
        for (int r = 0; r < m - 1; r++) {
            for (int c = 0; c < n - 1; c++) {
                char target = mapView[r][c];
                if (Objects.equals(target, '0')) {
                    continue;
                }
                boolean flag = true;
                for (int[] direction : directions) {
                    int newR = r + direction[0];
                    int newC = c + direction[1];
                    
                    if (!Objects.equals(target, mapView[newR][newC])) {
                        flag = false;
                        break;
                    }
                }
                
                if (flag) {
                    visited[r][c] = true;
                    for (int[] direction : directions) {
                        int newR = r + direction[0];
                        int newC = c + direction[1];
                        visited[newR][newC] = true;
                    }
                }
            }
        }
    }
    
}