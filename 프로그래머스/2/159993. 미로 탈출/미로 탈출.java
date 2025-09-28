import java.util.*;

class Solution {
    
    private int[][] grid;
    private int n, m;
    
    public int solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        grid = new int[n][m];
        
        int[] start = null;
        int[] lever = null;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = maps[i].charAt(j);
                if (ch == 'S') {
                    grid[i][j] = 1;
                    start = new int[]{i, j};
                } else if (ch == 'L') {
                    grid[i][j] = 2;
                    lever = new int[]{i, j};
                } else if (ch == 'E') {
                    grid[i][j] = 3;
                } else if (ch == 'X') {
                    grid[i][j] = -1;
                }
            }
        }
        
        int timeToLever = bfs(start[0], start[1], 2);
        int timeToExit = (timeToLever == -1) ? -1 : bfs(lever[0], lever[1], 3);
        
        if (timeToLever == -1 || timeToExit == -1) return -1;
        return timeToLever + timeToExit;
    }
    
    private int bfs(int r, int c, int target) {
        boolean[][] visited = new boolean[n][m];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c, 0});
        visited[r][c] = true;
        
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], dist = cur[2];
            
            if (grid[x][y] == target) return dist;
            
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (visited[nx][ny] || grid[nx][ny] == -1) continue;
                
                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny, dist + 1});
            }
        }
        
        return -1;
    }
}
