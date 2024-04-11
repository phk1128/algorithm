import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        int answer = 0;
        
        int[] dp = new int[y+1];
        
        if (x == y) {
            return 0;
        }
        
        
        for (int i = x+1; i <= y; i++) {
            
            int min = Integer.MAX_VALUE;
            boolean flag = false;
            
            if ((i - n) >= x) {
                if (dp[i-n] != -1) {
                    min = Math.min(min, dp[i-n]);
                    flag = true;
                }
            }
            
            if (i % 2 == 0 && i / 2 >= x) {
                if (dp[i / 2] != -1) {
                    min = Math.min(min, dp[i/2]);
                    flag = true;
                }
            }
            
            if (i % 3 == 0 && i / 3 >= x) {
                if (dp[i/3] != -1) {
                    min = Math.min(min, dp[i/3]);
                    flag = true;
                }
            }
            
            if (flag) {
                dp[i] = min + 1;
            } else {
                dp[i] = -1;
            }
        }
        
        answer = dp[y];
        
        return answer;
    }
}