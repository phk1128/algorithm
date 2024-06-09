import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(String dartResult) {
        int answer = 0;
        String[] str1 = dartResult.split("[^0-9]");
        String[] str2 = dartResult.split("[0-9]");
        int[] nums = new int[3];
        String[] ops = new String[3];
        int idx = 0;
        for (int i = 0; i < str1.length; i++) {
            if (!Objects.equals(str1[i], "")) {
                nums[idx++] = Integer.parseInt(str1[i]);
            }
        }
        idx = 0;
        for (int i = 0; i < str2.length; i++) {
            if (!Objects.equals(str2[i], "")) {
                ops[idx++] = str2[i];
            }
        }
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            int num = nums[i];
            String op = ops[i];
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