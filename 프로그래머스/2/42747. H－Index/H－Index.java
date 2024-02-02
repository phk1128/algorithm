import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] citations) {
        List<Integer> citationList = Arrays.stream(citations).boxed().collect(Collectors.toList());
        int answer = 0;
        
        // Collections.sort(citationList, (c1,c2) -> {
        //     int diff1 = citationList.size() - c1;
        //     int diff2 = citationList.size() - c2;
        //     if (diff1 < 0) {
        //         return 1;
        //     }
        //     if (c1 < c2 && diff2 < 0) {
        //         return -1;
        //     }
        //     return diff1 - diff2;
        // });
        
        // int h = citationList.get(0);
        
        for (int i = citations.length; i >= 0; i--) {
            int count = 0;
            for (int j : citations) {
                if (i <= j) {
                    count++;
                }
            }
            
            if (count >= i) {
                answer = i;
                break;
            }
        }
        return answer;
    }
}