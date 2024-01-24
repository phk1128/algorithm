from collections import defaultdict
result = []
graph = defaultdict(list)

def dfs(s):
    while graph[s]:
        
        dfs(graph[s].pop(0))
        
    if not graph[s]:
        result.append(s)
        return

def solution(tickets):
    for a,b in tickets:
        graph[a].append(b)
    for a in graph:
        graph[a].sort()
        
    dfs("ICN")
    return result[::-1]