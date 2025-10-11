import java.util.*;
class Solution {
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int picksCount = Arrays.stream(picks).sum();
        int len = Math.min((int) Math.ceil(minerals.length / 5.0), picksCount);
        
        List<MineralGroup> mineralGroups = new ArrayList<>();
        
        for (int i = 0; i < len; i++) {
            int start = i * 5;
            int end = Math.min(start + 5, minerals.length);
            int stoneCost = 0;
            
            for (int j = start; j < end; j++) {
                String mineral = minerals[j];
                if (mineral.equals("diamond")) {
                    stoneCost += 25;
                } else if (mineral.equals("iron")) {
                    stoneCost += 5;
                } else {
                    stoneCost += 1;
                }
            }
            
            mineralGroups.add(new MineralGroup(i, stoneCost, start, end));
        }
        
        Collections.sort(mineralGroups, (g1, g2) -> g2.cost - g1.cost);
        
        int[] select = new int[len];
        Arrays.fill(select, -1);
        
        for (MineralGroup group : mineralGroups) {
            for (int pickType = 0; pickType < picks.length; pickType++) {
                if (picks[pickType] > 0) {
                    picks[pickType]--;
                    select[group.index] = pickType;
                    break;
                }
            }
        }
        
        for (int i = 0; i < len; i++) {
            int pickNum = select[i];
            if (pickNum == -1) continue;
            
            int start = i * 5;
            int end = Math.min(start + 5, minerals.length);
            
            for (int j = start; j < end; j++) {
                String mineral = minerals[j];
                answer += getFatigue(pickNum, mineral);
            }
        }
        
        return answer;
    }
    
    private int getFatigue(int pickNum, String mineral) {
        if (pickNum == 0) {
            return 1;
        } else if (pickNum == 1) {
            if (mineral.equals("diamond")) {
                return 5;
            }
            return 1;
        } else {
            if (mineral.equals("diamond")) {
                return 25;
            } else if (mineral.equals("iron")) {
                return 5;
            }
            return 1;
        }
    }
    
    static class MineralGroup {
        int index;
        int cost;
        int start;
        int end;
        
        MineralGroup(int index, int cost, int start, int end) {
            this.index = index;
            this.cost = cost;
            this.start = start;
            this.end = end;
        }
    }
}