import java.util.*;

class Solution {
    
    private static int[][] arr;
    
    public int[] solution(int n) {
        int[] answer = {};
        int sum = (n * (n + 1) / 2);
        arr = new int[n][];
        
        for (int i = 0; i < n; i++) {
            arr[i] = new int[i + 1];
        }
        
        recursiveSolve(0,n-1,0,1);
        
        int[] result = new int[sum];
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int num : arr[i]) {
                result[idx++] = num;
            }
        }
        
        answer = result;
        
        return answer;
    }
    
    private static void recursiveSolve(int startR, int limitR, int startC, int number) {
        
        if (startR > limitR) {
            return;
        }
        
        for (int r = startR; r < limitR; r++) {
            arr[r][startC] = number++;
        }
        
        for (int c = startC; c <= limitR - startC; c++) {
            arr[limitR][c] = number++;
        }
        
        for (int r = limitR - 1; r > startR; r--) {
            arr[r][r - startC] = number++;
        }
        
        recursiveSolve(startR + 2, limitR - 1, startC + 1, number);
        
    }
}