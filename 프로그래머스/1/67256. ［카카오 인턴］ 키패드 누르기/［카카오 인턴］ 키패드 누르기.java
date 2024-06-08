import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        Map<Integer, int[]> pad = new HashMap<>();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                pad.put(((r * 3) + c) + 1, new int[]{r, c});
            }
        }
        
        pad.put(0, new int[]{3, 1});
        
        int[] left = new int[]{3, 0};
        int[] right = new int[]{3, 2};
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            int[] pos = pad.get(num);
            if (num == 1 || num == 4|| num == 7) {
                answer += "L";
                left = pos;
                continue;
            }
            
            if (num == 3 || num == 6|| num == 9) {
                answer += "R";
                right = pos;
                continue;
            }
            
            int diffL = Math.abs(left[0] - pos[0]) + Math.abs(left[1] - pos[1]);
            int diffR = Math.abs(right[0] - pos[0]) + Math.abs(right[1] - pos[1]);
            
            if (diffL < diffR) {
                answer += "L";
                left = pos;
                continue;
            }
            if (diffL > diffR) {
                answer += "R";
                right = pos;
                continue;
            }
            if (Objects.equals(hand, "right")) {
                answer += "R";
                right = pos;
            } else {
                answer += "L";
                left = pos;
            }
        }
        
        return answer;
    }
}