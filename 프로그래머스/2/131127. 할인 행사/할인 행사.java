import java.util.*;

class Solution {
    private static HashMap<String, Integer> wants;
    
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int sum = 0;
        wants = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wants.put(want[i], number[i]);
            sum += number[i];
        }
        
        for (int i = 0; i <= discount.length - sum; i++) {
            HashMap<String, Integer> tmpWants = new HashMap<>(wants);
            boolean flag = true;
            for (int j = i; j < i + sum; j++) {
                String d = discount[j];
                if (tmpWants.get(d) != null) {
                    if (tmpWants.get(d) != 0) {
                        tmpWants.replace(d, tmpWants.get(d) - 1);
                    }
                }
            }
            
            for (String w : want) {
                if (tmpWants.get(w) != 0) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                answer++;
            }
        }
    
        return answer;
    }
}