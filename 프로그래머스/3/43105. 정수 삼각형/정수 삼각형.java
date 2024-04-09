import java.util.*;

class Solution {
    
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int[][] dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];
        
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0) {
                    dp[i][j] = triangle[i][j] + dp[i-1][0];
                    continue;
                }
                
                if (j == triangle.length - 1) {
                    dp[i][j] = triangle[i][j] + dp[i-1][i-1];
                    continue;
                }
                
                dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j], dp[i-1][j-1]);
            }
        }
        
        for (int sum : dp[triangle.length - 1]) {
            if (sum > answer) {
                answer = sum;
            }
        }
        return answer;
    }
}