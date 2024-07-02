let N;
let M;
let graph;
let visited1;
let visited2;
let island;

function solution() {
	count = N;
	island = new Array(N + 1);
	
	for (let i = 1; i <= N; i++) {
		island[i] = i;
		bfs(i);
	}
	
	for (let s = 1; s <= N; s++) {
		for (let e = 1; e <= N; e++) {
			if (visited[s][e] && visited[e][s] && union(s, e)) {
				visited[s][e] = false;
				visited[e][s] = false;
				count--;
			}
		}
	}
	console.log(count);
}

function union(x, y) {
	x = find(x);
	y = find(y);
	
	if (x > y) {
		island[x] = y;
		return true;
	}
	if (x < y) {
		island[y] = x;
		return true;
	}
	return false;
}

function find(x) {
	if (x === island[x]) {
		return x;
	}
	return find(island[x]);
}

function bfs (start) {
	if (visited2[start]) {
		return;
	}
	let queue = [];
	queue.push(start);
	
	while (queue.length > 0) {
		let s = queue.shift();
		for (let e of graph[s]) {
		if (visited[s][e]) {
			continue;
		}
			visited[s][e] = true;
			queue.push(e);
		}
	}
	
}

const readline = require('readline');
let rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout,
});
let input;
let cnt = 0;

rl.on('line', (line) => {
	input = line;
	if (N === undefined && M === undefined) {
		[N, M] = input.split(" ").map(Number);
		graph = Array.from({length : N + 1}, () => []);
		visited = Array.from({length : N + 1}, () => new Array(N + 1).fill(false));
	} else {
		[s, e] = input.split(" ").map(Number);
		graph[s].push(e);
		cnt++;
	}
	if (cnt === M) {
			rl.close();
	}
});
rl.on('close', () => {
	solution();
	process.exit();
})