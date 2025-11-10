import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = {};
        answer = new int[2];
        int kind = (int) Arrays.stream(gems).distinct().count();
        int s = 0;
        int count = 0;
        int min = Integer.MAX_VALUE;
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < gems.length; i++) {
            if (!map.containsKey(gems[i])) {
                count++;
            }
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            
            while (map.get(gems[s]) > 1) {
                map.replace(gems[s], map.get(gems[s]) - 1);
                s++;
            }
            
            if (kind == count) {
                if (i - s < min) {
                    min = i - s;
                    answer[0] = s + 1;
                    answer[1] = i + 1;
                }
            }
        }
        return answer;
    }
}