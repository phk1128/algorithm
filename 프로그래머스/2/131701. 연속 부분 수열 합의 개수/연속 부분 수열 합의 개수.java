import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 1;
        Set<Integer> set = new HashSet<>();
        
        
        for (int i = 0; i < elements.length-1; i++) {
            for (int j = 0; j < elements.length; j++) {
                int tmp = 0;
                for (int k = 0; k <= i; k++) {
                    int idx = (j + k) % elements.length;
                    tmp += elements[idx];
                }
                set.add(tmp);
            }
            
        }
        
        answer += set.size();
        
        return answer;
    }
}