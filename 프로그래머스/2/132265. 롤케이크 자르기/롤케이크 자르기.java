import java.util.*;

class Solution {
    
    private static Map<Integer, Integer> map;
    private static Set<Integer> set;
    private static int cnt;
    private static int ans;
    
    public int solution(int[] topping) {
        map = new HashMap<>();
        set = new HashSet<>();
        cnt = 0;
        ans = 0;
        
        solve(topping);
        
            
        return ans;
    }
    
    private static void solve(int[] topping) {
        
        for (int i = 0; i < topping.length; i++) {
            
            int value = map.getOrDefault(topping[i],0);
            if (value == 0) {
                cnt++;
            }
            map.put(topping[i], value + 1);
            
        }
        
        
        
        for (int i = topping.length - 1; i >= 1; i--) {
            
            set.add(topping[i]);
            
            int value = map.get(topping[i]);
            
            if (value == 1) {
                cnt--;
            }
            
            map.replace(topping[i], value - 1);
            
            if (cnt == set.size()) {
                ans++;
            }
            
        }
    }
    
}