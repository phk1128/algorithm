import java.util.*;

class Solution {
    
    private static List<Integer> listA;
    private static int answer;
    private static boolean result;
    
    
    public int solution(int[] A, int[] B) {
        answer = 0;
        
        listA = new ArrayList();
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < A.length; i++) {
            listA.add(A[i]);
            queue.offer(B[i]);
        }
        
        Collections.sort(listA);
        
        while(!queue.isEmpty()) {
            result = false;
            int target = queue.poll();
            binarySearch(0, listA.size() - 1, target);
            
            if (result) {
                listA.remove(0);
            }
            
        }
        
        
        
        return answer;
    }
    
    private static void binarySearch(int start, int end, int target) {
        
        if (start == end) {
            if (start != 0) {
                result = true;
                answer++;
            }
            
            if (listA.size() == 1) {
                if (target > listA.get(0)) {
                    answer++;
                }
            }
            
            return;
        }
        
        int mid = (start + end) / 2;
        
        if (target > listA.get(mid)) {
            
            binarySearch(mid + 1, end, target);
        }
        
        if (target <= listA.get(mid)) {
            
            binarySearch(start, mid, target);
        }
    }
}