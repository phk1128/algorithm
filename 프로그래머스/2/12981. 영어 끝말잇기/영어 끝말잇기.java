import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = {};
        
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        char end = words[0].charAt(words[0].length() - 1);
        char start;
        int num = 0;
        int turn = 0;
        for (int i = 1; i < words.length; i++) {
            
            String word = words[i];
            start = word.charAt(0);
            set.add(word);
            
            if (set.size() != i + 1 || !Objects.equals(start, end)) {
                num = (i+1) % n;
                turn = (int) Math.ceil((double)(i + 1) / n);
                if (num == 0) {
                    num = n;
                }
                break;
            }
            
            end = word.charAt(word.length() - 1);  
        }
        
        answer = new int[]{num, turn};
        return answer;
    }
    
}