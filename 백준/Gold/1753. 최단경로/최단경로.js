const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./example.txt";
const inputs = fs.readFileSync(filePath).toString().trim().split("\n");
const [V, E] = inputs.shift().split(" ").map(Number);
const K = Number(inputs.shift());
let graph = Array.from({length: V + 1}, () => []);
let visited = new Array(V + 1).fill(false);
let dist = new Array(V + 1).fill(Number.MAX_SAFE_INTEGER);

class PriorityQueue {

    constructor() {
        this.queue = [0];
    }

    compare(e1, e2) {
        return e1.weight - e2.weight;
    }

    isEmpty() {
        return this.queue.length <= 1;
    }

    offer(edge) {

        let idx = this.queue.length;

        while (idx > 1 && this.compare(edge, this.queue[Math.floor(idx / 2)]) <= 0) {
            this.queue[idx] = this.queue[Math.floor(idx / 2)];
            idx = Math.floor(idx / 2);
        }
        this.queue[idx] = edge;
    }

    poll() {

        if (this.queue.length <= 1) {
            throw new Error("NoSuchElementException");
        }

        let first = this.queue[1];
        let last = this.queue.pop();
        let size = this.queue.length - 1;

        if (size === 0) {
            return first;
        }

        let pIdx = 1;
        let cIdx = 2;

        while (cIdx <= size) {

            if (this.queue[cIdx + 1] !== undefined && this.compare(this.queue[cIdx], this.queue[cIdx + 1]) > 0) {
                cIdx++;
            }

            if (this.compare(last, this.queue[cIdx]) <= 0) {
                break;
            }
            this.queue[pIdx] = this.queue[cIdx];

            pIdx = cIdx;
            cIdx *= 2;
        }

        this.queue[pIdx] = last;
        return first;
    }


}

class Edge {

    constructor(node, weight) {
        this.node = node;
        this.weight = weight;
    }

}

main();

function main() {

    for (let input of inputs) {
        let [start, end, weight] = input.split(" ").map(Number);
        graph[start].push(new Edge(end, weight));
    }

    dijkstra();

    let answer = dist.slice(1).map(d => (d === Number.MAX_SAFE_INTEGER ? "INF" : d)).join("\n");
    console.log(answer);

}

function dijkstra() {

    let queue = new PriorityQueue();
    queue.offer(new Edge(K, 0));
    dist[K] = 0;

    while (!queue.isEmpty()) {

        let edge = queue.poll();
        let node = edge.node;

        if (visited[node]) {
            continue;
        }

        visited[node] = true;

        for (let e of graph[node]) {
            if (dist[e.node] > dist[node] + e.weight) {
                dist[e.node] = dist[node] + e.weight;
                queue.offer(new Edge(e.node, dist[e.node]));
            }
        }
    }
}
