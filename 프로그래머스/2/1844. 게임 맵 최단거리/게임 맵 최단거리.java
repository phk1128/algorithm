import java.util.*;

class Solution {
    static int answer = -1;
    static boolean[][] visited;
    static int[] mrow = {-1, 1, 0, 0};
    static int[] mcol = {0, 0, -1, 1};

    public int solution(int[][] maps) {
        visited = new boolean[maps.length][maps[0].length];

        bfs(0, 0, maps);

        return answer;
    }

    public void bfs(int row, int col, int[][] maps) {
        List<Node> list = new ArrayList<>();

        list.add(new Node(0, 0, 1));
        visited[0][0] = true;

        while (!list.isEmpty()) {
            Node cur = list.remove(0);

            if (cur.row == maps.length - 1 && cur.col == maps[0].length - 1) {
                answer = cur.count;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nextRow = cur.row + mrow[i];
                int nextCol = cur.col + mcol[i];

                if (canMove(nextRow, nextCol, maps)) {
                    visited[nextRow][nextCol] = true;
                    list.add(new Node(nextRow, nextCol, cur.count + 1));
                }
            }
        }
    }

    class Node {
        int row;
        int col;
        int count;

        public Node(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }

    public boolean canMove(int row, int col, int[][] maps) {
        return row >= 0 && row < visited.length && col >= 0 && col < visited[0].length
                && !visited[row][col] && maps[row][col] != 0;
    }
}
