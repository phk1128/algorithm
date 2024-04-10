import java.util.*;

class Solution {
    
    private static int[] result;
    
    private static TreeMap<Integer, Integer> map;
    
    public int[] solution(String[] operations) {
        
        map = new TreeMap<>();
        solve(operations);
        
        int[] answer = result;
        return answer;
    }
    
    private static void solve(String[] operations) {
        
        for (String oper : operations) {
            
            String[] operSplit = oper.split(" ");
            String command = operSplit[0];
            int num = Integer.parseInt(operSplit[1]);
            
            if (Objects.equals(command, "I")) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                continue;
            }
            
            if (map.isEmpty()) {
                continue;
            }
            int key = 0;
            if (Objects.equals(command, "D")) {
                
                if (num == 1) {
                    key = map.lastKey();
                }
                if (num == -1) {
                    key = map.firstKey();
                }
            }
            
            if (map.get(key) > 1) {
                map.replace(key, map.get(key) - 1);
            } else {
                map.remove(key);
            }
        }
        
        if (map.isEmpty()) {
            result = new int[]{0,0};
        } else {
            result = new int[]{map.lastKey(), map.firstKey()};
        }
        
    }
}