import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        int[] answer = new int[n];
        List<Integer> nToList = new ArrayList<>();
        
        for (int i = 1; i <= n; i++) {
            nToList.add(i);
        }
        
        int index = 0;
        while (n != 0) {
            n -= 1;
            long num = factorial(n);
            int tmp = nToList.remove((int)((k - 1) / num));
            answer[index++] = tmp;
            k = k % num;
            
            if (k == 0 && n > 0) {
                k = num;
            }
        }
        
        return answer;
    }
    
    private long factorial(int num) {
        if (num == 0) {
            return 1;
        }
        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
}