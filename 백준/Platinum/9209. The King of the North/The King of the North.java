import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static List<List<Edge>> adj;
	static Queue<Integer> queue;
	static int[] dir, level, work;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

        int size = C + 2;
		int max = (R + 2) * size;
		int source = 2 * max;
		int sink = source + 1;

		int[] map = new int[max];
		for(int i = 1; i <= R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= C; j++)
				map[i * size + j] = Integer.parseInt(st.nextToken());
		}

		adj = new ArrayList<>(sink + 1);
		for(int i = 0; i <= sink; i++)
			adj.add(new ArrayList<>());

		dir = new int[] {-size, 1, size, -1};
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				int pos = i * size + j;
				if(map[pos] == 0)
					continue;

				addAdj(pos, pos + max, map[pos]);
				if(i == 1 || j == 1 || i == R || j == C)
					addAdj(source, pos, INF);
				for(int d : dir) {
					int next = pos + d;
					if(map[next] != 0)
						addAdj(pos + max, next, INF);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) + 1;
        int c = Integer.parseInt(st.nextToken()) + 1;

		addAdj(r * size + c + max, sink, INF);
		System.out.print(dinic(source, sink));
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