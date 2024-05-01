import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        Set<String> set = new HashSet<>(Arrays.asList(gems));
        Map<String, Integer> map = new HashMap<>();
        int kind = set.size();
        int start = 0;
        int count = 0;
        int min = Integer.MAX_VALUE;
        
        for (int i = 0; i < gems.length; i++) {
            if (!map.containsKey(gems[i])) {
                count++;
            }
            
            map.put(gems[i], map.getOrDefault(gems[i], 0) + 1);
            
            while (map.get(gems[start]) > 1) {
                map.replace(gems[start], map.get(gems[start]) - 1);
                start++;
            }
            
            if (count == kind) {
                if (min > i - start) {
                    min = i - start;
                    answer[0] = start + 1;
                    answer[1] = i + 1;
                }
            }
            
        }
        
        return answer;
    }
}