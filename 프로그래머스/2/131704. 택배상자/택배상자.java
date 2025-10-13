import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        Stack<Integer> stack = new Stack<>();
        
        int idx = 0;
        int i = 1;
        
        while (idx < order.length) {
            if (!stack.isEmpty() && stack.peek() == order[idx]) {
                stack.pop();
                idx++;
                answer++;
            }
            else if (i <= order.length) {
                stack.push(i);
                i++;
            }
            else {
                break;
            }
        }
        
        return answer;
    }
}