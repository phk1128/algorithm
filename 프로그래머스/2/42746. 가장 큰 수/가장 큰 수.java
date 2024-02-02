import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "0";
        List<String> numberList = Arrays.stream(numbers)
            .mapToObj(num -> String.valueOf(num))
            .collect(Collectors.toList());
        
        Collections.sort(numberList, (num1, num2) -> {
            return -(num1 + num2).compareTo(num2 + num1);
        });
        
        for (String num : numberList) {
            if (!Objects.equals(num, "0")) {
                break;
            }
            return "0";
        }
        
        answer = String.join("", numberList);
        return answer;
    }
}