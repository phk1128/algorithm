import java.util.*;

class Solution {
    
    private int answer;
    
    public int solution(int n, int k, int[] enemy) {
        answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < enemy.length; i++) {
            pq.offer(enemy[i]);
            
            if (pq.size() > k) {
                n -= pq.poll();
            }
            
            if (n < 0) {
                answer = i;
                return answer;
            }
        }
        
        answer = enemy.length;
        return answer;
    }
}