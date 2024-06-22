import java.io.*;
import java.util.*;
class Main {
	private static int bound;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[] A = new int[N];
		int[] B = new int[N];
		bound = 0;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		Arrays.sort(B);
		
		int countA = 0;
		int countB = 0;
		int minA = 0;
		int minB = 0;
		for (int i = 1; i <= 100000; i++) {
			leftSearch(0, N - 1, i - 2, A);
			int leftA = bound;
			rightSearch(0, N - 1, i + 2, A);
			int rightA = bound;
			int numA = (rightA - leftA) + 1;
			if (A[leftA] >= i - 2) {
				numA++;
			}
			if (A[rightA] <= i + 2) {
				numA++;
			}
			if (countA < numA) {
				countA = numA;
				minA = i;
			}
			
			leftSearch(0, N - 1, i - 2, B);
			int leftB = bound;
			rightSearch(0, N - 1, i + 2, B);
			int rightB = bound;
			int numB = (rightB - leftB) + 1;
			if (B[leftB] >= i - 2) {
				numB++;
			}
			if (B[rightB] <= i + 2) {
				numB++;
			}
			if (countB < numB) {
				countB = numB;
				minB = i;
			}
		}
		StringBuilder sb = new StringBuilder();
		sb.append(minA);
		sb.append(" ");
		sb.append(minB);
		sb.append("\n");
		if (minA > minB) {
			sb.append("good");
		} else {
			sb.append("bad");
		}
		
		System.out.println(sb);
	}
	
	private static void leftSearch(int start, int end, int target, int[] arr) {
		if (start >= end) {
			bound = start;
			return;
		}
		
		int mid = (start + end) / 2;
		
		if (target > arr[mid]) {
			leftSearch(mid + 1, end, target, arr);
		}
		
		if (target <= arr[mid]) {
			leftSearch(start, mid, target, arr);
		}
		
	}
	
		private static void rightSearch(int start, int end, int target, int[] arr) {
		if (start >= end) {
			bound = start;
			return;
		}
		
		int mid = (start + end) / 2;
		
		if (target >= arr[mid]) {
			rightSearch(mid + 1, end, target, arr);
		}
		
		if (target < arr[mid]) {
			rightSearch(start, mid, target, arr);
		}
		
	}
}