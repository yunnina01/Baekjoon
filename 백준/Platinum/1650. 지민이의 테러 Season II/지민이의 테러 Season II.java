import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static List<List<Edge>> adj;
	static Edge[] path;
	static int[] prev, dist;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int source = 0;
		int sink = N + 1;

		adj = new ArrayList<>();
		for(int i = source; i <= sink; i++)
			adj.add(new ArrayList<>());
		addAdj(source, 1, 2, 0);

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			addAdj(P, Q, 1, L);
			addAdj(Q, P, 1, L);
		}
		addAdj(N, sink, 2, 0);

		path = new Edge[sink + 1];
		prev = new int[sink + 1];
		dist = new int[sink + 1];
		visit = new boolean[sink + 1];
		System.out.print(MCMF(source, sink));
	}
	
	static void addAdj(int from, int to, int capacity, int cost) {
		Edge forward = new Edge(to, capacity, cost);
		Edge backward = new Edge(from, 0, -cost);

		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	static int MCMF(int source, int sink) {
		int cost = 0;
		while(SPFA(source, sink)) {
			int flow = INF;
			for(int i = sink; i != source; i = prev[i])
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			for(int i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				cost += flow * path[i].cost;
			}
		}
		return cost;
	}
	
	static boolean SPFA(int source, int sink) {
		Queue<Integer> queue = new ArrayDeque<>();
		Arrays.fill(dist, INF);
		queue.offer(source);
		dist[source] = 0;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			visit[now] = false;
			for(Edge edge : adj.get(now)) {
				if(edge.capacity > edge.flow && dist[now] + edge.cost < dist[edge.to]) {
					path[edge.to] = edge;
					prev[edge.to] = now;
					dist[edge.to] = dist[now] + edge.cost;
					if(!visit[edge.to]) {
						queue.offer(edge.to);
						visit[edge.to] = true;
					}
				}
			}
		}
		if(dist[sink] == INF)
			return false;
		return true;
	}
}
	
class Edge {
    int to, capacity, flow, cost;
    Edge reverse;
    
    Edge(int to, int capacity, int cost) {
        this.to = to;
        this.capacity = capacity;
        this.cost = cost;
    }
}