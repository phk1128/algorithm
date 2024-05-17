import java.util.*;

class Solution {
    private Map<String,Integer> mineralKind;
    private int[][] costs;
    private int[] combi;
    private int answer;
    public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;
        mineralKind = new HashMap<>();
        costs = new int[][]{{1,1,1}, {5,1,1}, {25,5,1}};
        mineralKind.put("diamond", 0);
        mineralKind.put("iron", 1);
        mineralKind.put("stone", 2);
        int picksCount = Arrays.stream(picks).sum();
        combi = new int[picksCount];
        
        recursiveSolve(0,picksCount,picks,minerals);
        
        return answer;
    }
    private void recursiveSolve(int depth, int limit, int[] picks, String[] minerals) {
        if (depth == limit) {
            answer = Math.min(answer, getCost(minerals));
            return;
        }
        
        for (int i = 0; i < picks.length; i++) {
            if (picks[i] <= 0) {
                continue;
            }
            combi[depth] = i;
            picks[i]--;
            recursiveSolve(depth + 1, limit, picks, minerals);
            picks[i]++;
        }
    }
    
    private int getCost(String[] minerals) {
        int idx = 0;
        int cost = 0;
        for (int i = 0; i < combi.length; i++) {
            int pick = combi[i];
            int count = 5;
            while (count-- > 0) {
                if (idx == minerals.length) {
                    break;
                }
                cost += costs[pick][mineralKind.get(minerals[idx++])];
            }
        }
        return cost;
    }
}