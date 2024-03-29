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
        
        // 사각형 내부에 포함되어 있는 좌표면 리턴
        try {
            rec = findRectangle(x, y, rec);
        } catch(IllegalArgumentException e) {
            return;
        }
        
        // 테두리 밖쪽 좌표면 리턴
        if (rectangle[rec][0] * 2 > x || rectangle[rec][2] * 2 < x || rectangle[rec][1] * 2 > y || rectangle[rec][3] * 2 < y) {
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
        // 사각형을 전부 돌면서 좌표가 사각형 내부에 있는 좌표인지 체크 + 사각형이 바뀌었다면 바뀐 사각형으로 rec변경
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