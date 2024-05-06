import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, (d1, d2) -> {
           if (d1[col-1] != d2[col-1]) {
               return d1[col-1] - d2[col-1];
           }
            return d2[0] - d1[0];
        });
        
        List<Integer> S = new ArrayList<>();
        
        for (int i = row_begin - 1; i < row_end; i++) {
            int[] d = data[i];
            int tmp = 0;
            for (int num : d) {
                tmp += (num % (i + 1));
            }
            S.add(tmp);
        }
        
        answer = S.get(0);
        for (int i = 1; i < S.size(); i++) {
            answer ^= S.get(i);
        }
        return answer;
    }
}