import java.util.*;

class Solution {
    public int solution(int N, int number) {
        List<Set<Integer>> nums = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            nums.add(new HashSet<>());
        }
        nums.get(1).add(N);
        for (int i = 2; i < 9; i++) {
            Set<Integer> num = nums.get(i); 
            for (int j = 1; j < i; j++) {
                Set<Integer> pre = nums.get(j);
                Set<Integer> post = nums.get(i - j);
                
                for (int k : pre) {
                    for (int l : post) {
                        num.add(k * l);
                        num.add(k + l);
                        num.add(k - l);
                        
                        if (l != 0) {
                            num.add(k / l);
                        }
                    }
                }
            }
            num.add(Integer.parseInt(String.valueOf(N).repeat(i)));
        }
        
        for (int i = 1; i < 9; i++) {
            if (nums.get(i).contains(number)) {
                return i;
            }
        }
        return -1;
    }
}