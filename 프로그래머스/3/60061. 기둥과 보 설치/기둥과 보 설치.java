import java.util.*;
import java.util.stream.*;

class Solution {
    
    private boolean[][] frame0;
    private boolean[][] frame1;
    private int N;
    
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        frame0 = new boolean[n + 3][n + 3];
        frame1 = new boolean[n + 3][n + 3];
        N = n;
        
        for (int[] bf : build_frame) {
            int x = bf[0];
            int y = bf[1];
            int a = bf[2];
            int b = bf[3];
            
            if (b == 1) {
                if (a == 0) {
                    if (isInstall(x, y, 0)) {
                        frame0[x][y] = true;
                    }
                } else {
                    if (isInstall(x, y, 1)) {
                        frame1[x][y] = true;
                    }
                }
            } else {
                if (a == 0) {
                    frame0[x][y] = false;
                    if (!isRemove()) {
                        frame0[x][y] = true;
                    }
                } else {
                    frame1[x][y] = false;
                    if (!isRemove()) {
                        frame1[x][y] = true;
                    }
                }
            }
        }
        
        List<int[]> result = new ArrayList<>();
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                if (frame0[x][y]) {
                    result.add(new int[]{x, y, 0});
                }
                if (frame1[x][y]) {
                    result.add(new int[]{x, y, 1});
                }
            }
        }
        
        answer = result.stream().toArray(int[][]::new);
        
        Arrays.sort(answer, (a1, a2) -> {
            if (a1[0] != a2[0]) {
                return a1[0] - a2[0];
            }
            if (a1[1] != a2[1]) {
                return a1[1] - a2[1];
            }
            return a1[2] - a2[2];
        });
        
        return answer;
    }
    
    private boolean isRemove() {
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                if (frame0[x][y] && !isInstall(x, y, 0)) {
                    return false;
                }
                if (frame1[x][y] && !isInstall(x, y, 1)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isInstall(int x, int y, int a) {
        if (a == 0) {
            if (y == 0) {
                return true;
            }
            
            if (y > 0 && frame0[x][y - 1]) {
                return true;
            }
            
            if (x <= N && frame1[x][y]) {
                return true;
            }
            
            if (x > 0 && frame1[x - 1][y]) {
                return true;
            }
            
            return false;
        }
        
        if (a == 1) {
            if (y > 0 && (frame0[x][y - 1] || frame0[x + 1][y - 1])) {
                return true;
            }
            
            if (x > 0 && x + 1 <= N && frame1[x - 1][y] && frame1[x + 1][y]) {
                return true;
            }
            
            return false;
        }
        
        return false;
    }
}