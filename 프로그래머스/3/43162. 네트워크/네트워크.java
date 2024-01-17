class Solution {
    private boolean[] visited;
    private int[][] computers;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        this.computers = computers;
        int answer = 0;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i,n);
                answer++;
            }
        }
        return answer;
    }
    
    public void dfs(int node, int n) {
        for (int i = 0; i < n; i++) {
            if (!visited[i] && computers[node][i] == 1) {
                visited[i] = true;
                dfs(i, n);
            }
        }
    }
}