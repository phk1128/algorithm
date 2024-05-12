import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < enemy.length; i++) {
            n -= enemy[i];
            queue.offer(enemy[i]);
            if (n < 0) {
                if (!queue.isEmpty() && k > 0) {
                    n += queue.poll();
                    k--;
                } else {
                    answer = i;
                    break;
                } 
            } 
        }
        return answer;
    }
}