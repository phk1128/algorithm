const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./example.txt";
let input = fs.readFileSync(filePath).toString().trim().split("\n");

const N = Number(input.shift());
const list = input.map((str) => str.split(" ").map(Number));
const mapView = Array.from({length: N}, () => new Array(N).fill(0));
const students = Array.from({length: (N * N) + 1}, () => []);
const directions = [[-1, 0], [1, 0], [0, -1], [0, 1]];
const score = [0, 1, 10, 100, 1000];
let seats;


class Seat {

    constructor(r, c, favorite, empty) {
        this.r = r;
        this.c = c;
        this.favorite = favorite;
        this.empty = empty;
    }
}

main();


function main() {

    for (let l of list) {
        let num = l.shift();
        for (let i = 0; i < 4; i++) {
            students[num].push(l.shift());
        }
        solve(num);
    }
    console.log(getAnswer());
}

function getAnswer() {
    let answer = 0;
    for (let r = 0; r < N; r++) {
        for (let c = 0; c < N; c++) {
            let num = mapView[r][c];
            let favorite = 0;

            for (let direction of directions) {
                let newR = r + direction[0];
                let newC = c + direction[1];

                if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
                    continue;
                }

                if (students[num].includes(mapView[newR][newC])) {
                    favorite++;
                }
            }

            answer += score[favorite];
        }
    }
    return answer;
}


function solve(num) {

    seats = [];

    for (let r = 0; r < N; r++) {
        for (let c = 0; c < N; c++) {
            if (mapView[r][c] === 0) {
                addSeat(r, c, num);
            }

        }
    }

    seats.sort(compareTo);
    mapView[seats[0].r][seats[0].c] = num;

}


function addSeat(r, c, num) {

    let favorite = 0;
    let empty = 0;

    for (let direction of directions) {
        let newR = r + direction[0];
        let newC = c + direction[1];

        if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
            continue;
        }

        if (mapView[newR][newC] === 0) {
            empty++;
        }

        if (students[num].includes(mapView[newR][newC])) {
            favorite++;
        }
    }
    seats.push(new Seat(r, c, favorite, empty));
}

function compareTo(s1, s2) {
    if (s1.favorite !== s2.favorite) {
        return s2.favorite - s1.favorite;
    }

    if (s1.empty !== s2.empty) {
        return s2.empty - s1.empty;
    }

    if (s1.r !== s2.r) {
        return s1.r - s2.r;
    }

    if (s1.c !== s2.c) {
        return s1.c - s2.c;
    }
}
