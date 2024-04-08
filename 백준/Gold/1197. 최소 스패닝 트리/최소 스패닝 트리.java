import java.io.*;
import java.util.*;

public class Main {
	
	private static BufferedReader br;
	private static BufferedWriter bw;
	private static StringTokenizer st;
	private static List<Edge>[] graph;
	private static boolean[] visited;
	private static long answer;
	
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
		int N = Integer.parseInt(st.nextToken()); 
		int M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		answer = 0L;
		
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		
		for (int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[start].add(new Edge(end, weight));
			graph[end].add(new Edge(start, weight));
		}
		
		prim();
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		
	}
	
	private static void prim() {
		PriorityQueue<Edge> queue = new PriorityQueue<>();
		queue.offer(new Edge(1,0));
		
		while (!queue.isEmpty()) {
			
			Edge edge = queue.poll();
			int node = edge.node;
			
			if (visited[node]) {
				continue;
			}
			answer += edge.weight;
			
			visited[node] = true;
			
			for (Edge e : graph[node]) {
				if (visited[e.node]) {
					continue;
				}
				
				queue.offer(new Edge(e.node, e.weight));
			}
		}
		
	}

}
