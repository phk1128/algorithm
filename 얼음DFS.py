def dfs(x,y):
    if x <= -1 or x >= n or y <= -1 or y >= m:
        return False
    if graph[x][y] == 0:

        #방문처리
        graph[x][y] = 1
        
        #상,하,좌,우 움직이면서 탐색
        dfs(x-1,y)
        dfs(x,y-1)
        dfs(x+1,y)
        dfs(x,y+1)
        return True  



# n x m 입력 받기
n, m = map(int,input().split())

graph = []



# input은 문자로 인식 받기 때문에 map을 사용해야함
for i in range(n):
    graph.append(list(map(int, input())))

result = 0

for i in range(n):
    for j in range(m):
        if dfs(i,j) == True:
            result += 1

print(result)


