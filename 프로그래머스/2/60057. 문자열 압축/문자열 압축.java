import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = s.length();
        
        for (int unit = 1; unit <= s.length() / 2; unit++) {
            int count = calculate(unit, s);
            if (count <= answer) {
                answer = count;
            }
        }
        return answer;
    }
    
    public int calculate(int unit, String s) {
        StringBuilder result = new StringBuilder();
        String str = s.substring(0, unit);
        int count = 1;
        
        int start = unit;
        while (start < s.length()) {
            int end = Math.min(start + unit, s.length());
            String tempStr = s.substring(start, end);
            
            if (end - start == unit && tempStr.equals(str)) {
                count++;
                start += unit;
            } else {
                if (count > 1) {
                    result.append(count);
                }
                result.append(str);
                str = tempStr;
                count = 1;
                start = end;
            }
        }
        
        if (count > 1) {
            result.append(count);
        }
        result.append(str);
        
        return result.length();
    }
}