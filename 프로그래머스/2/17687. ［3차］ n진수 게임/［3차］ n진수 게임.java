import java.util.*;

class Solution {
    private static StringBuilder sb;
    private static String answer;
    private static int n;
    private static int t;
    private static int m;
    private static int p;
    private static int turn;
    private static String[] alpha;
    
    public String solution(int n, int t, int m, int p) {
        
        this.n = n;
        this.t = t;
        this.m = m;
        this.p = p;
        turn = p % m;
        
        alpha = new String[16];
        
        for (int i = 10; i < 16; i++) {
            alpha[i] = String.valueOf((char) (55 + i));
        }
        
        System.out.println(convert(31, 16, ""));
        
        recursiveSolve(0, "", 0, 0);
        
        return answer;
    }
    
    private static void recursiveSolve(int number, String result, int count, int depth) {
        
        String convertedNum = convert(number, n, "");
        
        if (number == 0) {
            convertedNum = "0";
        }
        
        for (int i = 0; i < convertedNum.length(); i++) {
            count++;
            if ((count) % m == turn) {
                depth++;
                result += String.valueOf(convertedNum.charAt(i));
            }
            if (depth == t) {
                answer = result;
                return;
            }
        }
        
        recursiveSolve(number + 1, result, count, depth);
        
    }
    
    private static String convert(int num, int binary, String result) {
        
        
        if (num == 0) {
            sb = new StringBuilder(result);
            return sb.reverse().toString();
        }
        
        if (num % binary != 0) {
            int tmp = num % binary;
            String tmpStr = String.valueOf(tmp);
            if (tmp >= 10 && tmp <= 15) {
                tmpStr = alpha[tmp];
            }
            result = convert(num / binary, binary, result + tmpStr);
            
        }
        
        if (num % binary == 0) {
            
            result = convert(num / binary, binary, result + "0");
        }
        
        return result;
        
    }
}