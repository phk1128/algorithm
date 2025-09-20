import java.util.*;

class Solution {
    private int[] dp;
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        dp = new int[10000001];
        
        for (int t : tangerine) {
            dp[t]++;
        }
        
        Arrays.sort(dp);
        int count = 0;
        for (int i = 10000000; i >= 1; i--) {
            k -= dp[i];
            count++;
            
            if (k <= 0) {
                answer = count;
                break;
            }
        }
        
        
        return answer;
    }
    
}