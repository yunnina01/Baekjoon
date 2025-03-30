import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static List<List<Edge>> adj;
	static List<Edge> edges;
	static Queue<Integer> queue;
	static int[] level, work;
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		adj = new ArrayList<>(n + 1);
		for(int i = 0; i <= n; i++)
			adj.add(new ArrayList<>());

		edges = new ArrayList<>();
		int sum = 0;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

			addAdj(a, b, c);
			sum += c;
		}

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

        int min = INF;
		for(Edge off : edges) {
			for(Edge edge : edges) {
				edge.flow = 0;
				edge.reverse.flow = 0;
			}
			off.on = false;
			off.reverse.on = false;
			min = Math.min(min, dinic(s, t));
			off.on = true;
			off.reverse.on = true;
		}
		System.out.print(sum - min);
	}
	
	static void addAdj(int from, int to, int capacity) {
		Edge forward = new Edge(to, capacity);
		Edge backward = new Edge(from, capacity);

		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
		edges.add(forward);
	}
	
	static int dinic(int source, int sink) {
		queue = new ArrayDeque<>();
		level = new int[n + 1];
		int total = 0, flow;
		while(BFS(source, sink)) {
			work = new int[n + 1];
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
				if(edge.on && level[edge.to] == INF && edge.capacity > edge.flow) {
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
			if(edge.on && level[edge.to] == level[now] + 1 && edge.capacity > edge.flow) {
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
    boolean on;
    Edge reverse;
    
    Edge(int to, int capacity) {
        this.to = to;
        this.capacity = capacity;
        on = true;
    }
}