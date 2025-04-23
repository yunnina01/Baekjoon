import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE - 1;
	static final int MIN = Integer.MIN_VALUE + 1;
	static List<List<Edge>> adj;
	static Edge[] path;
	static int[] prev, dist;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

        int source = 0;
		int sink = N + 1;

		int[][] A = new int[N + 1][N + 1];
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = i + 1; j <= N; j++)
				A[i][j] = Integer.parseInt(st.nextToken());
		}

		adj = new ArrayList<>();
		for(int i = 0; i <= sink; i++)
			adj.add(new ArrayList<>());

		addAdj(source, 1, P, 0);
		for(int i = 1; i < N; i++)
			addAdj(i, i + 1, INF, 0);

		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = i + 1; j <= N; j++) {
				int C = Integer.parseInt(st.nextToken());
				addAdj(i, j, A[i][j], C);
			}
		}
		addAdj(N, sink, P, 0);
		
		path = new Edge[sink + 1];
		prev = new int[sink + 1];
		dist = new int[sink + 1];
		visit = new boolean[sink + 1];
		System.out.println(MCMF(source, sink));
	}
	
	static int MCMF(int source, int sink) {
		int totalFlow = 0;
		while(SPFA(source, sink)) {
			int flow = INF;
			for(int i = sink; i != source; i = prev[i])
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			for(int i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				totalFlow += path[i].cost * flow;
			}
		}
		return totalFlow;
	}

	static boolean SPFA(int source, int sink) {
		Queue<Integer> queue = new LinkedList<>();

		Arrays.fill(visit, false);
		Arrays.fill(dist, MIN);
		queue.offer(source);
		dist[source] = 0;
		visit[source] = true;

		while(!queue.isEmpty()) {
			int now = queue.poll();
			visit[now] = false;
			for(Edge line : adj.get(now)) {
				if(line.capacity - line.flow > 0) {
					int next = dist[now] + line.cost;
					if(next > dist[line.to]) {
						dist[line.to] = next;
						prev[line.to] = now;
						path[line.to] = line;
						if(!visit[line.to]) {
							queue.offer(line.to);
							visit[line.to] = true;
						}
					}
				}
			}
		}
		return dist[sink] > MIN;
	}
	
	private static void addAdj(int from, int to, int capacity, int cost) {
		Edge forward, backward;
		
		forward = new Edge(to, capacity, cost);
		backward = new Edge(from, 0, -cost);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
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