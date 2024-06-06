import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String decimal = getDecimal("", n , k);
        String[] split = decimal.split("0+");
        
        for (String num : split) {
            
            if (isPrime(Long.parseLong(num))) {
                answer++;
            }
        }
        return answer;
    }
    
    private String getDecimal(String result, int n, int k) {
        if (n == 0) {
            return result;
        }
        return getDecimal((n % k) + result, n / k, k);
    }
    
    private boolean isPrime(long num) {
        if (num == 1 || num == 0) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        for (int i = 2; i <= (int) Math.pow(num, 0.5); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}