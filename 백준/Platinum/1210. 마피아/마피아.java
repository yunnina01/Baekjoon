import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static List<List<Edge>> adj;
	static Edge[] path;
	static int[] prev;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int source = 0;
		int sink = 2 * n + 1;

		adj = new ArrayList<>(sink + 1);
		for(int i = 0; i <= sink; i++)
			adj.add(new ArrayList<>());

		st = new StringTokenizer(br.readLine());
		addAdj(source, Integer.parseInt(st.nextToken()), INF);
		addAdj(n + Integer.parseInt(st.nextToken()), sink, INF);
		for(int i = 1; i <= n; i++) {
			int cost = Integer.parseInt(br.readLine());
			addAdj(i, n + i, cost);
		}

		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			addAdj(n + u, v, INF);
			addAdj(n + v, u, INF);
		}

		path = new Edge[sink + 1];
		prev = new int[sink + 1];
		maxFlow(source, sink);

		visit = new boolean[sink + 1];
		DFS(source);
		for(int i = 1; i <= n; i++) {
			if(visit[i] && !visit[n + i])
				sb.append(i + " ");
		}
		System.out.print(sb);
	}
	
	static void addAdj(int from, int to, int capacity) {
		Edge forward = new Edge(to, capacity);
		Edge backward = new Edge(from, 0);

		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	static boolean BFS(int source, int sink) {
		Queue<Integer> queue = new ArrayDeque<>();
		Arrays.fill(prev, -1);
		queue.offer(source);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(Edge edge : adj.get(now)) {
				if(prev[edge.to] == -1 && edge.capacity > edge.flow) {
					path[edge.to] = edge;
					prev[edge.to] = now;
					if(edge.to == sink)
						return true;
					queue.offer(edge.to);
				}
			}
		}
		return false;
	}
	
	static int maxFlow(int source, int sink) {
		int total = 0;
		while(BFS(source, sink)) {
			int flow = INF;
			for(int i = sink; i != source; i = prev[i])
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			for(int i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
			}
			total += flow;
		}
		return total;
	}
	
	static void DFS(int node) {
		if(visit[node])
			return;

		visit[node] = true;
		for(Edge edge : adj.get(node)) {
			if(edge.capacity > edge.flow)
				DFS(edge.to);
		}
	}
}
	
class Edge {
    int to, capacity, flow;
    Edge reverse;

    Edge(int to, int capacity) {
        this.to = to;
        this.capacity = capacity;
    }
}