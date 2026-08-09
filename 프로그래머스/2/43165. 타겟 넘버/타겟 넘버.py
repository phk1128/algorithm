def solution(numbers, target):
    global results
    results = []
    dfs(numbers, 0, 0)
    return results.count(target)

def dfs(numbers, result, depth):
    if (depth >= len(numbers)):
        results.append(result)
        return

    dfs(numbers, result + numbers[depth], depth + 1)
    dfs(numbers, result - numbers[depth], depth + 1)