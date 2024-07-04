// Run by Node.js
const readline = require('readline');
let N;
let mapView;
let visited;

(async () => {
	let rl = readline.createInterface({ input: process.stdin });
	let x, y;
	let r = 1;
	
	for await (const line of rl) {
		if (N === undefined) {
			N = Number(line);
			mapView = Array.from({length : N + 1}, () => new Array(N + 1));
			visited = Array.from({length : N + 1}, () => new Array(N + 1).fill(false));
			continue;
		}
		if (x === undefined && y === undefined) {
			[x, y] = line.split(" ").map(Number);
			continue;
		}
		let row = line.split(" ");
		for (let c = 0; c < N; c++) {
			mapView[r][c + 1] = Number(row[c]);
		}
		r++;
		
		if (r > N) {
			rl.close();
		}
	}
	solve(x, y);
	process.exit();
})();

function solve(x, y) {
	let num = mapView[x][y];
	let max = 0;
	for (let r = 1; r <= N; r++) {
		for (let c = 1; c <= N; c++) {
			if (!visited[r][c] && mapView[r][c] === num) {
				max = Math.max(max, bfs(r,c,num));
			}
		}
	}
	console.log(max);
}

function bfs(r,c,num) {
	let cnt = 1;
	let ds = [[-1,0], [1,0], [0,-1], [0,1]];
	let queue = [];
	queue.push([r,c]);
	visited[r][c] = true;
	
	while (queue.length > 0) {
		let pos = queue.shift();
		let cR = pos[0];
		let cC = pos[1];
		
		for (let d of ds) {
			let nR = cR + d[0];
			let nC = cC + d[1];
			if (!(nR >= 1 && nR <= N && nC >= 1 && nC <= N)) {
				continue;
			}
			if (visited[nR][nC] || mapView[nR][nC] !== num) {
				continue;
			}
			visited[nR][nC] = true;
			cnt++;
			queue.push([nR, nC]);
		}
	}
	return cnt;
}
