import java.util.*;
import java.util.stream.*;

class Solution {
    
    private boolean[][] frame0;
    private boolean[][] frame1;
    private int N;
    
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        frame0 = new boolean[n + 1][n + 1];
        frame1 = new boolean[n + 1][n + 1];
        N = n;
        
        for (int[] bf : build_frame) {
            int x = bf[0];
            int y = bf[1];
            int a = bf[2];
            int b = bf[3];
            
            if (b == 0) {
                if (a == 0) {
                    frame0[x][y] = false;
                    if (!isRemove()) {
                        frame0[x][y] = true;;
                    }
                }
                if (a == 1) {
                    frame1[x][y] = false;
                    if (!isRemove()) {
                        frame1[x][y] = true;;
                    }
                }
            }
            
            if (b == 1) {
                if (a == 0 && isInstall(x, y, a)) {
                    frame0[x][y] = true;
                }
                
                if (a == 1 && isInstall(x, y, a)) {
                    frame1[x][y] = true;
                }
            }
        }
        
        
        List<int[]> result = new ArrayList<>();
        for (int x = 0; x <= N; x++) {
            for (int y = 0; y <= N; y++) {
                if (frame0[x][y]) {
                    result.add(new int[]{x,y,0});
                }
                if (frame1[x][y]) {
                    result.add(new int[]{x,y,1});
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
            
            if (frame0[x][y - 1]) {
                return true;
            }
            
            if (frame1[x][y]) {
                return true;
            }
            
            if (x > 0 && frame1[x - 1][y]) {
                return true;
            }
        }
        
        if (a == 1) {
            if (y > 0) {
               if (frame0[x][y - 1] || frame0[x + 1][y - 1]) {
                    return true;
                } 
            }
            
            if (x > 0 && x < N) {
                if (frame1[x - 1][y] && frame1[x + 1][y]) {
                    return true;
                }
            }
        }
        
        return false;
    }
}
// [x, y, a, b] x,y 좌표(열, 행) / a 0 기둥, 1 보 / b 0 삭제, 1 설치
// 기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야함
// 보는 한쪽 끝 부분이 기둥위에 있거나, 또는 양쪽 끝부분이 다른 보와 동시에 연결되어 있어야함
// 보는 오른쪽, 기둥은 위쪽을 바라보게 설치
// 삭제 할 때 구조물이 파괴되지 않게 해야함

// 기둥이 설치 체크 배열
// 보 설치 체크 배열
// 설치 가능 체크 로직 => 기둥 설치 배열, 보 설치 배열 활용
// 설치 로직
// 삭제 가능 체크 로직 => 기둥 설치 배열, 보 설치 배열 활용
// 삭제 로직