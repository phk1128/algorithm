import java.util.*;
import java.util.stream.Collectors;
class Solution {
    public List<String> solution(String[] orders, int[] course) { 
        List<String> answer = new ArrayList<>();
        for (int quantity : course) {
            Map<String, Integer> courseMenus = new HashMap<>();
            for (String order : orders) {
                List<String> covertedOrder = Arrays.stream(order.split("")).collect(Collectors.toList());
                Collections.sort(covertedOrder);
                order = String.join("", covertedOrder);
                List<String> combination = new ArrayList<>();
                boolean[] visited = new boolean[order.length()];
                int totalCombinations = 1 << order.length();
                for (int mask = 0; mask < totalCombinations; mask++) {
                    if (Integer.bitCount(mask) == quantity) {
                        StringBuilder menus = new StringBuilder();
                        for (int i = 0; i < order.length(); i++) {
                            if ((mask & (1 << i)) != 0) {
                                menus.append(order.charAt(i));
                            }
                        }
                        combination.add(menus.toString());
                    }
                }
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
}