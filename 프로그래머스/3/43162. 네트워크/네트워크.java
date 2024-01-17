class Solution {
    private boolean[] visited;
    private int answer = 0;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(computers,i,n);
                answer++;
            }
        }
        return answer;
    }
    
    public void dfs(int[][] computers,int node, int n) {
        for (int i = 0; i < n; i++) {
            if (!visited[i] && computers[node][i] == 1) {
                visited[i] = true;
                dfs(computers,i, n);
            }
        }
    }
}