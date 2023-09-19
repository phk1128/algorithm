import copy
import sys
from collections import deque

def bfs(y,x,cnt,breakCnt):
    queue = deque()
    queue.append((y,x,cnt,breakCnt))
    while queue:
        y,x,cnt,breakCnt = queue.popleft()
        if x == m-1 and y == n-1:
            return cnt
        for move in moves:
            mx, my = move
            nx, ny =  x+mx, y+my

            if 0 <= nx < m and 0 <= ny < n and mapView[ny][nx] == 0 and breakCnt <= 1:
                mapView[y][x] = 1
                queue.append((ny,nx,cnt + 1,breakCnt))
                

            if 0 <= nx < m and 0 <= ny < n and mapView[ny][nx] == 1 and breakCnt < 1:
                mapView[y][x] = 1
                queue.append((ny,nx,cnt + 1,1))

input = sys.stdin.readline
n, m = map(int,input().split())
mapView = [list(map(int ,input().rstrip())) for _ in range(n)]
moves = [(-1,0),(1,0),(0,-1),(0,1)]

result = bfs(0,0,1,0)
if result != None:  
    print(result)
else:
    print(-1)





        





