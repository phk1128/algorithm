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
        
        int r = routeList.get(0)[1];
        answer++;
        
        while(!routeList.isEmpty()) {
            if (r >= routeList.get(0)[0]) {
                routeList.remove(0);
            } else {
                r = routeList.get(0)[1];
                answer++;
            }
        }
        return answer;
    }
}