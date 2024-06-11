import java.io.*;
import java.util.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] height = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int h = Integer.parseInt(st.nextToken());
			height[i] = h;
		}
		Set<Integer> set = new HashSet<>();
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			for (int j = s; j <= e; j++) {
				set.add(j);
				height[j - 1]++;
			}
			if (i % 3 == 0) {
				for (int k : set) {
					height[k - 1]--;
				}
				set.clear();
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int h : height) {
			sb.append(h);
			sb.append(" ");
		}
		System.out.println(sb.toString());
	}
}
// 배수는 장마가 시작된 지 3의 배수가 되는 날마다, 비가 내리고 난 뒤 작동
// 배수는 작동 날짜를 기준으로 2일 이내 비가 내린 위치에서만 작동