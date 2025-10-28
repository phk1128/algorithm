import java.util.*;

class Solution {
    
    
    private static int[] dC = new int[]{0,0,-1,1 ,1,-1,-1,1};
    private static int[] dR = new int[]{-1,1,0,0 ,-1,-1,1,1};
    
    public int solution(String[] board) {
        
        boolean[] winEl = calculateWinEl(board);
        
        if (winEl[0] && !winEl[1] && countEl(board,'O') == countEl(board,'X') + 1) {
            return 1;
        }
        
        if (!winEl[0] && winEl[1] && countEl(board, 'O') == countEl(board, 'X')) {
            return 1; 
        }
        
        if (!winEl[0] && !winEl[1]) {
            if (countEl(board,'O') == countEl(board,'X') + 1) {
                return 1;
            }
            if (countEl(board,'O') == countEl(board,'X')) {
                return 1;
            }
        }
        
        return 0;
    }
    
    private int countEl(String[] board, char el) {
        int count = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r].charAt(c) == el) {
                    count++;
                }
            }
        }
        return count;
    }
    
    private boolean[] calculateWinEl(String[] board) {
        boolean[] result = new boolean[2];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r].charAt(c) != '.') {
                    if (isWin(board, board[r].charAt(c), r, c)) {
                        if(board[r].charAt(c) == 'O') {
                            result[0] = true;
                        }
                        if(board[r].charAt(c) == 'X') {
                            result[1] = true;  
                        }
                    }
                }
            }
        }
        return result;
    }
    
    private boolean isWin(String[] board, char el, int r, int c) {
        for (int i = 0; i < 8; i++) {
            int count = 0;
            int nR = r;
            int nC = c;
            while (nR >= 0 && nR < 3 && nC >= 0 && nC < 3) {
                if (board[nR].charAt(nC) == el) {
                    count++;
                }
                nR += dR[i];
                nC += dC[i];
                if (count == 3) {
                    return true;
                }
            }
        }
        return false;
        
    }
}