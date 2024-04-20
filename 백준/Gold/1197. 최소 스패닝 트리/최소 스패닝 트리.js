const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./example.txt";
const inputs = fs.readFileSync(filePath).toString().trim().split("\n");

const [V, E] = inputs.shift().split(" ").map(Number);
let graph = Array.from({length : V + 1}, () => new Array());
const visited = new Array(V + 1).fill(false);


class Edge {

    constructor(node, weight) {

        this.node = node;
        this.weight = weight;
    }
}

class PriorityQueue {
    constructor() {
        this.heap = [];
    }

    compare(e1, e2) {
        return e1.weight - e2.weight;
    }

    swap(a, b) {
        [this.heap[a], this.heap[b]] = [this.heap[b], this.heap[a]];
    }

    offer(edge) {
        this.heap.push(edge);
        this.bubbleUp();
    }

    poll() {
        if (this.isEmpty()) {
            return 0;
        }

        if (this.heap.length === 1) {
            return this.heap.pop();
        }

        let minValue = this.heap[0];
        this.heap[0] = this.heap.pop();
        this.bubbleDown();
        return minValue;
    }

    bubbleUp() {
        let index = this.heap.length - 1;

        while (index > 0) {
            let parentIdx = Math.floor((index - 1) / 2);

            if (this.compare(this.heap[parentIdx] , this.heap[index]) > 0) {
                this.swap(index, parentIdx);
                index = parentIdx;
            } else {
                break;
            }
        }
    }

    bubbleDown() {
        let index = 0;

        while (true) {
            let leftIdx = index * 2 + 1;
            let rightIdx = index * 2 + 2;
            let smallerIdx = index;

            if (
                leftIdx < this.heap.length &&
                this.compare(this.heap[leftIdx], this.heap[smallerIdx]) < 0
            ) {
                smallerIdx = leftIdx;
            }

            if (
                rightIdx < this.heap.length &&
                this.compare(this.heap[rightIdx], this.heap[smallerIdx]) < 0
            ) {
                smallerIdx = rightIdx;
            }

            if (smallerIdx !== index) {
                this.swap(index, smallerIdx);

                index = smallerIdx;
            } else {
                break;
            }
        }
    }

    isEmpty() {
        return this.heap.length === 0;
    }
}

for (let input of inputs) {
    let [start, end, weight] = input.split(" ").map(Number);
    graph[start].push(new Edge(end, weight));
    graph[end].push(new Edge(start, weight));
}

main();

function main() {
    console.log(prim());
}

function prim() {

    let answer = 0;
    let queue = new PriorityQueue();
    queue.offer(new Edge(1,0));

    while (!queue.isEmpty()) {

        let edge = queue.poll();
        let node = edge.node;

        if (visited[node]) {
            continue;
        }
        visited[node] = true;
        answer += edge.weight;

        for (let e of graph[node]) {
            if (!visited[e.node]) {
                queue.offer(new Edge(e.node, e.weight));
            }
        }

    }

    return answer;

}
