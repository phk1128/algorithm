def solution(n, computers):
    global parents
    parents = []
    for i in range(n):
        parents.append(i)
    
    for i in range(n):
        for j in range(n):
            if (computers[i][j] == 1):
                union(i, j)
    s = set()
    for i in range(n):
        s.add(find(i))

    return len(s)

def union(x, y):
    x = find(x)
    y = find(y)

    if (x > y):
        parents[x] = y
    if (x < y):
        parents[y] = x

def find(x):
    if (parents[x] == x):
        return x
    return find(parents[x])