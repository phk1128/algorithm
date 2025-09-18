import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] ans = new int[numbers.length];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < numbers.length; i++) {
            
            while (!stack.isEmpty() && numbers[i] > numbers[stack.peek()]) {
                ans[stack.pop()] = numbers[i];
            }
            
            stack.push(i);
        }
        return ans;
    }
}