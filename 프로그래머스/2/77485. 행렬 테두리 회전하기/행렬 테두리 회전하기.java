import java.util.*;

class Solution {
    
    private static int[][] mapView;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        mapView = new int[rows + 1][ columns + 1];
        int num = 1;
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= columns; c++) {
                mapView[r][c] = num++;
            }
        }
        
        int idx = 0;
        answer = new int[queries.length];
        for (int[] q : queries) {
            answer[idx++] = calculateMinNum(q);
        }
        return answer;
    }
    
    private int calculateMinNum(int[] q) {
        int sR = q[0];
        int sC = q[1];
        int eR = q[2];
        int eC = q[3];
        int tmp = mapView[sR][sC];
        int min = Integer.MAX_VALUE;
        
        for (int r = sR; r < eR; r++) {
            min = Math.min(min, mapView[r][sC]);
            mapView[r][sC] = mapView[r + 1][sC];
        }
        
        for (int c = sC; c < eC; c++) {
            min = Math.min(min, mapView[eR][c]);
            mapView[eR][c] = mapView[eR][c + 1];
        }
        
        for (int r = eR; r > sR; r--) {
            min = Math.min(min, mapView[r][eC]);
            mapView[r][eC] = mapView[r - 1][eC];
        }
        
        for (int c = eC; c > sC; c--) {
            min = Math.min(min, mapView[sR][c]);
            mapView[sR][c] = mapView[sR][c - 1];
        }
        
        min = Math.min(min, tmp);
        mapView[sR][sC +1] = tmp;
        
        return min;
        
    }
}