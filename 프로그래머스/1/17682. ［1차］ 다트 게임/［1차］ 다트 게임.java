import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        String[] ops = dartResult.split("[0-9]{1,2}");
        String[] nums = dartResult.split("[SDT][*#]?");
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            int num = Integer.parseInt(nums[i]);
            String op = ops[i + 1];
            if (op.contains("D")) {
                num = (int) Math.pow(num, 2);
            }
            if (op.contains("T")) {
                num = (int) Math.pow(num, 3);
            }
            if (op.contains("#")) {
                num *= -1;
            }
            if (op.contains("*")) {
                num *= 2;
                if (i >= 1) {
                    result[i-1] *= 2;
                }
            }
            result[i] = num;
        }
        answer = Arrays.stream(result).sum();
        return answer;
    }
}
//다트는 총 3번의 기회
//연산자와 숫자의 개수는 항상 같음
//스타상, 아차상은 있을수도 없을수도 있다.
//스타상 당첨 시 해당 점수와 바로 전에 얻은 점수를 각 2배
//첫번째에 스타상이 나올 시 첫번째 숫자만 2배
//아차상은 해당 점수는 마이너스