import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int total = 0;
		int[][] mapView = new int[N][N];
		int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};
		for(int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				int num = Integer.parseInt(st.nextToken());
				mapView[r][c] = num;
				total += num;
			}
		}
		
		int day = 0;
		while (total > 0) {
			int[][] tmpMapView = new int[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (mapView[r][c] == 0) {
						continue;
					}
					int num = mapView[r][c];
					for (int[] direction : directions) {
						int newR = r + direction[0];
						int newC = c + direction[1];
						if (!(newR >= 0 && newR < N && newC >= 0 && newC < N)) {
							continue;
						}
						if (num > 0 && mapView[newR][newC] == 0) {
							total--;
							num--;
						}
					}
					tmpMapView[r][c] = num;
				}
			}
			day++;
			mapView = tmpMapView;
		}
		System.out.println(day);
	}
}
// 0은 단풍나무가 물든곳
// 상하좌우로 인접한 구역 중 그날 아침 기준으로 단풍나무가 모두 물들어 있는 구역의 수만큼 줄어든다.
// 남은 단풍나무가 0보다 작거나 갔다면 패스
// 모든 단풍나무가 물들기 까지 걸리는 기간 출력

// 물들지 않은 단풍나무의 숫자를 세어 놓는다.
// 물들지 않은 단풍나무가 0이 되면 종료