import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        Map<String, Integer> result = new LinkedHashMap<>();
        for (String id : id_list) {
            result.put(id, 0);
        }
        Map<String, List<String>> repoMap = new HashMap<>();
        Set<String> repoSet = new HashSet<>();
        for (int i = 0; i < report.length; i++) {
            repoSet.add(report[i]);
        }
        for (String repo : repoSet) {
            String[] users = repo.split(" ");
            List<String> list = repoMap.getOrDefault(users[1], new ArrayList<>());
            list.add(users[0]);
            repoMap.put(users[1], list);
        }
        for (String key : repoMap.keySet()) {
            List<String> list = repoMap.get(key);
            if (list.size() >= k) {
                for (String user : list) {
                    result.put(user, result.getOrDefault(user, 0) + 1);
                }
            }
        }
        answer = new int[id_list.length];
        int idx = 0;
        for (String key : result.keySet()) {
            answer[idx++] = result.get(key);
        }
        return answer;
    }
}