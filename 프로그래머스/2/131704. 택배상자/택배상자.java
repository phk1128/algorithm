import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        
        int idx = 0;
        for (int i = 1; i <= order.length; i++) {
            boolean flag = false;
            
            if (order[idx] == i) {
                answer++;
                idx++;
                flag = true;
            }
            
            
            while (!stack.isEmpty() && idx < order.length) {
                if (stack.peek() == order[idx]) {
                    stack.pop();
                    idx++;
                    answer++;
                } else {
                    break;
                }
            }
            
            if (!flag) {
                stack.push(i);
            }
        }
        
        
        return answer;
    }
}