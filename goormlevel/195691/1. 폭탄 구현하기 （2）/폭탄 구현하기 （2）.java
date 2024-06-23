import java.io.*;
import java.util.*;
class Main {
	private static String[][] area;
	private static int[][] mapView;
	private static int[][] ds;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		area = new String[N][N];
		mapView = new int[N][N];
		ds = new int[][]{{0,0}, {-1,0}, {1,0}, {0,-1}, {0,1}};
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				area[r][c] = st.nextToken();
			}
		}
		
		while (K-- > 0) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			dropBomb(r, c, N);
		}
		
		int max = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				max = Math.max(max, mapView[r][c]);
			}
		}
		
		bw.write(String.valueOf(max));
		bw.flush();
		bw.close();
	}
	
	private static void dropBomb(int r, int c, int N) {
		
		for (int[] d : ds) {
			int nR = r + d[0];
			int nC = c + d[1];
			if (!(nR >= 0 && nR < N && nC >= 0 && nC < N)) {
				continue;
			}
			int cnt = 1;
			if (Objects.equals(area[nR][nC], "#")) {
				continue;
			}
			if (Objects.equals(area[nR][nC], "@")) {
				cnt = 2;
			}
			mapView[nR][nC] += cnt;
		}
	} 
}