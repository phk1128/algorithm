import java.util.*;

class Solution {
    private Set<Integer>[] results;
    public int solution(int N, int number) {
        results = new HashSet[9];
        for (int i = 1; i <= 8; i++) {
            results[i] = new HashSet<>();
        }
        results[1].add(N);
        
        for (int i = 2; i <= 8; i++) {
            for (int j = 1; j <= i / 2; j++) {
                unionResult(results[j], results[i-j], results[i]);
                unionResult(results[i-j], results[j], results[i]);
            }
            results[i].add(Integer.parseInt(String.valueOf(N).repeat(i)));
        }
        
        for (int i = 1; i <= 8; i++) {
            if (results[i].contains(number)) {
                return i;
            }
        }
        return -1;
    }
    
    private void unionResult(Set<Integer> preSet, Set<Integer> curSet, Set<Integer> nextSet) {
        
        for (int a : preSet) {
            for (int b : curSet) {
                nextSet.add(a * b);
                nextSet.add(a - b);
                nextSet.add(a + b);
                if (b != 0) {
                    nextSet.add(a / b);
                }
            }
        }
    }
}