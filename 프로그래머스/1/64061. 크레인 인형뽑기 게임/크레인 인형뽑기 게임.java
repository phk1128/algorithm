import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int height = board.length;
        Stack<Integer> stack = new Stack<>();
        for (int move : moves) {
            int r = 0;
            while (r < height) {
                if (board[r][move - 1] != 0) {
                    int doll = board[r][move - 1];
                    if (stack.isEmpty()) {
                        stack.push(doll);
                    } else {
                        if (stack.peek() == doll) {
                            stack.pop();
                            answer += 2;
                        } else {
                            stack.push(doll);
                        }
                    }
                    board[r][move - 1] = 0;
                    break;
                } else {
                    r++;
                }
            }
        }
        return answer;
    }
}