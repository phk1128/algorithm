class Solution {
    public int solution(int n) {
        int answer = 0;

        for (int i = 1; i <= n; i++) {
            int sum = n;
            for (int j = i; j <= n; j++) {
                sum -= j;
                if (sum == 0) {
                    answer++;
                    break;
                } else if (sum < 0) {
                    break;
                }
            }
        }

        return answer;
    }
}
