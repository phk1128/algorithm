import java.util.*;

class Solution {
    
    private static List<String> cache;
    
    public int solution(int cacheSize, String[] cityRequests) {
        int totalTime = 0;
        cache = new ArrayList<>();
        
        for (String currentCity : cityRequests) {
            if (cacheSize == 0) {
                totalTime += 5;
                continue;
            }
            
            currentCity = currentCity.toUpperCase();
            
            if (cache.isEmpty()) {
                cache.add(currentCity);
                totalTime += 5;
                continue;
            }
            
            if (cache.contains(currentCity)) {
                cache.remove(currentCity);
                cache.add(currentCity);
                totalTime += 1;
                continue;
            }
            
            totalTime += 5;
            
            if (cache.size() < cacheSize) {
                cache.add(currentCity);
                continue;
            }
            
            cache.remove(0);
            cache.add(currentCity);
        }
        return totalTime;
    }
}
