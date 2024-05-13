import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 1;

        for (int i = 1; i < s.length() - 1; i++) {
            char c = s.charAt(i);
            int left = i - 1;
            int right = i + 1;
            int result = 1;
            
            while (left >= 0 && right < s.length()) {
                if (Objects.equals(s.charAt(left), s.charAt(right))) {
                    left--;
                    right++;
                    result += 2;
                } else {
                    break;
                }
            }
            answer = Math.max(result, answer);
        }
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int result = 1;
            int tmpIdx = i + 1;
            while (tmpIdx < s.length()) {
                if (Objects.equals(s.charAt(tmpIdx), c)) {
                    result++;
                    tmpIdx++;
                } else {
                    break;
                }
            }
            answer = Math.max(answer, result);
        }
        
        for (int i = 0; i < s.length() - 1; i++) {
            char cC = s.charAt(i);
            char nC = s.charAt(i + 1);
            
            if (Objects.equals(cC, nC)) {

                int cIdx = i;
                int nIdx = i + 1;
                int left = cIdx - 1;
                int right = nIdx + 1;
                int result = 2;
                
                while (left >= 0 && right < s.length()) {
                    
                    if (Objects.equals(s.charAt(left), s.charAt(right))) {
                        result += 2;
                        left--;
                        right++;
                    } else {
                        break;
                    }
                }
                
                answer = Math.max(result, answer);
            }
        }

        return answer;
    }
}