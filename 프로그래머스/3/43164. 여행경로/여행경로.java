import java.util.*;

class Solution {
    private List<String> result;
    private boolean[] visited;
    
    public String[] solution(String[][] tickets) {
        result = new ArrayList<>();
        visited = new boolean[tickets.length];
        String[] answer = {};
        dfs("ICN", "ICN", tickets, 0);
        Collections.sort(result);
        answer = result.get(0).split(",");
        return answer;
    }
    
    private void dfs(String start, String root, String[][] tickets, int depth) {
        if (depth == tickets.length) {
            result.add(root);
            return;
        }
        
        for (int i = 0; i < tickets.length; i++) {
            if (Objects.equals(start,tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                dfs(tickets[i][1], root + "," + tickets[i][1], tickets, depth + 1);
                visited[i] = false;
            }
        }
    }
}