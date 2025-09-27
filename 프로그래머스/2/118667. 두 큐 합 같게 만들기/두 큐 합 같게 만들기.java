import java.util.*;

class Solution {
    public int solution(int[] q1Arr, int[] q2Arr) {
        long s1 = Arrays.stream(q1Arr).sum();
        long s2 = Arrays.stream(q2Arr).sum();
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        fill(q1, q1Arr);
        fill(q2, q2Arr);
        
        int lim = (q1.size() + q2.size()) * 2;
        int cnt = 0;
        
        while (lim-- > 0) {
            if (s1 > s2 && !q1.isEmpty()) {
                int x = q1.poll();
                q2.offer(x);
                s1 -= x;
                s2 += x;
                cnt++;
            } else if (s1 < s2 && !q2.isEmpty()) {
                int x = q2.poll();
                q1.offer(x);
                s2 -= x;
                s1 += x;
                cnt++;
            } else if (s1 == s2) {
                return cnt;
            }
        }
        return -1;
    }
    
    private void fill(Queue<Integer> q, int[] arr) {
        for (int x : arr) q.offer(x);
    }
}
