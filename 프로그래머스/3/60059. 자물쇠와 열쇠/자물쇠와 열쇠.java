import java.util.*;

class Solution {
    
    private int[][] keyMapView;
    private int[][] lockMapView;
    private boolean answer;
    
    public boolean solution(int[][] key, int[][] lock) {
        answer = false;
        int size = Math.max(key.length, lock.length);
        keyMapView = new int[size][size];
        lockMapView = new int[size][size];
        setMapView(keyMapView, key, size);
        setMapView(lockMapView, lock, size);
        
        for (int i = 0; i < 4; i++) {
            // 키 회전 
            keyMapView = getTurnKey();
            solve(size);
            if (answer) {
                break;
            }
        }
    
        return answer;
    }
    
    private void solve(int size) {
        
        // 키를 움직이면서 열수있는지 확인
        for (int moveR = 1; moveR < size * 2; moveR++) {
            for (int moveC = 1; moveC < size * 2; moveC++) {
                if (isOpen(moveR, moveC, size)) {
                    answer = true;
                    return;
                }
            }
        }
    }
    
    // 자물쇠를 열수있는지 확인
    private boolean isOpen(int moveR, int moveC, int size) {
        int[][] tmpLockMapView = new int[size][size];
        for (int i = 0; i < size; i++) {
            tmpLockMapView[i] = Arrays.copyOf(lockMapView[i], size);
        }
    
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                // keyR, keyC는 자물쇠에 대응하는 열쇠의 위치들을 나타낸다.
                // size= 3, moveR = 1, moveC = 1 이면 자물쇠 0,0 에는 열쇠의 2,2 가 대응하게 된다.
                // size= 3, moveR = 5, moveC = 5 이면 자물쇠 2,2 에는 열쇠의 0,0 가 대응하게 된다.
                int keyR = r + (size - moveR);
                int keyC = c + (size - moveC);
                if (!(keyR >= 0 && keyR < size && keyC >= 0 && keyC < size)) {
                    continue;
                }
                tmpLockMapView[r][c] += keyMapView[keyR][keyC];
            }
        }
        
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (tmpLockMapView[r][c] != 1) {
                    return false;
                }
            }
        }
        
        return true;
        
    }
    
    
    // 키를 시계 방향으로 90도 회전
    private int[][] getTurnKey() {
        int size = keyMapView.length;
        int[][] turnKey = new int[size][size];
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                turnKey[c][size - (r + 1)] = keyMapView[r][c];
            }
        }
        return turnKey;
    }
    
    // 최대 사이즈에 맞게 맵뷰 설정
    private void setMapView(int[][] mapView, int[][] arr, int size) {
        int diff = size - arr.length;
        for(int r = 0; r < arr.length; r++) {
            for (int c = 0; c < arr.length; c++) {
                int newR = r + diff;
                int newC = c + diff;
                mapView[newR][newC] = arr[r][c];
            }
        }    
    }
}

// 키와 자물쇠의 사이즈를 일치시켜야함 둘 중 큰 사이즈를 기준으로 일치
// 시계 방향으로 4번 회전
// 상하좌우로 움직이면서 자물쇠와 열쇠가 일치하는지 확인
// 움직였던 변위를 저장해야함
// 열쇠 홈과 자물쇠 홈은 겹치면 안됨
// 자물쇠가 전부 1이면 true 
