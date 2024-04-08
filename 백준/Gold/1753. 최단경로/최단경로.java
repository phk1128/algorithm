import java.util.*;
import java.io.*;

public class Main {
	
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;
	private static List<Edge>[] graph;
	private static boolean[] visited;
	private static int[] cost;
	
	
	static class Edge implements Comparable<Edge> {
		
		int node;
		int weight;
		
		public Edge(int node, int weight) {
			this.node = node;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			
			return this.weight - e.weight;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[V + 1];
		cost = new int[V + 1];
		visited = new boolean[V+1];
		
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>(); 
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[start].add(new Edge(end, weight));
		}
		
		djikstra(K);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= V; i++) {
			int c = cost[i];
			
			if (c == Integer.MAX_VALUE) {
				sb.append("INF");
				sb.append("\n");
			} else {
				sb.append(c);
				sb.append("\n");
			}
			
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		
	}
	
	private static void djikstra(int K) {
	
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		queue.offer(new Edge(K, 0));
		cost[K] = 0;
		
		while (!queue.isEmpty()) {
			
			Edge edge = queue.poll();
			int node = edge.node;
			
			if (visited[node]) {
				
				continue;
			}
			
			visited[node] = true;
			
			for (Edge e : graph[node]) {
				if (cost[e.node] > cost[node] + e.weight) {
					cost[e.node] = cost[node] + e.weight;
					queue.offer(new Edge(e.node, cost[e.node]));
				}
			}
		}
		
	}
}