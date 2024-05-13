import java.util.*;
import java.util.stream.*;

class Solution {
    
    private int answer;
    
    public int solution(int n, int k, int[] enemy) {
        answer = 0;
        binarySearch(0, enemy.length, n, k, enemy);
        return answer;
    }
    
    private void binarySearch(int start, int end, int n, int k, int[] enemy) {
        
        if (start >= end) {
            answer = start;
            return;
        }
        
        int mid = (start + end) / 2;
        System.out.println(mid);
        int[] tmpEnemy = Arrays.stream(enemy, 0, mid + 1).toArray();
        Arrays.sort(tmpEnemy);
        int tmpN = n;
        boolean flag = true;
        
        for (int i = 0; i < tmpEnemy.length; i++) {
            if (tmpN >= tmpEnemy[i]) {
                tmpN -= tmpEnemy[i];
                continue;
            }
            
            if (k < tmpEnemy.length - i) {
                flag = false;
            }
            break;
        }
        
        if (flag){
            binarySearch(mid + 1, end, n, k, enemy);
        } else {
            binarySearch(start, mid, n, k, enemy);
        }
    }
}