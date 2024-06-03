def solution(n, times):
    times.sort()
    left, right = 1, max(times)*n
    people = 0
    while (left <= right):
        mid = (left + right) // 2
        people = 0
        for time in times:
            people += (mid//time)
            
            if people >= n:
                break  
        if people >= n:
            right = mid-1
        else:
            left = mid + 1 
    answer = left
    return answer