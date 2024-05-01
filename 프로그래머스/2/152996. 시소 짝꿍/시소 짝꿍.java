import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Map<Double, Integer> weightCount = new HashMap<>();
        Arrays.sort(weights);
        
        for (int weight : weights) {
            double a = weight * (2.0 / 3.0);
            double b = weight * (1.0 / 2.0);
            double c = weight * (3.0 / 4.0);
            
            if (weightCount.containsKey((double) weight)) {
                answer += weightCount.get((double) weight);
            }
            
            if (weightCount.containsKey(a)) {
                answer += weightCount.get(a);
            }
            
            if (weightCount.containsKey(b)) {
                answer += weightCount.get(b);
            }
            
            if (weightCount.containsKey(c)) {
                answer += weightCount.get(c);
            }
            
            weightCount.put((double) weight, weightCount.getOrDefault((double) weight, 0) + 1);
            
            
        }
        return answer;
    }
}