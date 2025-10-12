import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        
        List<Integer> sortedPriorities = new ArrayList<>();
        for (int priority : priorities) {
            sortedPriorities.add(priority);
        }
        Collections.sort(sortedPriorities, Collections.reverseOrder());
        
        int c = 0;
        Queue<Process> queue = new ArrayDeque<>();
        
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new Process(i, priorities[i]));
        }
        
        while (!queue.isEmpty()) {
            Process current = queue.poll();
            
            if (current.priority == sortedPriorities.get(c)) {
                c++;
                answer++;
                
                if (current.index == location) {
                    return answer;
                }
            } else {
                queue.offer(current);
            }
        }
        
        return answer;
    }
    
    class Process {
        int index;
        int priority;
        
        Process(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
}