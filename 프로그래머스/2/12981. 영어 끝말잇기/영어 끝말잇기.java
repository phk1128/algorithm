import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {};
        
        Stack<String> stack = new Stack<>();
        stack.push(words[0]);
        int num = 0;
        int turn = 0;
        for (int i = 1; i < words.length; i++) {
            
            
            if (stack.contains(words[i])) {
                num = (i+1) % n;
                turn = (int) Math.ceil((double)(i + 1) / n);
                if (num == 0) {
                    num = n;
                }
                break;
            }
            
            String tmp = stack.peek();
            String word = words[i];
            
            if (!Objects.equals(tmp.charAt(tmp.length() - 1), word.charAt(0))) {
                num = (i + 1) % n;
                turn = (int) Math.ceil((double)(i + 1) / n);
                if (num == 0) {
                    num = n;
                }
                
                break;
            }
            
            stack.push(word);       
        }
        
        answer = new int[]{num, turn};
        return answer;
    }
    
}