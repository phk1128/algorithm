import java.util.*;
import java.util.stream.*;
class Solution {
    private List<int[]> result;
    public int[][] solution(int n) {
        int[][] answer = {};
        result = new ArrayList<>();
        
        recursiveSolve(1,2,3,n);
        
        answer = new int[result.size()][];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    private void recursiveSolve(int start, int mid, int end, int n) {
        
        if (n == 1) {
            result.add(new int[]{start, end});
            return;
        }
        
        recursiveSolve(start, end, mid, n-1);
        result.add(new int[]{start, end});
        recursiveSolve(mid, start, end, n-1);
        
    }
}