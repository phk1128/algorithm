import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (String op : operations) {
            String[] split = op.split(" ");
            String cmd = split[0];
            int num = Integer.parseInt(split[1]);
            if (Objects.equals(cmd, "I")) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                continue;
            }
            if (map.isEmpty()) {
                continue;
            }
            
            int key = 0;
            if (num == 1) {
                key = map.lastKey();
            }
            if (num == -1) {
                key = map.firstKey();
            }
            map.put(key, map.getOrDefault(key, 0) - 1);
            if (map.get(key) <= 0) {
                map.remove(key);
            }
        }
        answer = map.isEmpty() ? new int[]{0,0} : new int[]{map.lastKey(), map.firstKey()};
        return answer;
    }
}