import java.util.*;
import java.util.stream.*;
import java.util.stream.Collectors;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = {};
        List<Long> result = new ArrayList<>();
        
        for (long number : numbers) {
            
            StringBuilder bit = new StringBuilder(Long.toBinaryString(number));
            int idx = 0;
            boolean flag = false;
            for (int i = bit.length() - 1; i >= 0; i--) {
                if (Objects.equals(bit.charAt(i), '0')) {
                    idx = i;
                    flag = true;
                    break;
                }
            }
            
            if (flag) {
                bit.setCharAt(idx, '1');
                if (idx != bit.length() - 1) {
                    bit.setCharAt(idx+1, '0');
                }
            } else {
                bit.insert(0, '1');
                bit.setCharAt(1, '0');
            }
            
            result.add(Long.parseLong(bit.toString(), 2));
            
                
        }
        answer = result.stream().mapToLong(num -> num).toArray();
        return answer;
    }
}