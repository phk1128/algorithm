import java.util.*;

class Solution {
    public String solution(int n) {
        String answer = "";
        int[] arr = new int[]{4,1,2};
        
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int q = n / 3;
            int r = n % 3;
            sb.append(arr[r]);
            n = (n-1) / 3;
        }
        
        answer = sb.reverse().toString();
        
        
        return answer;
    }
}