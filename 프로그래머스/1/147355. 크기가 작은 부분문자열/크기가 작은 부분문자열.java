public class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        int tL = t.length();
        int pL = p.length();
        
        for (int i = 0; i < tL; i++) {
            if (i + pL <= tL) {
                String substring = t.substring(i, i + pL);
                
                if (Long.parseLong(substring) <= Long.parseLong(p)) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}