import java.util.*;

class Solution {
    private static StringBuilder sb;
    public int solution(String s) {
        
        int answer = 0;
        
        sb = new StringBuilder(s);
        
        if (check()) {
            answer++;
        }
        
        
        for (int i = 0; i < sb.length()-1; i++) {
            turn();
            if (check()) {
                answer++;
            }
        }
            
            
        return answer;
    }
    
    private static void turn() {
        
        StringBuilder tmpSb = new StringBuilder();
        
        String tmp = String.valueOf(sb.charAt(0));
        
        for (int i = 1; i < sb.length(); i++) {
            tmpSb.append(String.valueOf(sb.charAt(i)));
        }
        tmpSb.append(tmp);
        
        sb = tmpSb;
    }
    
    private static boolean check() {
        
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
                continue;
            }
            
            char tmp = stack.peek();
            
            if (Objects.equals(tmp, '[') && Objects.equals(c, ']')) {
                stack.pop();
                continue;
            }
            
            if (Objects.equals(tmp, '(') && Objects.equals(c, ')')) {
                stack.pop();
                continue;
            }
            
            if (Objects.equals(tmp, '{') && Objects.equals(c, '}')) {
                stack.pop();
                continue;
            }
            
            stack.push(c);
        }
        
        return stack.isEmpty();
        
    }
    
    
}