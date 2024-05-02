class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        
        for (int i = 0; i < arrayA.length; i++) {
            gcdA = gcd(arrayA[i], gcdA);
            gcdB = gcd(arrayB[i], gcdB);
            
        }
        
        if (isPossible(arrayA, gcdB)) {
            if (answer < gcdB) {
                answer = gcdB;
            }
        }
        
        if (isPossible(arrayB, gcdA)) {
            if (answer < gcdA) {
                answer = gcdA;
            }
        }
        return answer;
    }
    
    private boolean isPossible(int[] array, int gcd) {
        
        for (int num : array) {
            if (num % gcd == 0) {
                return false;
            }
        }
        return true; 
    }
    
    private int gcd(int a, int b) {
        
        if (a % b == 0) {
            return b;
        }
        
        return gcd(b, a % b);
    }
}