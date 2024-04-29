import java.util.*;

class Solution {
    
    private static int[] count;
    private static int[][] directions;
    
    public int[] solution(int[][] arr) {
        int[] answer = {};
        directions = new int[][]{{0,0},{-1,0}, {0,-1}, {-1,-1}};
        count = new int[2];
        recursiveSolve(arr);
        answer = count;
        
        return answer;
    }
    
    private static void recursiveSolve(int[][] arr) {
        int sizeR = arr.length;
        int sizeC = arr[0].length;
        
        if (sizeR <= 1 || sizeC <= 1) {
            int number = arr[sizeR - 1][sizeC - 1];
            if (number != -1) {
                count[number]++;
            }
            return;
        }
        
        int[][] newArr = new int[sizeR / 2][sizeC / 2];
        
        for (int r = 1; r < sizeR; r += 2) {
            for (int c = 1; c < sizeC; c+= 2) {
                int tmpZeroCount = 0;
                int tmpOneCount = 0;
                newArr[r / 2][c / 2] = -1;
                for (int[] direction : directions) {
                    int newR = r + direction[0];
                    int newC = c + direction[1];
                    int number = arr[newR][newC];
                    
                    if (number == 0) {
                        tmpZeroCount++;
                    }
                    
                    if (number == 1) {
                        tmpOneCount++;
                    }
                }
                if (tmpZeroCount == 4) {
                    newArr[r / 2][c / 2] = 0;
                    continue;
                }
                
                if (tmpOneCount == 4) {
                    newArr[r / 2][c / 2] = 1;
                    continue;
                }
                count[0] += tmpZeroCount;
                count[1] += tmpOneCount;
                
            }
        }
        recursiveSolve(newArr);
    }
}