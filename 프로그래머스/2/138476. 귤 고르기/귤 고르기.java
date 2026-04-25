import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        // 크기별 개수 카운팅
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int t : tangerine) {
            countMap.put(t, countMap.getOrDefault(t, 0) + 1);
        }
        
        // 개수 기준 내림차순 정렬
        List<Integer> counts = new ArrayList<>(countMap.values());
        counts.sort(Collections.reverseOrder());
        
        int types = 0;
        int sum = 0;
        for (int cnt : counts) {
            sum += cnt;
            types++;
            if (sum >= k) break;
        }
        return types;
    }
}