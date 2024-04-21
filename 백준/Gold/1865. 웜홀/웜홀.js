const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./example.txt";
const inputs = fs.readFileSync(filePath).toString().trim().split("\n");
const T = Number(inputs.shift());
let N, M, H;
let graph;
let dist;

class Edge {

    constructor(start, end, weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

}

main();

function main() {
    let idx = 0;
    let answer = [];
    for (let t = 0; t < T; t++) {
        [N, M, W] = inputs[idx++].split(" ").map(Number);
        graph = [];

        for (let i = 0; i < M; i++) {
            let [start, end, weight] = inputs[idx++].split(" ").map(Number);
            graph.push(new Edge(start, end, weight));
            graph.push(new Edge(end, start, weight));
        }

        for (let w = 0; w < W; w++) {
            let [start, end, weight] = inputs[idx++].split(" ").map(Number);
            graph.push(new Edge(start, end, -weight));
        }


        let result = "NO";
        for (let node = 1; node <= N; node++) {
            if (bellManFord(node)) {
                result = "YES";
                break;
            }
        }
        answer.push(result);
    }

    console.log(answer.join("\n"));
}

function bellManFord(s) {
    dist = new Array(N + 1).fill(Infinity);
    dist[s] = 0;

    for (let i = 1; i <= N; i++) {
        let flag = false;
        for (let edge of graph) {
            let start = edge.start;
            let end = edge.end;
            let weight = edge.weight;

            if (dist[start] === Infinity) {
                continue;
            }

            if (dist[end] > dist[start] + weight) {
                dist[end] = dist[start] + weight;
                flag = true;
                if (i === N) {
                    return true;
                }
            }
        }

        if (!flag) {
            return false;
        }
    }
    return false;
}