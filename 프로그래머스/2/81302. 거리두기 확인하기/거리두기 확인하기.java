import java.util.*;

class Solution {
    
    public List<Integer> solution(String[][] places) {
        List<Integer> answer = new ArrayList<>();
        char[][] mapView;
        for (String[] place : places) {
            mapView = new char[5][5];
            for (int r = 0; r < 5; r++) {
                String row = place[r];
                for (int c = 0; c < 5; c++) {
                    mapView[r][c] = row.charAt(c);
                }
            }
            answer.add(calculateResult(mapView));
        }
        
        return answer;
    }
    private int calculateResult(char[][] mapView) {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (mapView[r][c] == 'P' && !isSafe(r, c, mapView)) {
                    return 0;
                }
            }
        }
        return 1;
    }
    
    private boolean isSafe(int r, int c, char[][] mapView) {
        boolean[][] visited = new boolean[5][5];
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        Queue<int[]> queue = new ArrayDeque<>();
        visited[r][c] = true;
        queue.offer(new int[]{r,c,0});
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nR = cur[0] + dr[i];
                int nC = cur[1] + dc[i];
                if (!(nR >= 0 && nR < 5 && nC >= 0 && nC < 5)) {
                    continue;
                }
                if (mapView[nR][nC] == 'X' || visited[nR][nC]) {
                    continue;
                }
                if (mapView[nR][nC] == 'P' && cur[2] <= 1) {
                    return false;
                }
                if (cur[2] >= 1) {
                    continue;
                }
                visited[nR][nC] = true;
                queue.offer(new int[]{nR, nC, cur[2] + 1});
            }
        }
        return true;
        
    }
}