import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = -1;
        String regex = "[^" + skill + "]";
        answer = (int) Arrays
            .stream(skill_trees)
            .filter(tree -> skill.startsWith(tree.replaceAll(regex, ""))).count();
        return answer;
    }
}