class Solution {
    public long solution(int[] sequence) {
        long answer = Long.MIN_VALUE;
        long[][] dp = new long[sequence.length][2];
        int pulse0 = 1;
        int pulse1 = -1;
        dp[0][0] = -sequence[0];
        dp[0][1] = sequence[0];
        answer = Math.max(dp[0][0], dp[0][1]);
        for (int i = 1; i < sequence.length; i++) {
            dp[i][0] = Math.max(pulse0 * sequence[i], dp[i-1][0] + pulse0 * sequence[i]);
            dp[i][1] = Math.max(pulse1 * sequence[i], dp[i-1][1] + pulse1 * sequence[i]);
            long max = Math.max(dp[i][0], dp[i][1]);
            answer = Math.max(max, answer);
            pulse0 *= -1;
            pulse1 *= -1;
        }
        return answer;
    }
}