import java.util.*;

class Solution {
    public List<String> solution(String[] record) {
        Map<String, String> names = new HashMap<>();
        for (int i = 0; i < record.length; i++) {
            String[] splitedRecord = record[i].split(" ");
            String command = splitedRecord[0];
            String id = splitedRecord[1];
            if (Objects.equals(command, "Enter") || Objects.equals(command, "Change")) {
                names.put(id, splitedRecord[2]);
            }
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < record.length; i++) {
            String[] splitedRecord = record[i].split(" ");
            String command = splitedRecord[0];
            String id = splitedRecord[1];
            if (Objects.equals(command, "Enter")) {
                result.add(names.get(id) + "님이 들어왔습니다.");
            }
            if (Objects.equals(command, "Leave")) {
                result.add(names.get(id) + "님이 나갔습니다.");
            }
        }
        return result;
    }
}