import java.io.*;
import java.util.*;
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		int max = 0;
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			max = Math.max(Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken()), max);
		}
		System.out.println(max);
	}
}