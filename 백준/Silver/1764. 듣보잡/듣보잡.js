const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./example.txt";
const inputs = fs.readFileSync(filePath).toString().trim().split("\n");
const [N, M] = inputs.shift().split(" ").map(Number);


main();

function main() {

    let answer = [];
    let unknown = new Map();
    let idx = 0;
    for (let n = 0; n < N; n++) {
        let people = inputs[idx++];
        unknown.set(people, 1);
    }

    for (let m = 0; m < M; m++) {
        let people = inputs[idx++];
        if (unknown.has(people)) {
            answer.push(people);
        }
    }
    answer.sort();
    console.log(answer.length);
    console.log(answer.join("\n"));
}