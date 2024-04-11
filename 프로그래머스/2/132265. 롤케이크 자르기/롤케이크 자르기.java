import java.util.*;

class Solution {
    
    private static Map<Integer, Integer> map;
    private static Set<Integer> set;
    private static int count;
    private static int answer;
    
    public int solution(int[] topping) {
        map = new HashMap<>();
        set = new HashSet<>();
        count = 0;
        answer = 0;
        
        solve(topping);
        
            
        return answer;
    }
    
    private static void solve(int[] topping) {
        
        for (int i = 0; i < topping.length; i++) {
            
            int value = map.getOrDefault(topping[i],0);
            if (value == 0) {
                count++;
            }
            map.put(topping[i], value + 1);
            
        }
        
        
        
        for (int i = topping.length - 1; i >= 1; i--) {
            
            set.add(topping[i]);
            
            int value = map.get(topping[i]);
            
            if (value == 1) {
                count--;
            }
            
            map.replace(topping[i], value - 1);
            
            if (count == set.size()) {
                answer++;
            }
            
        }
    }
    
}