import java.util.*;
import java.util.stream.*;
import java.util.stream.Collectors;

class Solution {
    
    private boolean[] visited;
    private int[] combi;
    private List<List<Integer>> results;
    private int answer;
    
    
    public int solution(String[][] relation) {
        answer = 0;
        visited = new boolean[relation[0].length];
        results = new ArrayList<>();
        
        
        for (int limit = 1; limit <= relation[0].length; limit++) {
            combi = new int[limit];
            updateAnswer(0, 0, limit, relation);
        }
        return answer;
    }
    
    private void updateAnswer(int start, int depth, int limit, String[][] relation) {
        
        if (depth == limit) {
            if (!isExistCombi() && isUnique(relation)) {
                answer++;
                List<Integer> tmp = Arrays.stream(combi).boxed().collect(Collectors.toList());
                results.add(tmp);
            }
            return;
        }
        
        for (int i = start; i < relation[0].length; i++) {
            if (visited[i]) {
                continue;
            }
            combi[depth] = i;
            updateAnswer(i + 1, depth + 1, limit, relation);
        }
        
    }
    
    private boolean isUnique(String[][] relation) {
        Set<String> set = new HashSet<>();
        for (String[] r : relation) {
            StringBuilder sb = new StringBuilder();
            for (int c : combi) {
                sb.append(r[c]);
                sb.append(",");
            }
            set.add(sb.toString());
        }
        return set.size() == relation.length;
    }
    
    private boolean isExistCombi() {
        
        List<Integer> tmp = Arrays.stream(combi).boxed().collect(Collectors.toList());
        for (List<Integer> result : results) {
            if (tmp.containsAll(result)) {
                return true;
            }
        }
        return false;
    }
}