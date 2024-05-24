import java.util.*;

class Solution {
    private boolean[] combi;
    private int[] arrows;
    private List<Result> results;
    
    class Result implements Comparable<Result> {
        
        private int[] arrows;
        private int score;
        private int maxIdx;
        
        public Result(int[] arrows, int score, int maxIdx) {
            this.arrows = arrows;
            this.score = score;
            this.maxIdx = maxIdx;
        }
        
        @Override
        public int compareTo(Result r) {
            if (r.score != this.score) {
                return r.score - this.score;
            }
            
            return r.maxIdx - this.maxIdx;
        }
    }
    public int[] solution(int n, int[] info) {
        int[] answer = {};
        combi = new boolean[11];
        results = new ArrayList<>();
        recursiveSolve(0, 11, n, info);
        Collections.sort(results);
        Result result = results.get(0);
        if (result.score <= 0) {
            return new int[]{-1};
        }
        answer = result.arrows;
        return answer;
    }
    
    private void recursiveSolve(int depth, int limit, int n, int[] info) {
        
        if (depth == limit) {
            arrows = new int[11];
            int[] result = getResult(n, info);
            results.add(new Result(arrows, result[0], result[1]));
            return;
        }
        
        combi[depth] = true;
        recursiveSolve(depth + 1, limit,n,info);
        combi[depth] = false;
        recursiveSolve(depth + 1, limit,n,info);
            
    }
    
    private int[] getResult(int n, int[] info) {
        
        int scoreR = 0;
        int scoreA = 0;
        int idx = -1;
        for (int i = 0; i <= 10; i++) {
            int num = info[i] + 1;
            if (combi[i] && num <= n && n > 0) {
                scoreR += (10 - i);
                arrows[i] = num;
                n -= num;
                idx = i;
            }
        }
        
        if (n > 0) {
            arrows[10] = n;
            idx = 10;
        }
        
        for (int i = 0; i <= 10; i++) {
            if (info[i] != 0 && info[i] >= arrows[i]){
                scoreA += (10 - i);
            }
        }
        // System.out.println(scoreR + " " + scoreA);
        return new int[]{scoreR - scoreA, idx};
    }
    
        
}
    

