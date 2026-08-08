from collections import deque

def solution(maps):
    global answer, dr, dc, map_view, visited
    answer = -1
    dr = [1, -1, 0, 0]
    dc = [0, 0, -1, 1]
    map_view = maps
    visited = []
    for i in range(len(map_view)):
        row = []
        for j in range(len(map_view[0])):
            row.append(False)
        visited.append(row)
    visited[0][0] = True
    result = bfs()
    if result is not None:
        answer = result
    return answer
    

def bfs():
    queue = deque([])
    queue.append([0,0,1])
    while queue:
        cur = queue.popleft()
        cr = cur[0]
        cc = cur[1]
        if cr == len(map_view) - 1 and cc == len(map_view[0]) - 1:
            return cur[2]
        
        for i in range(4):
            nr = cr + dr[i]
            nc = cc + dc[i]
            if not is_in(nr, nc):
                continue
            if visited[nr][nc] or map_view[nr][nc] == 0:
                continue
            visited[nr][nc] = True
            queue.append([nr, nc, cur[2] + 1])
                

def is_in(r, c) -> bool:
    return r >= 0 and r < len(map_view) and c >= 0 and c < len(map_view[0])