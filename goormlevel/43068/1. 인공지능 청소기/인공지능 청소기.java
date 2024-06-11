import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			long X = Math.abs(Long.parseLong(st.nextToken()));
			long Y = Math.abs(Long.parseLong(st.nextToken()));
			long N = Math.abs(Long.parseLong(st.nextToken()));
		
			if (X +Y > N) {
				sb.append("NO");
				sb.append("\n");
				continue;
			}
			if ((X + Y) % 2 == 0 && N % 2 == 0) {
				sb.append("YES");
				sb.append("\n");
				continue;
			}
			
			if ((X + Y) % 2 != 0 && N % 2 != 0) {
				sb.append("YES");
				sb.append("\n");
				continue;
			}
			sb.append("NO");
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}