let answer;

function solution(p) {
    answer = "";
    
    if (isPerfect(p)) {
        return p;
    }
    
    answer = divide(p);
    
    return answer;
}

function divide(str) {
    
    if (str === "") {
        return "";
    }
    
    let stack = [];
    let result = [];
    let idx = 0;
    
    for (let i = 0; i < str.length; i++) {
        if (stack.length === 0) {
            stack.push(str[i]);
            result.push(str[i]);
            continue;
        }
        
        let tmp = stack[stack.length - 1];
        if (tmp === "(" && str[i] === ")") {
            stack.pop();
            result.push(str[i]);
            if (stack.length === 0) {
                idx = i + 1;
                break;
            }
            continue;
        }
        
        if (tmp === ")" && str[i] === "(") {
            stack.pop();
            result.push(str[i]);
            if (stack.length === 0) {
                idx = i + 1;
                break;
            }
            continue;
        }
        
        stack.push(str[i]);
        result.push(str[i]);
    }
    
    let u = result.join("");
    let v = str.substring(idx, str.length);
    
    if (isPerfect(u)) {
        return u + divide(v);
    } else {
        let tmpStr = "(" + divide(v) + ")";
        result.pop();
        result.shift();
        let convert = [];
        for (let s of result) {
            if (s === "(") {
                convert.push(")");
            }
            
            if (s === ")") {
                convert.push("(");
            }
        }
        return tmpStr + convert.join("");
    }
}

function isPerfect(str) {
    
    let stack = []
    for (let s of str) {
        if (stack.length === 0) {
            stack.push(s);
            continue;
        }
        
        let tmp = stack[stack.length - 1];
        if (tmp === "(" && s === ")") {
            stack.pop();
            continue;
        } 
        
        stack.push(s);
    }
    
    if (stack.length !== 0) {
        return false;
    }
    
    return true;
}