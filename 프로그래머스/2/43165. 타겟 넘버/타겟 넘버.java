import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        List<Integer> covertedNumbers = Arrays.stream(numbers).boxed().collect(Collectors.toList());
        answer = dfs(covertedNumbers,0,0,target);
        return answer;
    }
    
    public int dfs(List<Integer> numbers, int result, int count, int target) {
        if (count == numbers.size()) {
            if (result == target) {
                return 1;
            }
            return 0;
        }
        return dfs(numbers, result + numbers.get(count), count + 1, target) + 
            dfs(numbers, result - numbers.get(count), count + 1, target);
    }
    
}