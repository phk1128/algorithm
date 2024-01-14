import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        for (int unit = 1; unit <= s.length() / 2; unit++) {
            int count = calculate(0,unit,s,"",s.substring(0,unit),0);
            if (count <= answer) {
                answer = count;
            }
        }
        return answer;
    }
    
    public int calculate(int start, int unit, String s, String result, String str, int count) {
        if (start + unit > s.length()) {
            if(count > 1) {
                result += String.valueOf(count);
            }
            result += str;
            result += s.substring(start, s.length());
            return result.length();
        }
        String tempStr = s.substring(start, start + unit);
        if (!Objects.equals(tempStr, str)) {
            if(count > 1) {
                result += String.valueOf(count);
            }
            result += str;
            str = tempStr;
            count = 0;
        }
        count++;
        return calculate(start+unit, unit, s, result, str, count);
    }
}