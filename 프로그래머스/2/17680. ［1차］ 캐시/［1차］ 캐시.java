import java.util.*;

class Solution {
    
    private static List<String> list;
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        list = new ArrayList<>();
        
        
        for (String city : cities) {
            if (cacheSize == 0) {
                answer += 5;
                continue;
            }
            
            city = city.toUpperCase();
            
            if (list.isEmpty()) {
                list.add(city);
                answer += 5;
                continue;
            }
            
            
            if (list.contains(city)) {
                list.remove(city);
                list.add(city);
                answer += 1;
                continue;
            }
            
            answer += 5;
            
            if (list.size() < cacheSize) {
                list.add(city);
                continue;
            }
            
            list.remove(0);
            list.add(city);
        }
        return answer;
    }
}