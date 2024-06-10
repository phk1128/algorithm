import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        Map<String, Integer> termMap = new HashMap<>();
        String[] split = today.split("\\.");
        int todayInt = getDay(today);
        for (String term : terms) {
            String[] termSplit = term.split(" ");
            int day = Integer.parseInt(termSplit[1]) * 28;
            termMap.put(termSplit[0], day);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" "); // 0, 1 => 0 날짜, 1 종류
            int day = getDay(privacy[0]);
            int dead = termMap.get(privacy[1]);
            if (todayInt - day >= dead) {
                result.add(i + 1);
            }
        }
        answer = result.stream().mapToInt(num -> num).toArray();
        return answer;
    }
    
    private int getDay(String str) {
        String[] split = str.split("\\.");
        return (Integer.parseInt(split[0]) * 12 * 28) + (Integer.parseInt(split[1]) * 28) + Integer.parseInt(split[2]);
    }
}

// 파기해야 할 개인정보의 번호를 오름차순으로