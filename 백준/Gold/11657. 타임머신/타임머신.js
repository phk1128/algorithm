const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./example.txt";
const inputs = fs.readFileSync(filePath).toString().trim().split("\n");
const [N, M] = inputs.shift().split(" ").map(Number);
let graph = [];
let dist = new Array(N + 1).fill(Number.MAX_SAFE_INTEGER);

class Edge {
    constructor(start, end, weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

main();

function main() {

    for (let input of inputs) {
        let [start, end, weight] = input.split(" ").map(Number);
        graph.push(new Edge(start, end, weight));
    }

    console.log(getAnswer());
}

function getAnswer() {
    let answer = -1;
    if (bellManFord(1)) {
        answer = dist.slice(2).map(d => (d === Number.MAX_SAFE_INTEGER ? -1 : d)).join("\n");
    }
    return answer;
}

function bellManFord(s) {

    dist[s] = 0;

    for (let i = 1; i <= N; i++) {
        for (let edge of graph) {
            let start = edge.start;
            let end = edge.end;
            let weight = edge.weight;

            if (dist[start] === Number.MAX_SAFE_INTEGER) {
                continue;
            }

            if (dist[end] > dist[start] + weight) {
                dist[end] = dist[start] + weight;

                if (i === N) {
                    return false;
                }
            }
        }
    }
    return true;
}