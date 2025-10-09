import java.util.*;
import java.util.stream.*;

class Solution {
    public List<Integer> solution(String s) {
        String[] splitS = s.replaceAll("[^0-9|,]", "").split(",");
        Map<String, Integer> map = new HashMap<>();
        for (String str : splitS) {
            map.put(str, map.getOrDefault(str,0) + 1);
        }
        
        List<String> result = new ArrayList<>(map.keySet());
        Collections.sort(result, (r1, r2) -> map.get(r2) - map.get(r1));
        
        return result.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
