function solution(numbers) {
    var answer = [];
    let flag;
    
    for (let number of numbers) {
        
        let binaryStr = getBinary(number, "");
        
        let height = 1;
        
        while (true) {
            let tmp = Math.pow(2,height);
            
            if (binaryStr.length === 1) {
                break;
            }
            
            if (Math.floor(binaryStr.length / tmp) === 0) {
                break;
            }
            height++;
        }
        
        let pow = Math.pow(2, height) - 1;
        let diff = pow - binaryStr.length;
        
        for (let i = 0; i < diff; i++) {
            binaryStr = "0" + binaryStr;
        }
        
        let root = Math.floor(binaryStr.length / 2);
        
        let result = 1;
        if (!isPerfectBinaryTree(root, binaryStr, height - 1)) {
            result = 0;
        }
        answer.push(result);
        
    }
    return answer;
}

function isPerfectBinaryTree(idx, binaryStr, height) {
    
    if (height === 0) {
        return true;
    }
    
    let diff = Math.pow(2, height - 1);
    let left = idx - diff;
    let right = idx + diff;
    
    if (binaryStr[idx] === "0") {
        if (binaryStr[left] === "1" || binaryStr[right] === "1") {
           return false;
        }
    }
    
    return isPerfectBinaryTree(left, binaryStr, height - 1) && isPerfectBinaryTree(right, binaryStr, height - 1);
    
    
}

function getBinary(number, result) {
    
    if (number === 0) {
        
        return result;
    }
    
    if (number % 2 === 0) {
        result = getBinary(Math.floor(number / 2), "0" + result);
    }
    
    if (number % 2 !== 0) {
        result = getBinary(Math.floor(number / 2), "1" + result);
    }
    
    return result;
}