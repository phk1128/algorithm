import java.util.*;

class Solution {
    
    private int[][] mapView;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        mapView = new int[rows + 1][columns + 1];
        
        int num = 1;
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= columns; c++) {
                mapView[r][c] = num++;
            }
        }
        
        int idx = 0;
        for (int[] query : queries) {
            answer[idx++] = getMinNum(query);
        }
        
        return answer;
    }
    
    private int getMinNum(int[] query) {
        
        int num = Integer.MAX_VALUE;
        int startR = query[0];
        int startC = query[1];
        int endR = query[2];
        int endC = query[3];
        int tmp = mapView[startR][startC];
        
        // 왼쪽
        for (int r = startR + 1; r <= endR; r++) {
            num = Math.min(num, mapView[r][startC]);
            mapView[r - 1][startC] = mapView[r][startC];
        }
        
        // 아래
        for (int c = startC + 1; c <= endC; c++) {
            num = Math.min(num, mapView[endR][c]);
            mapView[endR][c - 1] = mapView[endR][c];
        }
        
        // 오른쪽
        for (int r = endR - 1; r >= startR; r--) {
            num = Math.min(num, mapView[r][endC]);
            mapView[r + 1][endC] = mapView[r][endC];
        }
        
        // 위 
        for (int c = endC - 1; c >= startC + 1; c--) {
            num = Math.min(num, mapView[startR][c]);
            mapView[startR][c + 1] = mapView[startR][c];
        }
        
        num = Math.min(num, tmp);
        mapView[startR][startC + 1] = tmp;
        
        return num;
    }
}