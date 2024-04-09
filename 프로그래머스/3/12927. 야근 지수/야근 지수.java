import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        
        
        for (int work : works) {
            queue.offer(work);
        }
        
        for (int i = 0; i < n; i++) {
            int work = queue.poll();
            if (work == 0) {
                return 0;
            } 
            queue.offer(work - 1);
        }
        while (!queue.isEmpty()) {
            int work = queue.poll();
            answer += (work * work);
        }
        return answer;
    }
}