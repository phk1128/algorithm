import java.util.*;
import java.util.stream.*;

class Solution {
    private List<Integer> res;
    private List<Integer> lo;
    private List<Integer> reservedList;

    public int solution(int n, int[] lost, int[] reserve) {
        res = Arrays.stream(reserve).boxed().collect(Collectors.toList());
        lo = Arrays.stream(lost).boxed().collect(Collectors.toList());
        reservedList = new ArrayList<>();
        int answer = n - lost.length;
        
        for (int l : lost) {
            if (res.contains(l)) {
                lo.remove(Integer.valueOf(l));
                res.remove(Integer.valueOf(l));
                answer++;
            }
        }
        
        Collections.sort(res);
        Collections.sort(lo);

        while (!lo.isEmpty()) {
            int number = lo.remove(0);
            for (int r : res) {
                if (Math.abs(number - r) == 1 && !reservedList.contains(r)) {
                    reservedList.add(r);
                    answer++;
                    break;
                }
            }
        }
        return answer;
    }

}