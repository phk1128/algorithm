import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        Queue<Character> queue = new ArrayDeque<>();
        
        for (int i = 0; i < skill.length(); i++) {
            queue.offer(skill.charAt(i));
        }
        
        for (String skill_tree : skill_trees) {
            Queue<Character> tmpQ = new ArrayDeque<>(queue);
            for (int i = 0; i < skill_tree.length(); i++) {
                
                if (tmpQ.isEmpty()) {
                    continue;
                }
                
                char tmp = tmpQ.peek();
                
                if (Objects.equals(tmp, skill_tree.charAt(i))) {
                    tmpQ.poll();
                }
            }
            
            if (tmpQ.isEmpty()) {
                answer++;
            } else {
                answer++;
                while (!tmpQ.isEmpty()) {
                    if (skill_tree.contains(String.valueOf(tmpQ.poll()))) {
                        answer--;
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
}