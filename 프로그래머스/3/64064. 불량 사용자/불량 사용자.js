let combi;
let set;
let banMap;

function solution(user_id, banned_id) {
    var answer = 0;
    
    banMap = new Map();
    combi = new Array(banned_id.length);
    set = new Set();
    
    for (let ban of banned_id) {
        banMap.set(ban, []);
        for (let user of user_id) {
            if (ban.length !== user.length) {
                continue;
            }
            let flag = true;
            for (let i = 0; i < ban.length; i++) {
                if (user[i] !== ban[i] && ban[i] !== "*") {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                banMap.get(ban).push(user);
            }
        }
    }
    
    recursiveSolve(0, banned_id.length, banned_id);
    answer = set.size;
    
    return answer;
    
}

function recursiveSolve(idx, limit, banned_id) {
    
    if (idx === limit) {
        
        let tmpCombi = Array.from(combi);
        tmpCombi.sort();
        set.add(tmpCombi.join(""));
        
        return;
    }
    
    for (let ban of banMap.get(banned_id[idx])) {
        
        if (combi.includes(ban)) {
            continue;
        }
        
        combi[idx] = ban;
        recursiveSolve(idx + 1, limit, banned_id);
        combi[idx] = "";
    }
    
}