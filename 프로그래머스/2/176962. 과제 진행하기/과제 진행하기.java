import java.util.*;

class Solution {

    static class Assignment implements Comparable<Assignment> {
        String name;
        int start;
        int playtime;

        public Assignment(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }

        @Override
        public int compareTo(Assignment o) {
            return this.start - o.start;
        }
    }

    public List<String> solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        PriorityQueue<Assignment> queue = new PriorityQueue<>();
        Stack<Assignment> stack = new Stack<>();

        for (String[] plan : plans) {
            String name = plan[0];
            String[] split = plan[1].split(":");
            int start = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
            int playtime = Integer.parseInt(plan[2]);
            queue.offer(new Assignment(name, start, playtime));
        }

        while (!queue.isEmpty()) {
            Assignment cur = queue.poll();
            int time = cur.start;

            if (!queue.isEmpty()) {
                Assignment next = queue.peek();
                int availableTime = next.start - cur.start;
                int diff = cur.playtime - availableTime;

                if (diff > 0) {
                    stack.push(new Assignment(cur.name, 0, diff));
                    continue;
                }

                time += cur.playtime;
                answer.add(cur.name);

                while (!stack.isEmpty() && next.start > time) {
                    Assignment pause = stack.pop();
                    int remaining = next.start - time;
                    int pauseDiff = pause.playtime - remaining;

                    if (pauseDiff > 0) {
                        time += remaining;
                        stack.push(new Assignment(pause.name, 0, pauseDiff));
                        break;
                    }

                    time += pause.playtime;
                    answer.add(pause.name);
                }
            } else {
                answer.add(cur.name);
            }
        }

        while (!stack.isEmpty()) {
            answer.add(stack.pop().name);
        }

        return answer;
    }
}