import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            long num = numbers[i];
            StringBuilder decimal = new StringBuilder(Long.toString(num, 2));
            boolean flag = false;
            for (int j = decimal.length() - 1; j >= 0; j--) {
                if (Objects.equals(decimal.charAt(j), '0')) {
                    decimal.setCharAt(j, '1');
                    if (j < decimal.length() - 1) {
                        decimal.setCharAt(j + 1, '0');
                    }
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                num = (num + 1) + num;
                decimal = new StringBuilder(Long.toString(num, 2));
                decimal.setCharAt(1, '0');
            }
            long result = Long.parseLong(decimal.toString(), 2);
            answer[i] = result;
        }
        return answer;
    }
}
// StringBuilder 사용
// long을 2진수로 바꿔서 뒤에서 부터 탐색
// 0을 만나면 1로 바꿔주고, 그 바로 뒤에 값을 0 으로 바꿔준다.
// 0을 만나지 않으면 (현재 값 + 1) + 현재값을 이진수로 바꾼다.
// 해당값의 인덱스 1 자리를 0 으로 바꾼다.