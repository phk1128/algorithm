import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";
        Map<String, int[]> pad = new HashMap<>();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                pad.put(String.valueOf(((r * 3) + c) + 1), new int[]{r, c});
            }
        }
        pad.put("*", new int[]{3, 0});
        pad.put("0", new int[]{3, 1});
        pad.put("#", new int[]{3, 2});
        
        // for (String key : pad.keySet()) {
        //     System.out.println(key);
        //     System.out.println(Arrays.toString(pad.get(key)));
        // }
        int[] left = new int[]{3, 0};
        int[] right = new int[]{3, 2};
        for (int i = 0; i < numbers.length; i++) {
            String target = String.valueOf(numbers[i]);
            int[] num = pad.get(target);
            if (Objects.equals(target, "1") || Objects.equals(target, "4")|| Objects.equals(target, "7")) {
                answer += "L";
                left = num;
                continue;
            }
            
            if (Objects.equals(target, "3") || Objects.equals(target, "6")|| Objects.equals(target, "9")) {
                answer += "R";
                right = num;
                continue;
            }
            
            int diffL = Math.abs(left[0] - num[0]) + Math.abs(left[1] - num[1]);
            int diffR = Math.abs(right[0] - num[0]) + Math.abs(right[1] - num[1]);
            
            if (diffL < diffR) {
                answer += "L";
                left = num;
                continue;
            }
            if (diffL > diffR) {
                answer += "R";
                right = num;
                continue;
            }
            if (Objects.equals(hand, "right")) {
                answer += "R";
                right = num;
            } else {
                answer += "L";
                left = num;
            }
        }
        
        return answer;
    }
}