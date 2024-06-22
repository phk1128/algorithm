import java.io.*;
import java.util.*;
class Main {
	
	private static int[][] mapView;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		mapView = new int[N + 1][N + 1];
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				mapView[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken());
			int c1 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int r3 = Integer.parseInt(st.nextToken());
			int c3 = Integer.parseInt(st.nextToken());
			int minR = Math.min(r1, r2);
			minR = Math.min(minR, r3);
			int minC = Math.min(c1, c2);
			minC = Math.min(minC, c3);
			int maxR = Math.max(r1, r2);
			maxR = Math.max(maxR, r3);
			int maxC = Math.max(c1, c2);
			maxC = Math.max(maxC, c3);
			if ((r1 == minR && r2 == minR) || (r1 == minR && r3 == minR) || (r2 == minR && r3 == minR)) {
				if ((c1 == minC && c2 == minC) || (c1 == minC && c3 == minC) || (c2 == minC && c3 == minC)) {
					sb.append(getReverseTriangle2(minR, minC, maxR, maxC));
					sb.append("\n");
				} else {
					sb.append(getReverseTriangle1(minR, minC, maxR, maxC));
					sb.append("\n");
				}

			}
			if ((r1 == maxR && r2 == maxR) || (r1 == maxR && r3 == maxR) || (r2 == maxR && r3 == maxR)) {
				if ((c1 == minC && c2 == minC) || (c1 == minC && c3 == minC) || (c2 == minC && c3 == minC)) {
					sb.append(getTriangle1(minR, minC, maxR, maxC));
					sb.append("\n");
				} else {
					sb.append(getTriangle2(minR, minC, maxR, maxC));
					sb.append("\n");
				}
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static int getTriangle1(int minR, int minC, int maxR, int maxC) {
		int triangle = 0;
		maxC = minC;
		for (int r = minR; r <= maxR; r++) {
			for (int c = minC; c <= maxC; c++) {
				triangle += mapView[r][c];
			}
			maxC++;
		}
		return triangle;
	}
	
	private static int getTriangle2(int minR, int minC, int maxR, int maxC) {
		int triangle = 0;
		minC = maxC;
		for (int r = minR; r <= maxR; r++) {
			for (int c = minC; c <= maxC; c++) {
				triangle += mapView[r][c];
			}
			minC--;
		}
		return triangle;
	}
	
	private static int getReverseTriangle1(int minR, int minC, int maxR, int maxC) {
		int reverseTriangle = 0;
		for (int r = minR; r <= maxR; r++) {
			for (int c = minC; c <= maxC; c++) {
				reverseTriangle += mapView[r][c];
			}
			minC++;
		}
		return reverseTriangle;
	}
	
	private static int getReverseTriangle2(int minR, int minC, int maxR, int maxC) {
		int reverseTriangle = 0;
		for (int r = minR; r <= maxR; r++) {
			for (int c = minC; c <= maxC; c++) {
				reverseTriangle += mapView[r][c];
			}
			maxC--;
		}
		return reverseTriangle;
	}
}