import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        String answer = "";
        Map<Character, Integer>[] types = new HashMap[4];
        Map<Integer, String> kind = new HashMap<>();
        kind.put(0, "RT");
        kind.put(1, "CF");
        kind.put(2, "JM");
        kind.put(3, "AN");
        for (int i = 0; i < 4; i++) {
            types[i] = new HashMap<>();
        }
        types[0].put('R', 0);
        types[0].put('T', 0);
        types[1].put('C', 0);
        types[1].put('F', 0);
        types[2].put('J', 0);
        types[2].put('M', 0);
        types[3].put('A', 0);
        types[3].put('N', 0);
        
        for (int i = 0; i < survey.length; i++) {
            int q = choices[i] / 4;
            int r = choices[i] % 4;
            if (q == 1 && r == 0) {
                continue;
            }
            String type = survey[i];
            int idx = 0;
            if (Objects.equals(type, "RT") || Objects.equals(type, "TR")) {
                idx = 0;
            }
            if (Objects.equals(type, "CF") || Objects.equals(type, "FC")) {
                idx = 1;
            }
            if (Objects.equals(type, "JM") || Objects.equals(type, "MJ")) {
                idx = 2;
            }
            if (Objects.equals(type, "AN") || Objects.equals(type, "NA")) {
                idx = 3;
            }
            if (q == 0) {
                types[idx].put(type.charAt(0), types[idx].getOrDefault(type.charAt(0), 0) + (4 - r));
                continue;
            }
            types[idx].put(type.charAt(1), types[idx].getOrDefault(type.charAt(1), 0) + r);
        }
        
        for (int i = 0; i < 4; i++) {
            Map<Character, Integer> type = types[i];
            int score1 = type.get(kind.get(i).charAt(0));
            int score2 = type.get(kind.get(i).charAt(1));
            String result = String.valueOf(kind.get(i).charAt(0));
            if (score1 < score2) {
                result = String.valueOf(kind.get(i).charAt(1));
            }
            answer += result;
        }
        return answer;
    }
}

// 점수가 다르다면 점수순
// 점수가 같다면 알파벳 사전순