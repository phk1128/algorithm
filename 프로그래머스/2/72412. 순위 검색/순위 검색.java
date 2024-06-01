import java.util.*;

class Solution {
    
    private List<Integer>[][][][] infoList;
    private int count;
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = {};
        setInfoSplit(info);
        answer = getResult(query);
        return answer;
    }
    
    private int[] getResult(String[] query) {
        int[] result = new int[query.length];
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        Collections.sort(infoList[i][j][k][l]);
                    }
                }
            }
        }
        
        for (int i = 0; i < query.length; i++) {
            String tmp = query[i].replace(" and", "");
            String[] el = tmp.split(" ");
            int idx1 = getIdx(el[0]);
            int idx2 = getIdx(el[1]);
            int idx3 = getIdx(el[2]);
            int idx4 = getIdx(el[3]);
            int score = Integer.parseInt(el[4]);
            count = 0;
            List<Integer> scores = infoList[idx1][idx2][idx3][idx4];
            binarySearch(0, scores.size(), score, scores);
            result[i] = count;
        }
        return result;
    }
    
    private void binarySearch(int start, int end, int target, List<Integer> scores) {
        
        if (start >= end) {
            count = scores.size() - start;
            return;
        }
        
        int mid = (start + end) / 2;
        
        if (target <= scores.get(mid)) {
            binarySearch(start, mid, target, scores);
        }
        
        if (target > scores.get(mid)) {
            binarySearch(mid + 1, end, target, scores);
        }
    }
    
    
    private void setInfoSplit(String[] info) {
        infoList = new ArrayList[4][3][3][3];
        int[] idxGroup = new int[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        infoList[i][j][k][l] = new ArrayList<>();
                    }
                }
            }
        }
        for (int i = 0; i < info.length; i++) {
            String[] el = info[i].split(" ");
            int score = Integer.parseInt(el[4]);
            for (int j = 0; j <= 4; j++) {
                idxGroup[0] = getIdx(el[0]);
                idxGroup[1] = getIdx(el[1]);
                idxGroup[2] = getIdx(el[2]);
                idxGroup[3] = getIdx(el[3]);
                addScore(0,0,j,idxGroup,score);
            }
        }
    }
    
    private void addScore(int depth, int start, int limit, int[] idxGroup, int score) {
        
        if (depth == limit) {
            infoList[idxGroup[0]][idxGroup[1]][idxGroup[2]][idxGroup[3]].add(score);
            return;
        }
        
        for (int i = start; i < 4; i++) {
            int tmp = idxGroup[i];
            idxGroup[i] = 0;
            addScore(depth + 1, i + 1, limit, idxGroup, score);
            idxGroup[i] = tmp;
        }
    }
    
    private int getIdx(String str) {
        if (Objects.equals(str, "cpp")) {
            return 1;
        }
        if (Objects.equals(str, "java")) {
            return 2;
        }
        if (Objects.equals(str, "python")) {
            return 3;
        }
        if (Objects.equals(str, "backend")) {
            return 1;
        }
        if (Objects.equals(str, "frontend")) {
            return 2;
        }
        if (Objects.equals(str, "junior")) {
            return 1;
        }
        if (Objects.equals(str, "senior")) {
            return 2;
        }
        if (Objects.equals(str, "chicken")) {
            return 1;
        }
        if (Objects.equals(str, "pizza")) {
            return 2;
        }
        return 0;
    }
    
}