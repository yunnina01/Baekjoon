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
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int size = R * C;
		int source = 2 * size;
		int sink = 2 * size + 1;

		adj = new ArrayList<>();
		for(int i = 0; i <= sink; i++)
			adj.add(new ArrayList<>());

		char[] chess = new char[size];
		for(int i = 0; i < R; i++) {
			String status = br.readLine();
			for(int j = 0; j < C; j++) {
				int pos = i * C + j;
				chess[pos] = status.charAt(j);
				if(chess[pos] == '.') {
					addAdj(pos, pos + size, 1);
					if((i + j) % 2 == 1) {
						if(i % 2 == 0)
							addAdj(source, pos, 1);
						else
							addAdj(pos + size, sink, 1);
					}
				}
			}
		}

		for(int i = 0; i < R; i += 2) {
			for(int j = 1; j < C; j += 2) {
				int pos = i * C + j;
				if(chess[pos] == '.') {
					if(i > 0 && chess[pos - C] == '.')
						addAdj(pos + size, pos - C, 1);
					if(i < R - 1 && chess[pos + C] == '.')
						addAdj(pos + size, pos + C, 1);
					if(j > 0 && chess[pos - 1] == '.')
						addAdj(pos + size, pos - 1, 1);
					if(j < C - 1 && chess[pos + 1] == '.')
						addAdj(pos + size, pos + 1, 1);
				}
			}
		}

		for(int i = 0; i < R; i += 2) {
			for(int j = 0; j < C; j += 2) {
				int pos = i * C + j;
				if(chess[pos] == '.') {
					if(i > 0 && chess[pos - C] == '.')
						addAdj(pos + size, pos - C, 1);
					if(i < R - 1 && chess[pos + C] == '.')
						addAdj(pos + size, pos + C, 1);
				}
			}
		}

		for(int i = 1; i < R; i += 2) {
			for(int j = 1; j < C; j += 2) {
				int pos = i * C + j;
				if (chess[pos] == '.') {
					if(j > 0 && chess[pos - 1] == '.')
						addAdj(pos + size, pos - 1, 1);
					if(j < C - 1 && chess[pos + 1] == '.')
						addAdj(pos + size, pos + 1, 1);
				}
			}
		}
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
		level = new int[sink + 1];
		queue = new ArrayDeque<>();
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
        this.flow = 0;
    }
}