class Solution {
    
    private int answer;
    
    public int solution(int[] stones, int k) {
        answer = 0;
        binarySearch(0, 200_000_000, stones, k);
        return answer;
    }
    
    private void binarySearch(int start, int end, int[] stones, int k) {
        
        if (start >= end) {
            return;
        }
        
        int mid = (start + end) / 2;
        boolean flag = true;
        int count = 0;
        for (int stone : stones) {
            if (stone - mid < 0) {
                count++;
            } else {
                count = 0;
            }
            
            if (count >= k) {
                flag = false;
                break;
            }
        }
        
        if (flag) {
            answer = Math.max(answer, mid);
            binarySearch(mid+1, end, stones, k);
        } else {
            binarySearch(start, mid, stones, k);
        }
        
        
    }
}