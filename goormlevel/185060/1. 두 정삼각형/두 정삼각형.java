import java.io.*;
import java.util.*;
class Main {
	private static int[][] newTriangle;
	private static int[] combi;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] A = new int[N][N];
		int[][] B = new int[N][N];
		combi = new int[4];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				int num = Integer.parseInt(st.nextToken());
				A[i][j] = num;
			}
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <= i; j++) {
				int num = Integer.parseInt(st.nextToken());
				B[i][j] = num;
			}
		}
		int result = getResult(A, B, N);
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
	}
	
	private static int getResult(int[][] A, int[][] B, int N) {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 2; k++) {
					for (int l = 0; l < 3; l++) {
						int[][] copyA = getCopy(A);
						int[][] copyB = getCopy(B);
						if (i == 1) {
							symetry(N,copyA);
						}
						int tmpJ = j;
						while (tmpJ-- > 0) {
							newTriangle = new int[N][N];
							turn(0, N - 1, 0, N - 1, copyA);
							copyA = newTriangle;
						}
						
						if (k == 1) {
							symetry(N,copyB);
						}
						int tmpL = l;
						while (tmpL-- > 0) {
							newTriangle = new int[N][N];
							turn(0, N - 1, 0, N - 1, copyB);
							copyB = newTriangle;
						}
						min = Math.min(min, getDiff(copyA, copyB, N));
					}
				}
			}
		}
		return min;
	}
	
	private static int getDiff(int[][] arr1, int[][] arr2, int N) {
		int diff = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (arr1[r][c] != arr2[r][c]) {
					diff++;
				}
			}
		}
		return diff;
	}
	
	private static int[][] getCopy(int[][] arr) {
		int[][] copy = new int[arr.length][arr[0].length];
		for (int i = 0; i < arr.length; i++) {
			copy[i] = Arrays.copyOf(arr[i], arr[i].length);
		}
		return copy;
	}
	
	private static void symetry(int N, int[][] triangle) {
		for (int i = 0; i < N; i++) {
			int r = i;
			for (int l = 0; l <= i / 2; l++) {
				int tmp = triangle[i][l];
				triangle[i][l] = triangle[i][r];
				triangle[i][r] = tmp;
				r--;
			}
		}
	}
	
	private static void turn(int t, int b, int s, int e, int[][] triangle) {
		
		if (t >= b) {
			if (t == b) {
				newTriangle[t][s] = triangle[t][s];
			}
			return;
		}
		
		int tmpS = s;
		int tmpT = t;
			// 왼쪽면 -> 아랫면
		for (int i = t; i <= b; i++) {
			newTriangle[b][tmpS++] = triangle[tmpT++][s];
		}
	
		int tmpE = e;
		tmpS = s;
		// 아랫면 -> 오른쪽면
		for (int i = t; i <= b; i++) {
			newTriangle[i][tmpS++] = triangle[b][tmpE--];
		}
		
		int tmpB = b;
		tmpE = e;
		// 오른쪽면 -> 왼쪽면
		for (int i = t; i <= b; i++) {
			newTriangle[i][s] = triangle[tmpB--][tmpE--];
		}
		
		turn(t + 2, b - 1, s + 1, e - 2, triangle);
		
	}
}
// Turn
// top은 2씩 증가, bottom은 1씩 감소
// top >= bottom 재귀 종료
// 왼쪽면 -> 아랫면, 아랫면 -> 오른쪽면, 오른쪽면 -> 왼쪽면