import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public List<String> solution(String[] orders, int[] course) { 
        List<String> answer = new ArrayList<>();
        for (int quantity : course) {
            Map<String, Integer> courseMenus = new HashMap<>();
            for (String order : orders) {
                List<String> combination = new ArrayList<>();
                List<String> covertedOrder = Arrays.stream(order.split("")).collect(Collectors.toList());
                Collections.sort(covertedOrder);
                order = String.join("",covertedOrder);
                boolean[] visited = new boolean[order.length()];
                addCombination(order, combination, 0, visited, 0, quantity);
                for (String menus : combination) {
                    if (Objects.equals(courseMenus.get(menus), null)) {
                        courseMenus.put(menus, 1);
                        continue;
                    }
                    courseMenus.replace(menus, courseMenus.get(menus) + 1);
                }
                
            }
            if (!courseMenus.isEmpty()) {
                int maxQuantity = Collections.max(courseMenus.values());
                for (Map.Entry<String, Integer> entry : courseMenus.entrySet()) {
                    int value = entry.getValue();
                    if (value == maxQuantity && value >= 2) {
                        answer.add(entry.getKey());
                    }
                }
                
            }
           
        }
        Collections.sort(answer);
        return answer;
    }
    public void addCombination(String order, List<String> combination, int start, boolean[] visited, int depth, int quantity) {
        if (quantity == depth) {
            String menus = "";
            for (int i = 0; i < order.length(); i++) {
                if (visited[i]) {
                    menus += order.split("")[i];
                }
            }
            combination.add(menus);
        }
        for (int i = start; i < order.length(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                addCombination(order, combination, i+1, visited, depth+1, quantity);
                visited[i] = false;
            }
        }
    }
}