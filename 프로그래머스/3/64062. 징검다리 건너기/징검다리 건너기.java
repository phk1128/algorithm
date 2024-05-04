import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = Integer.MAX_VALUE;
        
        Deque<Integer> queue = new ArrayDeque<>();
        
        for (int i = 0; i < stones.length; i++) {
            
            while (!queue.isEmpty() && queue.peekFirst() <= i - k) {
                queue.pollFirst();
            }
            
            while (!queue.isEmpty() && stones[queue.peekLast()] < stones[i]) {
                queue.pollLast();
            }
            queue.offer(i);
            
            if (i >= k - 1) {
                answer = Math.min(answer, stones[queue.peekFirst()]);
            }
        }
        return answer;
    }
}