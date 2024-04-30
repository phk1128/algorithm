import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        Queue<Integer> queue = new ArrayDeque<>();
        
        int sum = 0;
        int idx = 0;
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < sequence.length; i++) {
            
            int num = sequence[i];
            queue.offer(num);
            sum += num;
            
            while (sum > k) {
                sum -= queue.poll();
                idx++;
            }
            
            if (sum == k) {
                if (len > i - idx) {
                    answer[0] = idx;
                    answer[1] = i;
                    len = i - idx;
                }
            }
        }
        return answer;
    }
}