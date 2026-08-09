def solution(n, computers):
    global visited
    answer = 0
    visited = [False] * n

    for i in range(n):
        if (visited[i]):
            continue
        answer += 1
        dfs(n, computers, i, 0)
        
    return answer


def dfs(n, computers, node, depth):
    if depth >= n:
        return
    for i in range(n):
        if (visited[i] or computers[node][i] == 0):
            continue
        visited[i] = True
        dfs(n, computers, i, depth + 1)
