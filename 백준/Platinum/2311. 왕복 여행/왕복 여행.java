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

		int source = 1;
		int sink = 2 * N;

		adj = new ArrayList<>();
		for(int i = 0; i < sink + 1; i++)
			adj.add(new ArrayList<>());

		for(int i = 1; i <= N; i++)
			addAdj(i, i + N, 2, 0);

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());

			addAdj(A + N, B, 1, C);
			addAdj(B + N, A, 1, C);
		}

		path = new Edge[sink + 1];
		prev = new int[sink + 1];
		dist = new int[sink + 1];
		visit = new boolean[sink + 1];
		System.out.println(MCMF(source, sink));
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
		Queue<Integer> queue = new LinkedList<>();
		Arrays.fill(prev, -1);
		Arrays.fill(dist, INF);
		Arrays.fill(visit, false);
		queue.offer(source);
		dist[source] = 0;
		visit[source] = true;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			visit[now] = false;
			for(Edge line : adj.get(now)) {
				if(line.capacity - line.flow > 0) {
					int cost = dist[now] + line.cost;
					if(cost < dist[line.to]) {
						path[line.to] = line;
						dist[line.to] = cost;
						prev[line.to] = now;
						if(!visit[line.to]) {
							queue.offer(line.to);
							visit[line.to] = true;
						}
					}
				}
			}
		}
		return dist[sink] < INF;
	}
}
	
class Edge {
	int to, capacity, flow, cost;
	Edge reverse;
	
	Edge(int to, int capacity, int cost) {
		this.to = to;
		this.capacity = capacity;
		this.flow = 0;
		this.cost = cost;
	}
}