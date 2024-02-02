import java.util.*;
import java.util.stream.*;

class Solution {
    private List<Integer> result;
    public int[] solution(int[] prices) {
        int[] answer = {};
        int len = prices.length;
        result = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            int count = 0;
            for (int j = i + 1; j < len; j++) {
                count++;
                if (prices[i] > prices[j])
                    break;
            }
            result.add(count);
        }
        answer = result.stream().mapToInt(n -> n).toArray();
        return answer;
    }
}