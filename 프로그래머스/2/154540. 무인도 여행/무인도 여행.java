import java.util.*;
import java.util.stream.*;

class Solution {
    
    private boolean[][] visited;
    private int[][] mapView;
    private int[][] directions;
    
    public int[] solution(String[] maps) {
        int[] answer = {};
        visited = new boolean[maps.length][maps[0].length()];
        mapView = new int[maps.length][maps[0].length()];
        directions = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        
        for (int r = 0; r < maps.length; r++) {
            for (int c = 0; c < maps[0].length(); c++) {
                char num = maps[r].charAt(c);
                if (Character.isDigit(num)) {
                    mapView[r][c] = Character.getNumericValue(num);
                }
            }
        }
        List<Integer> result = new ArrayList<>();
        for (int r = 0; r < mapView.length; r++) {
            for (int c = 0; c < mapView[0].length; c++) {
                if (visited[r][c] || mapView[r][c] == 0) {
                    continue;
                }
                int score = getScore(r,c,0);
                if (score > 0) {
                    result.add(score);
                }
            }
        }
        
        if (!result.isEmpty()) {
            Collections.sort(result);
            answer = result.stream().mapToInt(num -> num).toArray();
        } else {
            answer = new int[]{-1};
        }
        
        return answer;
    }
    
    private int getScore(int r, int c, int count) {
        
        visited[r][c] = true;
        count += mapView[r][c];
        
        for (int[] direction : directions) {
            int newR = r + direction[0];
            int newC = c + direction[1];
            
            if (!(newR >= 0 && newR < mapView.length && newC >= 0 && newC < mapView[0].length)) {
                continue;
            }
            if (visited[newR][newC] || mapView[newR][newC] == 0) {
                continue;
            }
            count = getScore(newR, newC, count);
        }
        
        return count;
        
    }
}