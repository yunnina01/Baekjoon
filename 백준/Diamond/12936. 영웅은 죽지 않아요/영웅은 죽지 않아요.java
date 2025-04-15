import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static List<List<Edge>> adj;
	static Queue<Integer> queue;
	static int[] level, work;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int source = 0;
		int sink = N + M + 1;

		adj = new ArrayList<>();
		for(int i = 0; i <= sink; i++)
			adj.add(new ArrayList<>());

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
            int P = Integer.parseInt(st.nextToken());
			addAdj(source, i, P);
		}

		int sum = 0;
		for(int i = N + 1; i < sink; i++) {
			st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

			addAdj(A, i, INF);
			addAdj(B, i, INF);
			sum += C;
			addAdj(i, sink, C);
		}
		System.out.print(sum - dinic(source, sink));
	}
	
	static void addAdj(int from, int to, int capacity) {
		Edge forward = new Edge(to, capacity);
		Edge backward = new Edge(from, 0);

		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	static int dinic(int source, int sink) {
		queue = new ArrayDeque<>();
		level = new int[sink + 1];
		int total = 0, flow;
		while(BFS(source, sink)) {
			work = new int[sink + 1];
			do {
				flow = DFS(source, sink, INF);
				total += flow;
			} while(flow != 0);
		}
		return total;
	}
	
	static boolean BFS(int source, int sink) {
		Arrays.fill(level, INF);
		queue.offer(source);
		level[source] = 0;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(Edge edge : adj.get(now)) {
				if(level[edge.to] == INF && edge.capacity > edge.flow) {
					level[edge.to] = level[now] + 1;
					queue.offer(edge.to);
				}
			}
		}
		return level[sink] != INF;
	}
	
	static int DFS(int now, int sink, int max) {
		if(now == sink)
			return max;

		List<Edge> edges = adj.get(now);
		for(; work[now] < edges.size(); work[now]++) {
			Edge edge = edges.get(work[now]);
			if(level[edge.to] == level[now] + 1 && edge.capacity > edge.flow) {
				int flow = DFS(edge.to, sink, Math.min(max, edge.capacity - edge.flow));
				if(flow > 0) {
					edge.flow += flow;
					edge.reverse.flow -= flow;
					return flow;
				}
			}
		}
		return 0;
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