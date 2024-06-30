// Run by Node.js

let mapView;
let visited1;
let visited2;
let ds;

function solution(R,C,K,input) {
	mapView = Array.from({length : R + 1}, () => new Array(C + 1));
	visited = Array.from({length : R + 1}, () => Array.from({length : C + 1} , () => new Array(K + 1).fill(false)));
	ds = [[-1,0], [1, 0], [0, -1], [0, 1]];
	
	for (let r = 0; r < R; r++) {
		for (let c = 0; c < C; c++) {
			mapView[r + 1][c + 1] = Number(input[r][c]);
		}
	}
	console.log(getAnswer(R, C, K));
	
}

function getAnswer(R, C, K) {
	let queue = [];
	queue.push([1,1,0,K]); // r,c,t,k
	visited[1][1][K] = true;
	
	while (queue.length !== 0) {
		let cur = queue.shift();
		let cR = cur[0];
		let cC = cur[1];
		let cT = cur[2];
		let cK = cur[3];
		
		if (cR === R && cC === C) {
			return cT;
		}
		
		for (let d of ds) {
			let nR = cR + d[0];
			let nC = cC + d[1];
			if (!(nR >= 1 && nR <= R && nC >= 1 && nC <= C)) {
				continue;
			}
			if (visited[nR][nC][cK]) {
				continue;
			}

			if (mapView[nR][nC] === 0) {
					queue.push([nR, nC, cT + 1, cK]);
					visited[nR][nC][cK] = true;
			}
			
			if (mapView[nR][nC] === 1 && cK >= 10) {
				nR += d[0];
				nC += d[1];
			if (!(nR >= 1 && nR <= R && nC >= 1 && nC <= C)) {
				continue;
			}
			if (mapView[nR][nC] === 0) {
					queue.push([nR , nC , cT + 1, cK - 10]);
					visited[nR][nC][cK - 10] = true;
				}
			}
		}
	}
	return -1;
}
const readline = require('readline');

(async () => {
	let rl = readline.createInterface({ input: process.stdin });
	let input = [];
	let R, C, K;
	for await (const line of rl) {
		if (R === undefined && C === undefined && K === undefined) {
			[R, C, K] = line.split(" ").map(Number);
		} else {
			input.push(line);
		}
		if (input.length === R) {
			rl.close();
		}
	}
	solution(R,C,K,input);
	process.exit();
})();
