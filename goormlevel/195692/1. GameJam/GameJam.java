import java.io.*;
import java.util.*;
class Main {
	private static int[][][] mapView;
	private static int[][] ds;
	private static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		mapView = new int[N][N][2]; // 방향, 카운트가 담겨 있음
		ds = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}}; // U, D, L, R
		int[] goorm = new int[2];
		int[] player = new int[2];
		st = new StringTokenizer(br.readLine());
		goorm[0] = Integer.parseInt(st.nextToken()) - 1;
		goorm[1] = Integer.parseInt(st.nextToken()) - 1;
		st = new StringTokenizer(br.readLine());
		player[0] = Integer.parseInt(st.nextToken()) - 1;
		player[1] = Integer.parseInt(st.nextToken()) - 1;
		
		Map<String, Integer> command = new HashMap<>();
		command.put("U", 0);
		command.put("D", 1);
		command.put("L", 2);
		command.put("R", 3);
	
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				String input = st.nextToken();
				int cnt = Integer.parseInt(input.substring(0, input.length() - 1));
				String cmd = String.valueOf(input.charAt(input.length() - 1));
				mapView[r][c] = new int[]{command.get(cmd), cnt};
			}
		}
		int gs = getScore(goorm[0], goorm[1], N);
		int ps = getScore(player[0], player[1], N);
		StringBuilder sb = new StringBuilder();
		if (gs > ps) {
			sb.append("goorm");
			sb.append(" ");
			sb.append(gs);
		} else {
			sb.append("player");
			sb.append(" ");
			sb.append(ps);
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static int getScore(int r, int c,int N) {
		visited = new boolean[N][N];
		Queue<int[]> queue = new ArrayDeque();
		queue.offer(new int[]{r, c, mapView[r][c][0], mapView[r][c][1]});
		visited[r][c] = true;
		int score = 1;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int cR = cur[0];
			int cC = cur[1];
			int d = cur[2];
			int cnt = cur[3];
			if (cnt == 0) {
				queue.offer(new int[]{cR, cC, mapView[cR][cC][0], mapView[cR][cC][1]});
				continue;
			}
			int nR = (cR + ds[d][0]) % N;
			int nC = (cC + ds[d][1]) % N;
			if (nR < 0) {
				nR += N;
			}
			if (nC < 0) {
				nC += N;
			}
			
			if (visited[nR][nC]) {
				continue;
			}
			score++;
			visited[nR][nC] = true;
			queue.offer(new int[]{nR, nC, d, cnt - 1});
		}
		return score;
	}
}