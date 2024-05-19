import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        String[] splitS = s.replace("{{", "").replace("}}", "").split("\\}\\,\\{");
        Arrays.sort(splitS, (s1, s2) -> s1.length() - s2.length());
        
        List<String> result = new ArrayList<>();
        for (String str : splitS) {
            String[] strSplit = str.split(",");
            for (int i = 0; i < strSplit.length; i++) {
                String tmpStr = strSplit[i];
                if (result.contains(tmpStr)) {
                    continue;
                }
                result.add(tmpStr);
            }
        }
        
        answer = result.stream().mapToInt(Integer::parseInt).toArray();
        
        
        return answer;
    }
}
