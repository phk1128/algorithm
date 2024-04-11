class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        boolean[][] visited = new boolean[n + 1][m + 1];
        int[][] dp = new int[n + 1][m + 1];
        
        for (int[] p : puddles) {
            visited[p[1]][p[0]] = true;
        }
        
        dp[1][1] = 1;
        
        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= m; c++) {
                if (visited[r][c]) {
                    continue;
                }
                
                dp[r][c] += dp[r-1][c] % 1_000_000_007;
                dp[r][c] += dp[r][c-1] % 1_000_000_007;
            }
        }
        
        answer = (int) (dp[n][m]) % 1_000_000_007;
        
        return answer;
    }
}