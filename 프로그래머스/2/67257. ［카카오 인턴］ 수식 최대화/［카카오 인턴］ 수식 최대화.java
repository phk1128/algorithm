import java.util.*;

class Solution {
    
    private long[] nums;
    private int[][] combi;
    private char[] ops;
    
    public long solution(String expression) {
        long answer = 0;
        nums = Arrays.stream(expression.split("[\\+\\-\\*]")).mapToLong(Long::parseLong).toArray();
        ops = expression.replaceAll("\\d", "").toCharArray();
        combi = new int[][] {{0,1,2}, {0,2,1}, {1,0,2}, {1,2,0}, {2,0,1}, {2,1,0}}; // idx0 = +, idx1 = -, idx2 = *
        
        for (int c = 0; c < combi.length; c++) {
            answer = Math.max(answer, getResult(c));
        }
        
        return answer;
    }
    
    private long getResult(int c) {
        
        Stack<Long> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        numStack.push(nums[0]);
        
        for (int i = 1; i < nums.length; i++) {
            while (!opStack.isEmpty() && getPriority(ops[i-1],c) >= getPriority(opStack.peek(),c)) {
                long a = numStack.pop();
                long b = numStack.pop();
                numStack.push(calculate(b, a, opStack.pop()));
            }
            
            numStack.push(nums[i]);
            opStack.push(ops[i-1]);
        }
        
        while (!opStack.isEmpty()) {
            long a = numStack.pop();
            long b = numStack.pop();
            numStack.push(calculate(b, a , opStack.pop()));
        }
        
        return Math.abs(numStack.pop());
    }
    
    private long calculate(long a, long b, char op) {
        if (Objects.equals(op, '+')) {
            return a + b;
        }
        
        if (Objects.equals(op, '-')) {
            return a - b;
        }
        
        return a * b;
    }
    
    private int getPriority(char op, int c) {
        
        int idx = -1;
        if (Objects.equals(op, '+')) {
            idx = 0;
        }
        if (Objects.equals(op, '-')) {
            idx = 1;
        }
        if (Objects.equals(op, '*')) {
            idx = 2;
        }
        return combi[c][idx];
    }
}