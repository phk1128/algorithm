const readline = require('readline');
let rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout,
});

let input = [];

rl.on('line', (line) => {
	input.push(line);
	
	if (input.length === 2) {
		rl.close();
	}
});

rl.on('close', () => {
	solve(input);
})

function solve(input) {
	let N = Number(input[0]);
	let str = input[1];
	let set = new Set();
	let result = [];
	
	for (let s = 1; s <= str.length - 2; s++) {
		for (let e = str.length - 1; e > s; e--) {
			let arr = [];
			arr.push(str.slice(0, s));
			arr.push(str.slice(s, e));
			arr.push(str.slice(e, str.length));
			result.push(arr);
		}
	}
	
	for (let i = 0; i < result.length; i++) {
		for (let j = 0; j < 3; j++) {
			set.add(result[i][j]);
		}
	}
	let arr = [];
	for (let s of set) {
		arr.push(s);
	}
	
	arr.sort((a1, a2) => a1.localeCompare(a2));
	let score = {};
	for (let i = 0; i < arr.length; i++) {
		score[arr[i]] = i + 1;
	}
	
	let max = 0;
	for (let i = 0; i < result.length; i++) {
		let tmp = 0;
		for (let j = 0; j < 3; j++) {
			tmp += score[result[i][j]];
		}
		max = Math.max(max, tmp);
	}
	
	console.log(max);
	
}