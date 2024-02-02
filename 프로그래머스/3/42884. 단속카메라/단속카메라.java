import java.util.*;
import java.util.stream.*;
class Solution {
    private List<int[]> routeList;
    public int solution(int[][] routes) {
        int answer = 0;
        routeList = new ArrayList<>();
        
        for (int[] route : routes) {
            routeList.add(route);
        }
        Collections.sort(routeList, (route1, route2) -> (route1[1] - route2[1]));
        
        while(!routeList.isEmpty()) {
            int r = routeList.get(0)[1];
            
            for (int i = 0; i < routeList.size(); i++) {
                if (r >= routeList.get(i)[0]) {
                    routeList.remove(i);
                    i--;
                }
            }
            answer++;
        }
        
        return answer;
    }
}