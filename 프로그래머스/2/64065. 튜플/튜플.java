import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        String[] splitS = s.replace("{{", "").replace("}}", "").split("\\}\\,\\{");
        Arrays.sort(splitS, (s1, s2) -> s1.length() - s2.length());
        
        Map<String, Integer> map = new HashMap<>();
        for (String str : splitS) {
            String[] splitStr = str.split(",");
            for (int i = 0; i < splitStr.length; i++) {
                map.put(splitStr[i], map.getOrDefault(splitStr[i],0) + 1);
            }
        }
        
        List<String> result = new ArrayList<>(map.keySet());
        Collections.sort(result, (r1, r2) -> map.get(r2) - map.get(r1));
        answer = result.stream().mapToInt(Integer::parseInt).toArray();
        
        return answer;
    }
}
