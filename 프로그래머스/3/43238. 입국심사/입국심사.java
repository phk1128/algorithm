import java.util.*;

class Solution {

    private long minTime;
    
    public long solution(int n, int[] times) {
        long answer = 0;
        Arrays.sort(times);
        long maxTime = (long)times[times.length - 1] * n;
        binarySearch(1, maxTime, n, times);
        answer = minTime;
        return answer;
    }
    
    private void binarySearch(long start, long end, int n, int[] times) {
        if (start >= end) {
            minTime = start;
            return;
        }
        
        long mid = (start + end) / 2;
        long count = getCount(mid, times, n);
        
        if (n > count) {
            binarySearch(mid + 1, end, n, times);
        }
        if (n <= count) {
            binarySearch(start, mid, n, times);
        }
    }
    
    private long getCount(long time, int[] times, int n) {
        long count = 0;
        
        for (int i = 0; i < times.length; i++) {
            count += time / times[i];
        }
        return count;
    }
}