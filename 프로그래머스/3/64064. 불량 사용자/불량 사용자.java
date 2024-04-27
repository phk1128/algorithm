import java.util.*;

class Solution {
    
    private static String[] banned_id;
    private static String[] user_id;
    private static String[] combi;
    private static boolean[] visited;
    private static Set<String> result;
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
        for (int i = 0; i < banned_id.length; i++) {
            banned_id[i] = banned_id[i].replace("*",".");
        }
        
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
            String[] tmpCombi = Arrays.copyOf(combi, banned_id.length);
            Arrays.sort(tmpCombi);
            result.add(String.join("",tmpCombi));
            return;
        }
        
        for (int i = 0; i < user_id.length; i++) {
            String regex = banned_id[depth];
            if (!user_id[i].matches(regex) || visited[i]) {
                continue;
            }
            visited[i] = true;
            combi[depth] = user_id[i];
            recursiveSolve(depth + 1);
            visited[i] = false;
        }
        
    }
}