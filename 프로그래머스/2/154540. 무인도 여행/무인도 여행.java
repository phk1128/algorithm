import java.util.*;
import java.util.stream.*;

class Solution {
    
    private boolean[][] visited;
    private int[][] mapView;
    private int[][] directions;
        
    public int[] solution(String[] maps) {
        int[] answer = new int[]{-1};
        visited = new boolean[maps.length][maps[0].length()];
        mapView = new int[maps.length][maps[0].length()];
        directions = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
        
        for (int r = 0; r < maps.length; r++) {
            for (int c = 0; c < maps[0].length(); c++) {
                char num = maps[r].charAt(c);
                if (Character.isDigit(num)) {
                    mapView[r][c] = Character.getNumericValue(num);
                }
            }
        }
        
        List<Integer> scores = new ArrayList<>();
        for (int r = 0; r < mapView.length; r++) {
            for (int c = 0; c < mapView[0].length; c++) {
                if (visited[r][c] || mapView[r][c] == 0) {
                    continue;
                }
                scores.add(getScore(r,c));
            }
        }
        
        if (!scores.isEmpty()) {
            Collections.sort(scores);
            answer = scores.stream().mapToInt(num -> num).toArray();
        }
        
        return answer;
    }
    
    public int getScore(int r, int c) {
        int score = mapView[r][c];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r,c});
        visited[r][c] = true;
        
        while (!queue.isEmpty()) {
            
            int[] current = queue.poll();
            int cR = current[0];
            int cC = current[1];
        
            for (int[] direction : directions) {
                int nR = cR + direction[0];
                int nC = cC + direction[1];
                if (!(nR >= 0 && nR < mapView.length && nC >= 0 && nC < mapView[0].length)) {
                    continue;
                }
                
                if (visited[nR][nC] || mapView[nR][nC] == 0) {
                    continue;
                }
                visited[nR][nC] = true;
                score += mapView[nR][nC];
                queue.offer(new int[]{nR,nC});
            }
            
        }
        
        return score;
    }
}