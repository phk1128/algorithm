from collections import defaultdict

def solution(n, wires):
    answer = float('inf')
    for i in range(n-1):
        tmp_wires = wires.copy()
        del tmp_wires[i]
        answer = min(answer, count(tmp_wires, n))
    return answer
    
def count(wires, n):
    global parents
    parents = []
    for i in range(n + 1):
        parents.append(i)
    
    for wire in wires:
        union(wire[0], wire[1])
    
    s = set()
    for i in range(1, n + 1):
        s.add(find(i))
    
    map = defaultdict(int)
    if (len(s) == 2):
        a, b = s
        for i in range(1, n + 1):
            map[find(i)] += 1
        return abs(map[a] - map[b])
    return float('inf')

def find(x):
    if(parents[x] == x):
        return x
    return find(parents[x])

def union(x, y):
    x = find(x)
    y = find(y)

    if (x > y):
        parents[x] = y
        return True
    if (x < y):
        parents[y] = x
        return True
    return False
    