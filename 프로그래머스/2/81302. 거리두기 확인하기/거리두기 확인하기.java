import java.util.*;

class Solution {
    
    private int[][] mapView;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            String[] place = places[i];
            mapView = new int[5][5];
            int people = 0;
            for (int r = 0; r < 5; r++) {
                for (int c = 0; c < 5; c++) {
                    int num = 0;
                    char el = place[r].charAt(c);
                    if (Objects.equals(el, 'P')) {
                        num = 2;
                        people++;
                    }
                    if (Objects.equals(el, 'X')) {
                        num = -1;
                    }
                    mapView[r][c] = num;
                }
            }
            
            answer[i] = getResult(people);
            
        }
        return answer;
    }
    
    private int getResult(int people) {
        
        if (people == 0) {
            return 1;
        }
        
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (mapView[r][c] == 2 && !isSafe(r,c)) {
                    return 0;
                }
            }
        }
        
        return 1;
    }
    
    private boolean isSafe(int r, int c) {
        boolean[][] visited = new boolean[5][5];
        int[][] directions = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r,c,0});
        visited[r][c] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cR = current[0];
            int cC = current[1]; 
            int count = current[2];
            
            for (int[] direction : directions) {
                int newR = cR + direction[0];
                int newC = cC + direction[1];
                
                if (!(newR >= 0 && newR < 5 && newC >= 0 && newC < 5)) {
                    continue;
                }
                
                if (visited[newR][newC] || mapView[newR][newC] == -1) {
                    continue;
                }
    
                if (mapView[newR][newC] == 2 && count <= 1) {
                    return false;
                }
                
                if (count >= 1) {
                    continue;
                }
          
                visited[newR][newC] = true;
                queue.offer(new int[]{newR, newC, count + 1});
            }
        }
        
        return true;
    }
}