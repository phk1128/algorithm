import java.util.*;
import java.util.stream.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        Map<String, String> nickName = new LinkedHashMap<>();
        int idx = 0;
        for (String r : record) {
            String[] split = r.split(" ");
            String command = split[0];
            if (!Objects.equals(command, "Change")) {
                idx++;
            }
            if (Objects.equals(command, "Leave")) {
                continue;
            }
            
            String id = split[1];
            String name = split[2];
            nickName.put(id, name);
        }
        answer = new String[idx];
        idx = 0;
        for (String r : record) {
            String[] split = r.split(" ");
            String command = split[0];
            String id = split[1];
            String name = nickName.get(id);
            if (Objects.equals(command, "Change")) {
                continue;
            }
            if (Objects.equals(command, "Enter")) {
                answer[idx++] = name + "님이 들어왔습니다.";
            }
            if (Objects.equals(command, "Leave")) {
                answer[idx++] = name + "님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}