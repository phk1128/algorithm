let answer;
let visited;
let combi;
let idx;

function solution(tickets) {
    answer = [];
    let map = new Map();
    visited = new Map();
    combi = new Array(tickets.length + 1);
    combi[0] = "ICN";
    idx = 0;
    
    for (let ticket of tickets) {
        let[start, end]  = ticket;
        let tmpMap = map.get(start);
        if (tmpMap === undefined) {
            map.set(start, [end]);
            continue;
        }
        tmpMap.push(end);
    }
    
    for (let key of map.keys()) {
        visited.set(key, new Array(map.get(key).length).fill(false));
    }
    
    search("ICN", map, 1, tickets.length + 1);
    console.log(answer);
    
    return answer[0];
}

function search(start, map, depth, limit) {
    if (depth === limit) {
        answer.push(Array.from(combi));
        return;
    }
    
    let ends = map.get(start);
    
    if (ends === undefined) {
        return;
    }
    
    ends.sort();
    
    for (let i = 0; i < ends.length; i++) {
        let end = ends[i];
        
        if (visited.get(start)[i]) {
            continue;
        }
        visited.get(start)[i] = true;
        combi[depth] = end;
        search(end, map, depth + 1, limit);
        visited.get(start)[i] = false;
    }
}