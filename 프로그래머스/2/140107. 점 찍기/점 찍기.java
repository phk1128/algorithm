class Solution {
    public long solution(int k, int d) {
        long answer = 1;
        answer += ((d / k) * 2);

        double pow = Math.pow(d, 2);
        for (int i = 1; i * k <= d; i++) {
            int tmp = i * k;
            answer += (int) Math.sqrt(pow - Math.pow(tmp, 2)) / k;
        }

        return answer;
    }
}