let count = new Array(2).fill(0);

function solution(arr) {
    var answer = [];
    recursiveSolve(0,0,arr.length,arr);
    answer = count;
    return answer;
}

function recursiveSolve(startR, startC, size, arr) {
    
    if (size <= 0) {
        return;
    }
    
    let number = arr[startR][startC];
    let flag = true;
    for (let r = startR; r < startR + size; r++) {
        for (let c = startC; c < startC + size; c++) {
            if (arr[r][c] !== number) {
                flag = false;
                break;
            }
        }
    }
    
    if (flag) {
        count[number]++;
        return;
    }
    
    recursiveSolve(startR, startC, size / 2, arr);
    recursiveSolve(startR + size / 2, startC, size / 2, arr);
    recursiveSolve(startR , startC + size / 2, size / 2, arr);
    recursiveSolve(startR + size / 2, startC + size / 2, size / 2, arr);
    
}