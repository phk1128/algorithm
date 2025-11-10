import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a1, a2) -> a1 - a2);
        for (int s : scoville) {
            queue.offer(s);
        }
        
        while (!queue.isEmpty()) {
            int min = queue.poll();
            if (min >= K) {
                return answer;
            }
            if (!queue.isEmpty()) {
                queue.offer(min + queue.poll() * 2);
                answer++;
            }
        }
        return -1;
    }
}