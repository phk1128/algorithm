import java.util.*;

class Solution {
    
    public int solution(int[][] jobs) {
        int answer = 0;
        int len = jobs.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>((j1, j2) -> {
            if (j1[0] != j2[0]) {
                return j1[0] - j2[0];
            }
            return j1[1] - j2[1];
        });
        
        for (int[] job : jobs) {
            queue.offer(job);
        }
        
        int total = 0;
        int end = 0;
        while (!queue.isEmpty()) {
            int[] job = queue.peek();
            int start = job[0];
            int time = job[1];
            
            if (start >= end) {
                queue.poll();
                end = (start + time);
                total += (end - start);
                continue;
            }
            
            PriorityQueue<int[]> priorityJobs = getPriorityJobs(end, queue);
            job = priorityJobs.poll();
            start = job[0];
            time = job[1];
            end += time;
            total += (end - start);
            
            queue.clear();
            
            for (int[] j : priorityJobs) {
                queue.offer(j);
            }
        }
        answer = total / len;
        
        return answer;
    }
    
    private PriorityQueue<int[]> getPriorityJobs(int end, PriorityQueue<int[]> queue) {
        
        PriorityQueue<int[]> priorityJobs = new PriorityQueue<>((j1, j2) -> {
            if (j1[0] <= end && j2[0] <= end) {
                return j1[1] - j2[1];
            }
            return j1[0] - j2[0];
        });
        
        for (int[] job : queue) {
            priorityJobs.offer(job);
        }
        return priorityJobs;
    }
    
    
}