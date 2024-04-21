const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./example.txt";
const inputs = fs.readFileSync(filePath).toString().trim().split("\n");
const N = Number(inputs.shift());
const M = Number(inputs.shift());
let dists = Array.from({length: N + 1}, () => new Array(N + 1).fill(Number.MAX_SAFE_INTEGER));


main();

function main() {

    for (let i = 1; i <= N; i++) {
        dists[i][i] = 0;
    }

    for (let input of inputs) {
        let [start, end, weight] = input.split(" ").map(Number);
        dists[start][end] = Math.min(dists[start][end], weight);
    }

    floyd();

    console.log(getAnswer());

}

function getAnswer() {
    let answer = [];


    for (let i = 1; i <= N; i++) {
        let result = [];
        for (let j = 1; j <= N; j++) {
            let dist = dists[i][j];
            if (dist === Number.MAX_SAFE_INTEGER) {
                dist = 0;
            }
            result.push(dist);
        }
        answer.push(result.join(" "));
    }

    return answer.join("\n");

}

function floyd() {

    for (let i = 1; i <= N; i++) {
        for (let start = 1; start <= N; start++) {
            for (let end = 1; end <= N; end++) {


                if (dists[start][i] === Number.MAX_SAFE_INTEGER || dists[i][end] === Number.MAX_SAFE_INTEGER) {
                    continue;
                }

                if (dists[start][end] > dists[start][i] + dists[i][end]) {
                    dists[start][end] = dists[start][i] + dists[i][end];
                }
            }
        }
    }
}

