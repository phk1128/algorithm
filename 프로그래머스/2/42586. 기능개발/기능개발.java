import java.util.*;

class Solution {
    public List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int time = 0;
        int cnt = 0;
        int idx = 0;
        
        while (idx < progresses.length) {
            if (progresses[idx] + time * speeds[idx] >= 100) {
                idx++;
                cnt++;
            } else {
                if (cnt > 0) {
                    answer.add(cnt);
                    cnt = 0;
                }
                time++;
            }
        }
        answer.add(cnt);
        
        return answer;
    }
}