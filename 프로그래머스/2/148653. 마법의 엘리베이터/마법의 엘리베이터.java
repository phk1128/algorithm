import java.util.*;

class Solution {
    private static int answer;
    public int solution(int storey) {
        answer = 0;
        recursiveSolve(storey, 0, 1);
        
        return answer;
    }
    
    private static void recursiveSolve(int storey, int count, int depth) {
        
        if (storey == 0) {
            answer = count;
            return;
        }
        
        String str = String.valueOf(storey);
        int len = str.length();
        int num = Integer.parseInt(String.valueOf(str.charAt(len - depth)));
        int pow = (int) Math.pow(10, depth - 1);
        
        if (num == 5) {
            if (depth < len) {
                int nextNum = Integer.parseInt(String.valueOf(str.charAt(len - depth - 1)));
                if (nextNum >= 5) {
                    storey += (num * pow);
                } else {
                    storey -= (num * pow);
                }
            }
            if (depth == len) {
                storey -= (num * pow);
            }
        }
        
        if (num < 5) {
            storey -= (num * pow);
        }
        
        if (num > 5) {
            num = 10 - num;
            storey += (num * pow);
        }
        
            
        recursiveSolve(storey, count + num, depth + 1);
        
    }
}