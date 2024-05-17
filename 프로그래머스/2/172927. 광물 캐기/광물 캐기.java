import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int picksCount = (int) Arrays.stream(picks).sum();
        int len = (int) Math.ceil(minerals.length / 5.0);
        String[][] mineralGroup = new String[len][5];
        int[][] costs = new int[len][2];
        int[] select = new int[len];
        
        for (int i = 0; i < picksCount * 5; i++) {
            if (i >= minerals.length) {
                break;
            }
            String mineral = minerals[i];
            int cost = 0;
            if (Objects.equals(mineral, "diamond")) {
                cost = 25;
            }
            if (Objects.equals(mineral, "iron")) {
                cost = 5;
            }
            if (Objects.equals(mineral, "stone")) {
                cost = 1;
            }
            costs[i / 5][1] += cost;
            costs[i / 5][0] = i / 5;
            mineralGroup[i / 5][i % 5] = mineral;
        }
        
        Arrays.sort(costs, (c1,c2) -> c2[1] - c1[1]);
        
        
        for (int[] cost : costs) {
            if (cost[1] == 0) {
                break;
            }
            int tmpPick = -1;
            for (int i = 0; i < picks.length; i++) {
                if (picks[i] > 0) {
                    picks[i]--;
                    tmpPick = i; 
                    break;
                }
            }
            select[cost[0]] = tmpPick;
        }
           
        for (int i = 0; i < mineralGroup.length; i++) {
            int pickNum = select[i];
            for (String mineral : mineralGroup[i]) {
                if (Objects.equals(mineral, null)) {
                    continue;
                }
                if (pickNum == 0) {
                    answer++;
                }
                if (pickNum == 1) {
                    if (Objects.equals(mineral, "diamond")) {
                        answer += 5;
                        continue;
                    }
                    answer++;
                }
                if (pickNum == 2) {
                    if (Objects.equals(mineral, "diamond")) {
                        answer += 25;
                        continue;
                    }
                    if (Objects.equals(mineral, "iron")) {
                        answer += 5;
                        continue;
                    }
                    answer++;
                }
            }
        }
        return answer;
    }
}