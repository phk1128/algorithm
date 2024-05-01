import java.util.*;

class Solution {
    
    private static long[] people;
    private static int[] combi;
    private static long answer;
    
    public long solution(int[] weights) {
        answer = 0L;
        people = new long[1001];
        combi = new int[2];
        Set<Integer> set = new HashSet<>();
        for (int weight : weights) {
            people[weight]++;
            set.add(weight);
        }
        
        
        List<Integer> list = new ArrayList<>(set);
        
        for (int i = 0; i < list.size(); i++) {
            long num = people[list.get(i)];
            if (num >= 2) {
                answer += (long) (num * (num - 1)) / 2;
            }
            
        }
        
        recursiveSolve(0,0,list);
    
            
        return answer;
    }
    
    
    private static void recursiveSolve(int start, int depth, List<Integer> list) {
        
        if (depth == 2) {
            if (isPair()) {
                answer += (long) (people[combi[0]] * people[combi[1]]);
            }
            return;
        }
        
        for (int i = start; i < list.size(); i++) {
            combi[depth] = list.get(i);
            recursiveSolve(i+1, depth+1,list);
        }
    }
    
    private static boolean isPair() {
        
        if (combi[0] == combi[1]) {
            return true;
        }
        
        if (combi[0] * 2 == combi[1] * 3 || combi[0] * 2 == combi[1] * 4) {
            return true;
        } 
        
        if (combi[0] * 3 == combi[1] * 2 || combi[0] * 3 == combi[1] * 4) {
            return true;
        }
        
        if (combi[0] * 4 == combi[1] * 2 || combi[0] * 4 == combi[1] * 3) {
            return true;
        }
        
        return false;
        
    }
}