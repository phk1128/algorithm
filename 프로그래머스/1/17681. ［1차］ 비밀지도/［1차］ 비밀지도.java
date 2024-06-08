import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            int num = arr1[i] | arr2[i];
            String decimal = Integer.toString(num, 2);
            decimal = decimal.replaceAll("1", "#");
            decimal = decimal.replaceAll("0", " ");
            while (decimal.length() < n) {
                decimal = " " + decimal;
            }
            answer[i] = decimal;
        }
        return answer;
    }
}