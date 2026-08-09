import heapq

def solution(n, edge):
    graph = []
    for i in range(n + 1):
        graph.append([])

    for start, end in edge:
       graph[start].append([1, end]) 
       graph[end].append([1, start])

    dists = [float('inf')] * (n + 1)
    dists[1] = 0
    visited = [False] * (n + 1)
    heap = [[0, 1]]
    
    while heap:
        cost, node = heapq.heappop(heap)
        if (visited[node]):
            continue
        visited[node] = True

        for nxt_cost, nxt_node in graph[node]:
            new_cost = dists[node] + nxt_cost
            if dists[nxt_node] > new_cost:
                dists[nxt_node] = new_cost
                heapq.heappush(heap, [new_cost, nxt_node])
    
    return dists.count(max(dists[1:]))