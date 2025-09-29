import java.util.*;

class Solution {
    private final int[] dx = new int[]{1, 0, 1};
    private final int[] dy = new int[]{0, 1, 1};
    private char[][] mapView;
    private boolean[][] markers;
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        mapView = new char[m][n];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                mapView[r][c] = board[r].charAt(c);
            }
        }
        answer = getAnswer(m, n);
        
        return answer;
    }
    
    private int getAnswer(int m, int n) {
        markBlock(m, n);
        int count = countTotalEqualBlock(m, n);
        if (count == 0) {
            return 0;
        }
        moveToDown(m, n);
        return count + getAnswer(m, n);
        
    }
    
    private void moveToDown(int m, int n) {
        for (int r = m - 2; r >= 0; r--) {
            for (int c = 0; c < n; c++) {
                char block = mapView[r][c];
                if (block == '-') {
                    continue;
                }
                int nextR = r;
                while (nextR < m - 1) {
                    if (mapView[nextR + 1][c] == '-') {
                        nextR++;
                    } else {
                        break;
                    }
                }
                mapView[r][c] = '-';
                mapView[nextR][c] = block;
            }
        }
    }
    
    
    private int countTotalEqualBlock(int m, int n) {
        int count = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (markers[r][c]) {
                    mapView[r][c] = '-';
                    count++;
                }
            }
        }
        return count;
    }
    
    private void markBlock(int m, int n) {
        markers = new boolean[m][n];
        for (int r = 0; r < m - 1; r++) {
            for (int c = 0; c < n - 1; c++) {
                if (mapView[r][c] == '-') {
                    continue;
                }
                int count = countAroundEqualBlock(r, c);
                if (count == 4) {
                    mark(r, c);
                }
            }
        }
    }
    
    private void mark(int r, int c) {
        markers[r][c] = true;
        markers[r + dy[0]][c + dx[0]] = true;
        markers[r + dy[1]][c + dx[1]] = true;
        markers[r + dy[2]][c + dx[2]] = true;
    }
    
    private int countAroundEqualBlock(int r, int c) {
        char block = mapView[r][c];
        int count = 1;
        for (int i = 0; i < 3; i++) {
            int nR = r + dy[i];
            int nC = c + dx[i];
            if (block == mapView[nR][nC]) {
                count++;
            }
        }
        return count;
    }
}