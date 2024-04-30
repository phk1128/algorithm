import java.util.*;

class Solution {
    private static int answer;
    public int solution(int storey) {
        answer = 0;
        recursiveSolve(storey, 0);
        
        return answer;
    }
    
    private static void recursiveSolve(int storey, int count) {
        
        if (storey == 0) {
            answer = count;
            return;
        }
        int num = storey % 10;
        storey /= 10;
        
        if (num == 5) {
            if (storey % 10 >= 5) {
                storey++;
            }
        }
        
        if (num > 5) {
            num = 10 - num;
            storey++;
        }
        
        recursiveSolve(storey, count + num);
        
    }
}