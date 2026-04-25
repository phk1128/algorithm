import java.util.*;
public class Solution {
    public int solution(int n, int[][] guessNumbers, int[] answers) {
        // n개 중 5개를 고르는 조합 모두 생성
        List<int[]> candidates = new ArrayList<>();
        combine(new ArrayList<>(), 1, n, 5, candidates);

        int validCnt = 0;
        for (int[] code : candidates) {
            boolean valid = true;
            for (int i = 0; i < guessNumbers.length; i++) {
                int intersection = 0;
                for (int num : guessNumbers[i]) {
                    for (int c : code) {
                        if (num == c) {
                            intersection++;
                        }
                    }
                }
                if (intersection != answers[i]) {
                    valid = false;
                    break;
                }
            }
            if (valid) validCnt++;
        }
        return validCnt;
    }

    // n개 중 m개를 고르는 조합 재귀
    void combine(List<Integer> curr, int start, int n, int m, List<int[]> result) {
        if (curr.size() == m) {
            int[] arr = new int[m];
            for (int i = 0; i < m; i++) arr[i] = curr.get(i);
            result.add(arr);
            return;
        }
        for (int i = start; i <= n; i++) {
            curr.add(i);
            combine(curr, i + 1, n, m, result);
            curr.remove(curr.size() - 1);
        }
    }
}