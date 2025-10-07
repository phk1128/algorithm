import java.util.*;
class Solution {
    
    private static String[] banned_id;
    private static String[] user_id;
    private static String[] combi;
    private static boolean[] visited;
    private static Set<String> result;
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
        this.banned_id = banned_id;
        this.user_id = user_id;
        combi = new String[banned_id.length];
        result = new HashSet<>();
        visited = new boolean[user_id.length];
        
        recursiveSolve(0);
        answer = result.size();
        
        return answer;
    }
    
    private static void recursiveSolve(int depth) {
        
        if (depth == banned_id.length) {
            int[] indices = new int[banned_id.length];
            for (int i = 0; i < banned_id.length; i++) {
                for (int j = 0; j < user_id.length; j++) {
                    if (combi[i].equals(user_id[j])) {
                        indices[i] = j;
                        break;
                    }
                }
            }
            Arrays.sort(indices);
            result.add(Arrays.toString(indices));
            return;
        }
        
        for (int i = 0; i < user_id.length; i++) {
            if (visited[i] || !isMatch(user_id[i], banned_id[depth])) {
                continue;
            }
            visited[i] = true;
            combi[depth] = user_id[i];
            recursiveSolve(depth + 1);
            visited[i] = false;
        }
        
    }
    
    private static boolean isMatch(String user, String banned) {
        if (user.length() != banned.length()) {
            return false;
        }
        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) != '*' && user.charAt(i) != banned.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}