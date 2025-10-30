class Solution {
    public long solution(int w, int h) {
        long answer = 0;
        
        long y = (long) h;
        
        for (int i = 1; i < w; i++) {
            answer += (y * i) / w;
        }
        
        return answer * 2;
    }
}