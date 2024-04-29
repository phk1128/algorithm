import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        long sum1 = (long) Arrays.stream(queue1).sum();
        long sum2 = (long) Arrays.stream(queue2).sum();
        long total = sum1 + sum2;
        
        if (total % 2 != 0) {
            return -1;
        }
        
        int limit = queue1.length * 3;
        long number = total / 2;
        Queue<Long> q1 = new ArrayDeque<>();
        Queue<Long> q2 = new ArrayDeque<>();
        
        for (int i = 0; i < queue1.length; i++) {
            q1.offer((long)queue1[i]);
            q2.offer((long)queue2[i]);
        }
        int count = 0;
        while (limit-- > 0) {
            if (sum1 > sum2) {
                if (!q1.isEmpty()) {
                    long num1 = q1.poll();
                    sum1 -= num1;
                    sum2 += num1;
                    q2.offer(num1);
                    count++;
                }
            }
            if (sum1 < sum2) {
                if (!q2.isEmpty()) {
                    long num2 = q2.poll();
                    sum1 += num2;
                    sum2 -= num2;
                    q1.offer(num2);
                    count++;
                }
            }
            if (sum1 == sum2) {
                return count;
            }
        }
        
        return answer;
    }
}