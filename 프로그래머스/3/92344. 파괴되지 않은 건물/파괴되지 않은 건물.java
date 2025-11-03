import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int R = board.length;
        int C = board[0].length;
        int[][] degrees = new int[R + 1][C + 1];
        for (int i = 0; i < skill.length; i++) {
                int[] row = skill[i];
                int type = row[0];
                int r1 = row[1];
                int c1 = row[2];
                int r2 = row[3];
                int c2 = row[4];
                int degree = row[5];
                if (type == 1) {
                    degree *= -1;
                }
                degrees[r1][c1] += degree;
                degrees[r2 + 1][c1] += -degree;
                degrees[r1][c2 + 1] += -degree;
                degrees[r2 + 1][c2 + 1] += degree;
        }
        
        for (int r = 0; r < R + 1; r++) {
            for (int c = 0; c < C; c++) {
                degrees[r][c + 1] += degrees[r][c];
            }
        }
        
        for (int c = 0; c < C + 1; c++) {
            for (int r = 0; r < R; r++) {
                degrees[r + 1][c] += degrees[r][c];
            }
        }
        
        int count = R * C;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int num = board[r][c] + degrees[r][c];
                if (num <= 0) {
                    count--;
                }
            }
        }
        answer = count;
        return answer;
    }
}