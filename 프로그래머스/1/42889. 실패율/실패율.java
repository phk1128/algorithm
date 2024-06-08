import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = {};
        double[][] result = new double[N][2]; // 0, 1 => 스테이지, 실패율
        Queue<Integer> queue = new ArrayDeque<>();
        Arrays.sort(stages);
        for (int stage : stages) {
            queue.offer(stage);
        }
        int stage = 1;
        double count = 0;
        double n = (double) stages.length;
        while (stage <= N) {
            if (!queue.isEmpty() && (queue.peek() <= stage)) {
                queue.poll();
                count++;
            } else {
                double rate = count / n;
                if (n == 0) {
                    rate = 0;
                }
                result[stage - 1] = new double[]{stage, rate};
                n -= count;
                stage++;
                count = 0;
            }
        }
        Arrays.sort(result, (r1, r2) -> {
            if (r1[1] != r2[1]) {
                return Double.compare(r2[1], r1[1]);
            }
            return Double.compare(r1[0], r2[0]);
        });

        answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = (int) result[i][0];
        }
        return answer;
    }
}

// 실패율 기준 내림차순, 실패율이 같다면 스페이지 오름 차순