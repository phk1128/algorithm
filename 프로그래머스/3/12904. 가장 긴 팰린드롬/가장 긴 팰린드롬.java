import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        int len = s.length();
        
        for (int i = len; i >= 1; i--) {
            boolean flag = false;
            for (int j = 0; i + j <= len; j++) {
                if (isPalindrome(j, i + j - 1, s)) {
                    answer = i;
                    flag = true;
                }
            }
            if (flag) {
                break;
            }
        }

        return answer;
    }
    
    private boolean isPalindrome(int start, int end, String str) {
        
        while (start <= end) {
            if (!Objects.equals(str.charAt(start++), str.charAt(end--))) {
                return false;
            }
        }
        
        return true;
        
    }
}