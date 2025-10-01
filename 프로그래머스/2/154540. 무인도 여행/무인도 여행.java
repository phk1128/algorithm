import java.util.*;
import java.util.stream.*;
class Solution {
    
    private boolean[][] visited;
    private int[][] mapView;
    private int[][] directions;
        
    public int[] solution(String[] maps) {
        int[] answer = new int[]{-1};
        directions = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
        
        initializeMap(maps);
        
        List<Integer> scores = findAllIslands();
        
        if (!scores.isEmpty()) {
            Collections.sort(scores);
            answer = scores.stream().mapToInt(num -> num).toArray();
        }
        
        return answer;
    }
    
    private void initializeMap(String[] maps) {
        visited = new boolean[maps.length][maps[0].length()];
        mapView = new int[maps.length][maps[0].length()];
        
        for (int r = 0; r < maps.length; r++) {
            for (int c = 0; c < maps[0].length(); c++) {
                char num = maps[r].charAt(c);
                if (Character.isDigit(num)) {
                    mapView[r][c] = Character.getNumericValue(num);
                }
            }
        }
    }
    
    private List<Integer> findAllIslands() {
        List<Integer> scores = new ArrayList<>();
        
        for (int r = 0; r < mapView.length; r++) {
            for (int c = 0; c < mapView[0].length; c++) {
                if (!visited[r][c] && mapView[r][c] != 0) {
                    scores.add(getScore(r, c));
                }
            }
        }
        
        return scores;
    }
    
    public int getScore(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        
        int score = 0;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cR = current[0];
            int cC = current[1];
            
            score += mapView[cR][cC];
            
            for (int[] direction : directions) {
                int nR = cR + direction[0];
                int nC = cC + direction[1];
                
                if (isValidPosition(nR, nC) && !visited[nR][nC] && mapView[nR][nC] != 0) {
                    visited[nR][nC] = true;
                    queue.offer(new int[]{nR, nC});
                }
            }
        }
        
        return score;
    }
    
    private boolean isValidPosition(int r, int c) {
        return r >= 0 && r < mapView.length && c >= 0 && c < mapView[0].length;
    }
}