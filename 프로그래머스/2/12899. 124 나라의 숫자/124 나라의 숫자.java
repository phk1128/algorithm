import java.util.*;

class Solution {
    public String solution(int n) {
        String answer = "";
        int[] arr = new int[]{4,1,2};
        
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int r = n % 3;
            sb.append(arr[r]);
            n /= 3;
            if (r == 0) {
                n--; 
            }
        }
        
        answer = sb.reverse().toString();
        
        
        return answer;
    }
}