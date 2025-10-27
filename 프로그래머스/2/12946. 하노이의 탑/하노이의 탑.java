import java.util.*;

class Solution {
    public int[][] solution(int n) {
        List<int[]> result = new ArrayList<>();
        Stack<int[]> stack = new Stack<>();
        
        stack.push(new int[]{n, 1, 2, 3});
        
        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int disks = current[0];
            int start = current[1];
            int mid = current[2];
            int end = current[3];
            
            if (disks == 1) {
                result.add(new int[]{start, end});
            } else {
                stack.push(new int[]{disks-1, mid, start, end});
                stack.push(new int[]{1, start, mid, end});
                stack.push(new int[]{disks-1, start, end, mid});
            }
        }
        
        return result.toArray(new int[result.size()][]);
    }
}