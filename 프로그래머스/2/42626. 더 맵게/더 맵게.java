import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = -1;
        int result = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for (int sc : scoville) {
            queue.offer(sc);
        }
        
        while (!queue.isEmpty()) {
            int min = queue.poll();
            
            if (min >= K) {
                return result;
            }
            
            if (!queue.isEmpty()) {
                queue.offer(min + (queue.poll()*2));
                result++;
            }
        }
        
        return answer;
    }
}