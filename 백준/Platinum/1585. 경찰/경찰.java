import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;
	static List<List<Edge>> adj;
	static Edge[] path;
	static int[] prev, dist;
	static boolean[] visit;
	static int totalFlow;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());

		int source = 0;
		int sink = 2 * N + 1;

		adj = new ArrayList<>();
		for(int i = 0; i <= sink; i++)
			adj.add(new ArrayList<>());

		int[] inTime = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			inTime[i] = Integer.parseInt(st.nextToken());
			addAdj(source, i, 1, 0);
		}

		int[] outTime = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			outTime[i] = Integer.parseInt(st.nextToken());
			addAdj(N + i, sink, 1, 0);
		}

		int T = Integer.parseInt(br.readLine());
		int F = Integer.parseInt(br.readLine());

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
                int onRoad = outTime[j] - inTime[i];
				if(onRoad > 0)
					addAdj(i, N + j, 1, onRoad < T ? Math.min(F, (T - onRoad) * (T - onRoad)) : 0);
			}
		}

		path = new Edge[sink + 1];
		prev = new int[sink + 1];
		dist = new int[sink + 1];
		visit = new boolean[sink + 1];
		int min = minCost(source, sink);
		if(totalFlow != N) {
			System.out.print("-1");
			return;
		}

		for(int i = 0; i <= sink; i++) {
			for(Edge edge : adj.get(i))
				edge.flow = 0;
		}
		sb.append(min + " " + maxCost(source, sink) + "\n");
		System.out.print(sb);
	}
	
	static void addAdj(int from, int to, int capacity, int cost) {
		Edge forward = new Edge(to, capacity, cost);
		Edge backward = new Edge(from, 0, -cost);

		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	static int minCost(int source, int sink) {
		totalFlow = 0;
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
			totalFlow += flow;
		}
		return cost;
	}

	static boolean SPFA(int source, int sink) {
		Arrays.fill(dist, INF);
        Queue<Integer> queue = new ArrayDeque<>();
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
	
	private static int maxCost(int source, int sink) {
		int cost = 0;
		while(LPFA(source, sink)) {
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
	
	static boolean LPFA(int source, int sink) {
		Arrays.fill(dist, MIN);
        Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(source);
		dist[source] = 0;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			visit[now] = false;
			for(Edge edge : adj.get(now)) {
				if(edge.capacity > edge.flow && dist[now] + edge.cost > dist[edge.to]) {
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
		if(dist[sink] == MIN)
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