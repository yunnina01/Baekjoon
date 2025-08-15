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
		
		int N = Integer.parseInt(br.readLine());

		int source = 0;
		int sink = 2 * N + 1;

		int[][] time = new int[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++)
				time[i][j] = Integer.parseInt(st.nextToken());
		}

		adj = new ArrayList<>();
		for(int i = 0; i <= sink; i++)
			adj.add(new ArrayList<>());

		for(int i = 1; i <= N; i++)
			addAdj(source, i, 1, 0);
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++)
				addAdj(i, N + j, 1, time[i][j]);
		}
		for(int i = N + 1; i < sink; i++)
			addAdj(i, sink, 1, 0);

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
				cost += path[i].cost * flow;
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