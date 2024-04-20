const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./example.txt";
const inputs = fs.readFileSync(filePath).toString().trim().split("\n");
const [V, E] = inputs.shift().split(" ").map(Number);
let graph = [];
let parents = new Array(V + 1);

class Edge {

    constructor(start, end, weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

for (let i = 1; i <= V; i++) {
    parents[i] = i;
}

for (let input of inputs) {
    let [start, end, weight] = input.split(" ").map(Number);
    graph.push(new Edge(start, end, weight));
}

main();

function main() {
    graph.sort((e1, e2) => e1.weight - e2.weight);
    console.log(kruskal());
}


function kruskal() {
    let answer = 0;
    for (let edge of graph) {
        let start = edge.start;
        let end = edge.end;
        let weight = edge.weight;

        if (union(start, end)) {
            answer += weight;
        }
    }

    return answer;
}

function find(x) {

    if (x === parents[x]) {
        return x;
    }

    return find(parents[x]);

}

function union(x, y) {
    x = find(x);
    y = find(y);


    if (x > y) {
        parents[x] = y;
        return true;
    }

    if (y > x) {
        parents[y] = x;
        return true;
    }

    return false;
}



