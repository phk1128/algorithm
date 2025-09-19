import java.util.*;

class Solution {
    private boolean[] visited;
    private int groupSize;
    
    public int solution(int[] cards) {
        int numberOfBoxes = cards.length;
        visited = new boolean[numberOfBoxes + 1];
        List<Integer> groupSizes = new ArrayList<>();
        
        for (int boxIndex = 0; boxIndex < numberOfBoxes; boxIndex++) {
            if (visited[boxIndex + 1]) {
                continue;
            }
            groupSize = 1;
            visited[boxIndex + 1] = true;
            dfs(cards[boxIndex], cards);
            groupSizes.add(groupSize);
        }
        if (groupSizes.size() < 2) {
            return 0;
        }
        Collections.sort(groupSizes, Collections.reverseOrder());
        return groupSizes.get(0) * groupSizes.get(1);
    }
    
    private void dfs(int nextBox, int[] cards) {
        if (visited[nextBox]) {
            return;
        }
            visited[nextBox] = true;
            groupSize++;
            dfs(cards[nextBox - 1], cards);
    }
}