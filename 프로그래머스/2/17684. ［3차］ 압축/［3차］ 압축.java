import java.util.*;

class Solution {
    
    private static int num;
    private static Map<String, Integer> alpha;
    private static List<Integer> result;
    private static String msg;
    
    
    public int[] solution(String msg) {
        int[] answer = {};
        result = new ArrayList<>();
        alpha = new HashMap<>();
        num = 26;
        this.msg = msg;
        int idx = 1;
        int ascii = 65;
        while (num-- > 0) {
            alpha.put(String.valueOf((char) ascii), idx);
            idx++;
            ascii++;
        }
        solve();
        
        answer = result.stream().mapToInt(num -> num).toArray();
        
        return answer;
    }
    
    private static void solve() {
        num = 26;
        for (int i = 0; i < msg.length(); i++) {
            if (i == msg.length() - 1) {
                result.add(alpha.get(String.valueOf(msg.charAt(i))));
                return;
            }
            int tmp = i;
            String str = "";
            while (true) {
                
                if (tmp == msg.length()) {
                    result.add(alpha.get(str));
                    return;
                }
                
                str += String.valueOf(msg.charAt(tmp));
                if (alpha.get(str) == null) {
                    num++;
                    alpha.put(str, num);
                    result.add(alpha.get(str.substring(0, str.length() - 1)));
                    i = (tmp - 1);
                    break;
                }
                tmp++;
            }
            
            if (i == msg.length() - 1) {
                result.add(alpha.get(String.valueOf(msg.charAt(i))));
                return;
            }
        }
    }
}