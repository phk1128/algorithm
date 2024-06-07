import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;
        boolean[][][][] visited = new boolean[11][11][11][11];
        int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}}; // 0,1,2,3 = U,D,L,R
        
        int cR = 5;
        int cC = 5;
        for (int i = 0; i < dirs.length(); i++) {
            int nR = cR;
            int nC = cC;
            if (Objects.equals(dirs.charAt(i), 'U')) {
                nR = cR + directions[0][0];
                nC = cC + directions[0][1];
                if (!isMove(nR, nC)) {
                    continue;
                }
                if (!(visited[cR][cC][nR][nC] && visited[nR][nC][cR][cC])) {
                    answer++;
                    visited[cR][cC][nR][nC] = true;
                    visited[nR][nC][cR][cC] = true;
                }
                cR = nR;
                cC = nC;
                continue;
            }
            if (Objects.equals(dirs.charAt(i), 'D')) {
                nR = cR + directions[1][0];
                nC = cC + directions[1][1];
                if (!isMove(nR, nC)) {
                    continue;
                }
                if (!(visited[cR][cC][nR][nC] && visited[nR][nC][cR][cC])) {
                    answer++;
                    visited[cR][cC][nR][nC] = true;
                    visited[nR][nC][cR][cC] = true;
                }
                cR = nR;
                cC = nC;
                continue;
            }
            if (Objects.equals(dirs.charAt(i), 'L')) {
                nR = cR + directions[2][0];
                nC = cC + directions[2][1];
                if (!isMove(nR, nC)) {
                    continue;
                }
                if (!(visited[cR][cC][nR][nC] && visited[nR][nC][cR][cC])) {
                    answer++;
                    visited[cR][cC][nR][nC] = true;
                    visited[nR][nC][cR][cC] = true;
                }
                cR = nR;
                cC = nC;
                continue;
            }
             if (Objects.equals(dirs.charAt(i), 'R')) {
                nR = cR + directions[3][0];
                nC = cC + directions[3][1];
                if (!isMove(nR, nC)) {
                    continue;
                }
                if (!(visited[cR][cC][nR][nC] && visited[nR][nC][cR][cC]))  {
                    answer++;
                    visited[cR][cC][nR][nC] = true;
                    visited[nR][nC][cR][cC] = true;
                }
                cR = nR;
                cC = nC;
            }
        }
        
        return answer;
    }
    
    private boolean isMove(int r, int c) {
        return r >= 0 && r <= 10 && c >= 0 && c <= 10;
        
    }
}