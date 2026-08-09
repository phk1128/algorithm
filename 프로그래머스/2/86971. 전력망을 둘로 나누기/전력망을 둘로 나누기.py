from collections import deque

def solution(n, wires):
    answer = float('inf')
    for i in range(n - 1):
        graph = []
        for j in range(n + 1):
            graph.append([])

        tmp_wires = wires.copy()
        del tmp_wires[i]
        
        for wire in tmp_wires:
            graph[wire[0]].append(wire[1])
            graph[wire[1]].append(wire[0])
        result = bfs(n, graph)
        answer = min(answer, abs(result - (n-result)))
    return answer
        

def bfs(n, graph):
    visited = [False] * (n + 1)
    queue = deque()
    queue.append(1)
    visited[1] = True
    cnt = 1
    while queue:
        cur = queue.popleft()
        for node in graph[cur]:
           if visited[node]:
               continue
           cnt += 1
           visited[node] = True
           queue.append(node)
    return cnt