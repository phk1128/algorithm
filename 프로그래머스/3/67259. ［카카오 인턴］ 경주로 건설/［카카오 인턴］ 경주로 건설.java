import java.util.*;

class Solution {
    private int minCost;
    private int N;
    private List<Position> result;
    
    static class Position implements Comparable<Position> {
        
        int r;
        int c;
        int[] direction;
        int cost;
        
        public Position(int r, int c, int[] direction, int cost) {
            this.r = r;
            this.c = c;
            this.direction = direction;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Position p) {
            return this.cost - p.cost;
        }
    }
    
    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        minCost = Integer.MAX_VALUE;
        result = new ArrayList<>();
        updateMinCost(board);    
        answer = minCost;
        return answer;
    }
    
    private void updateMinCost(int[][] board) {
        int[][] costs = new int[N][N];
        int[][] directions = new int[][]{{1,0}, {-1,0}, {0,-1}, {0,1}};
        Queue<Position> queue = new ArrayDeque<>();
        queue.offer(new Position(0,0, new int[]{1,0}, 0));
        queue.offer(new Position(0,0, new int[]{0,1}, 0));
        
        while (!queue.isEmpty()) {
            Position position = queue.poll();
            int r = position.r;
            int c = position.c;
            int[] currentD = position.direction;
            int currentCost = position.cost;
            
            if (r == N - 1 && c == N - 1) {
                minCost = Math.min(minCost, currentCost);
                continue;
            }
            
            for (int[] direction : directions) {
                int newR = r + direction[0];
                int newC = c + direction[1];
                
                if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)){
                    continue;
                }
                
                if (board[newR][newC] == 1) {
                    continue;
                }
                
                int newCost = currentCost +getCost(currentD, direction);
                if (costs[newR][newC] == 0) {
                    costs[newR][newC] = newCost;
                    queue.offer(new Position(newR, newC, direction, costs[newR][newC]));
                }
                
                if (costs[newR][newC] + 500 > newCost) {
                    costs[newR][newC] = newCost;
                    queue.offer(new Position(newR, newC, direction, costs[newR][newC]));
                }
            }
        }
    }
    
    private int getCost(int[] currentD, int[] nextD) {
        
        if (currentD[0] != nextD[0] || currentD[1] != nextD[1]) {
            return 600;
        }
        return 100;
    }
}