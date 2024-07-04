const fs = require("fs");
const filePath = process.platform === "linux" ? "/dev/stdin" : "./example.txt";
const inputs = fs.readFileSync(filePath).toString().trim().split("\n");
let N;
let mapView;
let ds;

function main() {
    N = Number(inputs.shift());
    mapView = Array.from({length : 7}, () => new Array(7).fill("."));
    mapView[3][3] = "W";
    mapView[4][4] = "W";
    mapView[3][4] = "B";
    mapView[4][3] = "B";
    ds = [[-1,0], [1,0], [0,-1], [0,1], [-1,-1], [-1,1], [1,-1],[1,1]]; // 상,하,좌,우,10시,2시,7시,4시

    for (let i = 0; i < N; i++) {
        let [R, C] = inputs.shift().split(" ").map(Number);
        if (i % 2 === 0) {
            mapView[R][C] = "B";
            yummy(R,C,"B");
        }

        if (i % 2 === 1) {
            mapView[R][C] = "W";
            yummy(R,C,"W");
        }
    }
    let white = 0;
    let black = 0;
    for (let r = 1; r <= 6; r++) {
        for (let c = 1; c <= 6; c++) {
            if (mapView[r][c] === "W") {
                white++;
            }
            if (mapView[r][c] === "B") {
                black++;
            }
        }
    }
    let result = mapView.slice(1).map(map => map.slice(1).join("")).join("\n");
    if (white < black) {
        result += "\n";
        result += "Black";
    } else {
        result += "\n";
        result += "White";
    }

    console.log(result);
}

function yummy(r,c,type) {
    for (let d of ds) {
        let nR = r + d[0];
        let nC = c + d[1];
        if (!isIn(nR, nC)) {
            continue;
        }
        let pos = [];
        while (isIn(nR, nC) && mapView[nR][nC] !== type && mapView[nR][nC] !== "." ) {
            pos.push([nR, nC]);
            nR += d[0];
            nC += d[1];
        }
        if (isIn(nR, nC) && mapView[nR][nC] === type) {
            for (let i = 0; i < pos.length; i++) {
                mapView[pos[i][0]][pos[i][1]] = type;
            }
        }
    }
}

function isIn(r, c) {
    return (r >= 1 && r <= 6 && c >= 1 && c <= 6);
}

main();

//흑돌이 선을 잡음
//비기는 경우 없음
//돌을 둘수 없는 경우 없음
// 시간 복잡도 36 * 36 * 32
