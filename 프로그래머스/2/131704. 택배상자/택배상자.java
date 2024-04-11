import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        
        int idx = 0;
        for (int i = 1; i <= order.length; i++) {
            
            stack.push(i);
            
            while (!stack.isEmpty() && idx < order.length) {
                if (stack.peek() == order[idx]) {
                    stack.pop();
                    idx++;
                    answer++;
                } else {
                    break;
                }
            }
        }
        
        
        return answer;
    }
}