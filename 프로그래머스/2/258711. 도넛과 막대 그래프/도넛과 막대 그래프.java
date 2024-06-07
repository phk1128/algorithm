import java.util.*;

class Solution {
    private boolean[] visited;
    private int[] result;
    private int[] count;
    private List<Integer>[] graph;
    public int[] solution(int[][] edges) {
        int[] answer = {};
        visited = new boolean[1_000_001];
        result = new int[4]; // 0, 1, 2, 3 => 정점, 도넛, 막대, 8자
        count = new int[2]; // 0, 1 => 노드, 간선 카운팅
        graph = new ArrayList[1_000_001];
        int root = 0;
        for (int i = 0; i < 1_000_001; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            visited[edge[1]] = true;
            graph[edge[0]].add(edge[1]);
        }
        // 정점 찾기
        for (int i = 1; i < 1_000_001; i++) {
            if (!visited[i]) {
                if (graph[root].size() < graph[i].size()) {
                    root = i;
                }
            }
        }
        visited = new boolean[1_000_001];
        result[0] = root;
        // 루트랑 연결된 그래프 탐색
        for (int node : graph[root]) {
            count[0] = 1;
            count[1] = 0;
            search(node);
            if (count[0] == count[1]) {
                result[1]++;
            }
            if (count[0] > count[1]) {
                result[2]++;
            }
            if (count[0] < count[1]) {
                result[3]++;
            }
        }
        answer = result;
        return answer;
    }
    
    private void search(int start) {
        count[1] += graph[start].size();
        for (int node : graph[start]) {
            if (visited[node]) {
                continue;
            }
            visited[node] = true;
            count[0]++;
            search(node);
        }
    }
}
// 정점은 나가는 간선이 가장 많고, 들어오는 간선이 없는것
// 막대 : 노드수 - 1 = 간선수
// 도넛 : 노드수 = 간선수
// 8자 : 노드수 + 1 = 간선수
// 재귀로 탐색