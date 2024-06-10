import java.util.*;

class Solution {
    public String solution(String new_id) {
        String answer = "";
        new_id = new_id.toLowerCase();
        new_id = new_id.replaceAll("[^a-z0-9-_.]","");
        new_id = new_id.replaceAll("[.]{2,}", ".");
        new_id = new_id.replaceAll("^[.]|[.]$", "");
        if (Objects.equals(new_id, "")) {
            new_id += "a";
        }
        if (new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("[.]$", "");
        }
        while (new_id.length() <= 2) {
            String str = String.valueOf(new_id.charAt(new_id.length() - 1));
            new_id += str;
        }
        answer = new_id;
        return answer;
    }
}