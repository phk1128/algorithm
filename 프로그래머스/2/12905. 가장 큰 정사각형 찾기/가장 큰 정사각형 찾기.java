import java.util.*;

class Solution
{
    public int solution(int [][]board)
    {
        int answer = 0;
        int[][] dp = new int[board.length + 1][board[0].length + 1];
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dp[r + 1][c + 1] = board[r][c];
            }
        }
        
        for (int r = 1; r <= board.length; r++) {
            for (int c = 1; c <= board[0].length; c++) {
                if (dp[r][c] == 0) {
                    continue;
                }
                
                int num1 = dp[r-1][c-1];
                int num2 = dp[r][c-1];
                int num3 = dp[r-1][c];
                int min = Integer.MAX_VALUE;
                min = Math.min(min,num1);
                min = Math.min(min,num2);
                min = Math.min(min,num3);
                dp[r][c] = min + 1;
                answer = Math.max(answer, dp[r][c]);
            }
        }
        
        answer *= answer;
        return answer;
    }
}