import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        long sum1 = Arrays.stream(queue1).sum();
        long sum2 = Arrays.stream(queue2).sum();
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        offerNum(q1, queue1);
        offerNum(q2, queue2);
        int limit = (q1.size() + q2.size()) * 2;
        int count = 0;
        while (limit-- > 0) {
            if (sum1 > sum2 && !q1.isEmpty()) {
                int num = q1.poll();
                q2.offer(num);
                sum1 -= num;
                sum2 += num;
                count++;
            }
            
            if (sum1 < sum2 && !q2.isEmpty()) {
                int num = q2.poll();
                q1.offer(num);
                sum2 -= num;
                sum1 += num;
                count++;
            }
            
            if (sum1 == sum2) {
                answer = count;
                break;
            }
        }
        
        return answer;
    }
    
    private void offerNum(Queue<Integer> queue, int[] nums) {
        for (int num : nums) {
            queue.offer(num);
        }
    }
}