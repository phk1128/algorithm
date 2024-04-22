let n;
let cores;
let time;
let count;

function solution(n, cores) {
    var answer = 0;
    global.n = n;
    global.cores = cores;
    
    let minTime = 0;
    let maxTime = 10_000 * n;
    
    binarySearch(minTime, maxTime, n, 0);
    
    let rest = global.count - n;
    
    for (let i = cores.length - 1; i >= 0; i--) {
        
        if (global.time % cores[i] === 0) {
            
            if (rest <= 0) {
                answer = i + 1;
                break;
            }
            rest--;
        }
    }
    
    return answer;
}

function binarySearch(start, end, target, count) {
    
    if (start >= end) {
        
        return;
    }
    
    let mid = Math.floor((start + end) / 2);
    count = calculate(mid);
    
    if (count < target) {
        binarySearch(mid + 1, end, target, count);
    }
    
    if (count >= target) {
        global.count = count;
        global.time = mid;
        binarySearch(start, mid, target, count);
    }
    
}

function calculate(time) {
    
    if (time === 0) {
        return global.cores.length;
    }
    
    let count = global.cores.length;
    for (let core of global.cores) {
        count += Math.floor(time / core);
    }
    
    return count;
}