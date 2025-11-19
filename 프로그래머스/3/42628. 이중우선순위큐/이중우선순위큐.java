import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((i1, i2) -> i2 - i1);
        for (String op : operations) {
            String[] split = op.split(" ");
            String cmd = split[0];
            int num = Integer.parseInt(split[1]);
            if (Objects.equals(cmd, "I")) {
                minQ.offer(num);
                maxQ.offer(num);
                continue;
            }
            if (num == 1) {
                if (!maxQ.isEmpty()) {
                    minQ.remove(maxQ.poll());
                }
            }
            if(num == -1) {
                if (!minQ.isEmpty()) {
                    maxQ.remove(minQ.poll());
                }
                    
            }
        }
        int min = minQ.isEmpty() ? 0 : minQ.poll();
        int max = maxQ.isEmpty() ? 0 : maxQ.poll();
        answer = new int[]{max, min};
        return answer;
    }
}