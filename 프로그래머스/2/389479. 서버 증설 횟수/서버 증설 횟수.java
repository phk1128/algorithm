import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int cur = 0;
        
        for(int i = 0; i < 24; i++){
            while(!pq.isEmpty() && pq.peek()[0] == i){
                cur -= pq.poll()[1];
            }
            
            int need = players[i] / m;
            int diff = need - cur;
            
            if(diff > 0){
                cur += diff;
                answer += diff;
                pq.add(new int[]{i + k, diff});
            }
        }
        
        return answer;
    }
}