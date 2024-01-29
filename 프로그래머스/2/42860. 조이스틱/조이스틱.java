import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public int solution(String name) {
        List<Character> nameList = new ArrayList<>();
        for (char c : name.toCharArray()) {
            nameList.add(c);
        }
        return recursiveSolution(nameList, 0, 0);
    }

    public int recursiveSolution(List<Character> nameList, int index, int moves) {
        int currentMove = Math.min(nameList.get(index) - 'A', 'Z' - nameList.get(index) + 1);
        nameList.set(index, 'A');
        moves += currentMove;

        if (Collections.frequency(nameList, 'A') == nameList.size()) {
            return moves;
        }

        int left = 0;
        int right = 0;
        int leftIndex = index;
        int rightIndex = index;

        while (nameList.get(rightIndex) == 'A') {
            rightIndex = (rightIndex + 1) % nameList.size();
            right++;
        }

        while (nameList.get(leftIndex) == 'A') {
            leftIndex = (leftIndex - 1 + nameList.size()) % nameList.size();
            left++;
        }

        int rightMove = recursiveSolution(new ArrayList<>(nameList), rightIndex, moves + right);
        int leftMove = recursiveSolution(new ArrayList<>(nameList), leftIndex, moves + left);

        return Math.min(rightMove, leftMove);
    }
}