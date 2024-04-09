import java.util.*;

public class Solution {
    
    
    public int solution(int n) {
        int ans = 1;
        int num = 0;
        
        while (n != 1) {
            num += n % 2;
            n /= 2;
            
        }
        
        ans += num;

        return ans;
    }
}