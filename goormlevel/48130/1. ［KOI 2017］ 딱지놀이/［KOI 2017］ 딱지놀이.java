import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		while (N-- > 0) {
			int[] A = new int[5];
			int[] B = new int[5];
			st = new StringTokenizer(br.readLine());
			String[] aArr = st.nextToken("").split(" ");
			addScore(aArr, A);
			st = new StringTokenizer(br.readLine());
			String[] bArr = st.nextToken("").split(" ");
			addScore(bArr, B);
			
			if (A[4] > B[4]) {
				sb.append("A");
				sb.append("\n");
				continue;
			}
			if (A[4] < B[4]) {
				sb.append("B");
				sb.append("\n");
				continue;
			}
			if (A[3] > B[3]) {
				sb.append("A");
				sb.append("\n");
				continue;
			}
			if (A[3] < B[3]) {
				sb.append("B");
				sb.append("\n");
				continue;
			}
			if (A[2] > B[2]) {
				sb.append("A");
				sb.append("\n");
				continue;
			}
			if (A[2] < B[2]) {
				sb.append("B");
				sb.append("\n");
				continue;
			}
			if (A[1] > B[1]) {
				sb.append("A");
				sb.append("\n");
				continue;
			}
			if (A[1] < B[1]) {
				sb.append("B");
				sb.append("\n");
				continue;
			}
			sb.append("D");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static void addScore(String[] arr, int[] scores) {
		int c = Integer.parseInt(arr[0]);
		for (int i = 1; i <= c; i++) {
			scores[Integer.parseInt(arr[i])]++;
		}
	}
}
// 별, 동그라미, 네모, 세모 => 4, 3, 2, 1
// 아래의 순서대로 이기는것
// 별의 개수
// 동그라미
// 네모
// 세모
// 모두 같다면 무승부