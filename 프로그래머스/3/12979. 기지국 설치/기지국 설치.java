class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int start = 0;
        for (int i = 0; i < stations.length; i++) {
            int left = stations[i] - w - 1;
            int right = stations[i] + w;
            
            if (start >= left) {
                start = right;
                continue;
            }
            answer += getCount(start, left, w);
            start = right;
            
        }
        
        if (stations[stations.length - 1] + w < n) {
            answer += getCount(stations[stations.length - 1] + w, n, w);;
        }

        return answer;
    }
    
    private static int getCount(int start, int end, int w) {
        
        int diff = (end - start);
        int len = (w*2) + 1;
        int count = diff / len;
        int r = diff % len;
    
        if (r != 0) {
            count++;
        }
        
        return count;
        
    }
}