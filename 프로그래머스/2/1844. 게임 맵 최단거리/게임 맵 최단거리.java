import java.util.*;

class Solution {
    
    private boolean[][] visitLog;
    private int limitX;
    private int limitY;
    private int answer = -1;
    private final int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    
    public int solution(int[][] maps) {
        visitLog = new boolean[maps.length][maps[0].length];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,0,1));
        
        limitX = maps[0].length;
        limitY = maps.length;
        
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.getX();
            int y = node.getY();
            int count = node.getCount();
            
            if (isArrive(x,y)) {
                return count;
            }
            
            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (!isAvailableMove(newX, newY, limitX, limitY)) {
                    continue;
                }
                if (maps[newY][newX] == 1 && !visitLog[newY][newX]) {
                    visitLog[newY][newX] = true;
                    queue.add(new Node(newX, newY, count+1));
                }
            }
        }
        return answer;
    }
    
    public boolean isAvailableMove(int x, int y, int limitX, int limitY) {
        return x < limitX && x >= 0 && y < limitY && y >= 0;
    }
    
    public boolean isArrive(int x, int y) {
        return x == (limitX-1) && y == (limitY-1);
    }
    
    class Node {
        private int x;
        private int y;
        private int count;
        
        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
        
        public int getX() {
            return this.x;
        }
        
        public int getY() {
            return this.y;
        }
        
        public int getCount() {
            return this.count;
        }
    }
}