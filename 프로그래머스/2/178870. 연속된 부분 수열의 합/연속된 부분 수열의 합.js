function solution(sequence, k) {
    var answer = new Array(2);
    
    let queue = [];
    let len = Number.MAX_SAFE_INTEGER;
    let sum = 0;
    let idx = 0;
    for (let i = 0; i < sequence.length; i++) {
        
        let num = sequence[i];
        queue.push(num);
        sum += num;
        
        while (sum > k) {
            sum -= queue[idx++];
        }
        
        if (sum === k) {
            if (len > i - idx) {
                answer[0] = idx;
                answer[1] = i;
                len = i - idx;
            }
        }
    }
    return answer;
}