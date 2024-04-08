
import java.util.*;
import java.io.*;

public class Main {
	
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;
	private static List<Edge> graph;
	private static long[] dist;
	
	
	static class Edge {
		
		int start;
		int end;
		int weight;
		
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}
	
	
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		dist = new long[N + 1];
		graph = new ArrayList<>();
		
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.add(new Edge(start,end,weight));
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (!bellManFord(N, 1)) {
			
			for (int i = 2; i <= N; i++) {
				long d = dist[i];
				
				if (d == Integer.MAX_VALUE) {
					d = -1;
				}
				sb.append(d);
				sb.append("\n");
			
			}
		} else {
			sb.append(-1);
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	
	private static boolean bellManFord(int N, int s) {
		
		dist[s] = 0;
		
		for (int i = 1; i <= N; i++) {
			for (Edge edge : graph) {
				
				int start = edge.start;
				int end = edge.end;
				int weight = edge.weight;
				
				
				// 갈 수 없던 곳이면 패스
				if (dist[start] == Integer.MAX_VALUE) {
					continue;
				}
				
				if (dist[end] > dist[start] + weight) {
					dist[end] = dist[start] + weight;
					if (i == N) {
						return true;
					}
				}
			}
		}
		
		return false;
		
		
	}
}