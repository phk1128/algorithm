import java.util.*;

class Solution {
    private int answer;
    private String[] str;
    private List<String> result;
    
    public int solution(String word) {
        str = new String[]{"A","E","I","O","U"};
        result = new ArrayList<>();
        dfs(word,"",0);
        answer = result.indexOf(word);
        return answer;
    }
    
    private void dfs(String word, String tempStr, int count) {
        result.add(tempStr);
        if (Objects.equals(word, result)) {
            return;
        }
        
        if (tempStr.length() == 5) {
            return;
        }
        
        for (int i = 0; i < 5; i++) {
            dfs(word, tempStr + str[i], count);
        }
    }
}