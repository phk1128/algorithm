import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        // 보석 종류 집합 구하기
        Set<String> gemKinds = new HashSet<>(Arrays.asList(gems));
        int kindCount = gemKinds.size();
        
        Map<String, Integer> window = new HashMap<>();
        int minLength = Integer.MAX_VALUE;
        int left = 0, right = 0;
        int answerLeft = 0, answerRight = 0;
        
        while (right < gems.length) {
            // 오른쪽 끝에 보석 추가
            window.put(gems[right], window.getOrDefault(gems[right], 0) + 1);
            right++;
            
            // 모든 종이 포함됐을 때 왼쪽 윈도우 줄이기 시도
            while (window.size() == kindCount) {
                if (right - left < minLength) {
                    minLength = right - left;
                    answerLeft = left;
                    answerRight = right; // right는 exclusive
                }
                // 왼쪽 끝 보석 제거
                window.put(gems[left], window.get(gems[left]) - 1);
                if (window.get(gems[left]) == 0) {
                    window.remove(gems[left]);
                }
                left++;
            }
        }
        // 1-indexed return 요구
        return new int[] {answerLeft + 1, answerRight};
    }
}