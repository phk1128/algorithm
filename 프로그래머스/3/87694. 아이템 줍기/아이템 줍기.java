import java.util.*;

class Solution {
    private int[][] rectangle;
    private boolean[][] visited;
    private int answer;
    private int itemX;
    private int itemY;
    private int[][] directions;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        answer = Integer.MAX_VALUE;
        this.rectangle = rectangle;
        visited = new boolean[102][102];
        this.itemX = itemX * 2;
        this.itemY = itemY * 2;
        directions = new int[][]{{1,0}, {-1,0}, {0,1}, {0,-1}}; 
        
        dfs(characterX * 2, characterY * 2,0,0);
        
        return answer;
    }
    
    private void dfs(int x, int y, int rec, int count) {
        if (itemX == x && itemY == y) {
            answer = Math.min(answer, count / 2);
            return;
        }
        try {
            rec = findRectangle(x, y, rec);
        } catch(IllegalArgumentException e) {
            return;
        }
        
        if (rectangle[rec][0] * 2 > x || rectangle[rec][2] * 2 < x || rectangle[rec][1] * 2 > y || rectangle[rec][3] * 2 < y) {
            return;
        }
        
        if (x > rectangle[rec][0] * 2 && x < rectangle[rec][2] * 2 && y > rectangle[rec][1] * 2 && y < rectangle[rec][3] * 2) {
            return;
        }
        
        visited[x][y] = true;
        
        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            
            if (!visited[newX][newY]) {
                dfs(newX, newY, rec, count + 1);
            }
        }
    }
    
    private int findRectangle(int x, int y, int rec) {
        for (int i = 0; i < rectangle.length; i++) {
            if (rectangle[i][0] * 2 <= x && rectangle[i][2] * 2 >= x && rectangle[i][1] * 2 <= y && rectangle[i][3] * 2 >= y) {
                if (rectangle[i][0] * 2 < x && rectangle[i][2] * 2 > x && rectangle[i][1] * 2 < y && rectangle[i][3] * 2 > y) {
                    
                    throw new IllegalArgumentException("사각형 내부에 포함되어 있는 좌표 입니다.");
                }
                
                if (rec != i) {
                    rec = i;
                }
            }
        }
        return rec;
    }
}