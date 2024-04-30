let arr;
let sum;

function solution(n) {
    var answer = [];
    sum = (n * (n + 1)) / 2;
    
    arr = Array.from(new Array(n), () => []);
    for (let i = 0; i < n; i++) {
        arr[i] = new Array(i+1).fill(0);
    }
    
    recursiveSolve(0,n-1,0,1);
    
    let idx = 0;
    for (let i = 0; i < arr.length; i++) {
        for (let num of arr[i]) {
            answer[idx++] = num;
        }
    }
    
    return answer;
}

function recursiveSolve(startR, limitR, startC, number) {
    
    // 숫자가 블럭의 숫자보다 커지면 종료 (=블럭이 다채워지면)
    if (number > sum) {
        return;
    }
    
    // 왼쪽으로 내려가면서 숫자를 채운다.
    for (let r = startR; r < limitR; r++) {
        arr[r][startC] = number++;
    }
    
    // 맨 아랫층 숫자를 채운다.
    for (let c = startC; c <= limitR - startC; c++) {
        arr[limitR][c] = number++;
    }
    
    // 오른쪽으로 올라가면서 숫자를 채운다.
    for (let r = limitR - 1; r > startR; r--) {
        arr[r][r - startC] = number++;
    }
    
    // 시작층 + 2, 마지막층 - 1, 시작열 + 1
    recursiveSolve(startR + 2, limitR - 1, startC + 1, number);
}