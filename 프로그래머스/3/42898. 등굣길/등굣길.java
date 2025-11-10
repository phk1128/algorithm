import java.util.*;
class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        int[][] dp = new int[m + 1][n + 1];
        dp[1][1] = 1;
        for (int r = 1; r <= m; r++) {
            for (int c = 1; c <= n; c++) {
                if (isPuddle(r, c, puddles)) {
                    continue;
                }
                dp[r][c] += (dp[r-1][c] + dp[r][c-1]) % 1_000_000_007;
            }
        }
        answer = dp[m][n] % 1_000_000_007;
        return answer;
    }
    
    private boolean isPuddle(int r, int c, int[][] puddles) {
        for (int[] puddle : puddles) {
            if(puddle[0] == r && puddle[1] == c) {
                return true;
            }
        }
        return false;
    }
}