import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		int[][] minDp = new int[N + 1][3];
		int[][] maxDp = new int[N + 1][3];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int c3 = Integer.parseInt(st.nextToken());

			minDp[r][0] = Math.min(minDp[r - 1][0], minDp[r - 1][1]) + c1;
			minDp[r][1] = Math.min(minDp[r - 1][0], Math.min(minDp[r - 1][1], minDp[r - 1][2])) + c2;
			minDp[r][2] = Math.min(minDp[r - 1][1], minDp[r - 1][2]) + c3;

			maxDp[r][0] = Math.max(maxDp[r - 1][0], maxDp[r - 1][1]) + c1;
			maxDp[r][1] = Math.max(maxDp[r - 1][0], Math.max(maxDp[r - 1][1], maxDp[r - 1][2])) + c2;
			maxDp[r][2] = Math.max(maxDp[r - 1][1], maxDp[r - 1][2]) + c3;

		}

		int min = Integer.MAX_VALUE;
		int max = 0;

		for (int c = 0; c < 3; c++) {
			min = Math.min(min, minDp[N][c]);
			max = Math.max(max, maxDp[N][c]);
		}

		StringBuilder sb = new StringBuilder();
		sb.append(max);
		sb.append(" ");
		sb.append(min);

		bw.flush();
		bw.write(sb.toString());
		bw.close();

	}
}