import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static List<List<Edge>> adj;
    static Queue<Integer> queue;
	static int[] level, work;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int num = Integer.parseInt(st.nextToken());
		
        int size = N;
		int max = N * size;
		int source = 2 * max;
		int sink = source + 1;

		adj = new ArrayList<>(sink + 1);
		for(int i = 0; i <= sink; i++)
			adj.add(new ArrayList<>());

		char[][] map = new char[N][];
		for(int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int pos = i * size + j;
				switch(map[i][j]) {
				case '+':
					break;
				case 'O':
					addEdge(pos, sink, INF);
					break;
				case '.':
					addEdge(pos, pos + max, 1);
					for(int[] dir : DIRECTIONS) {
						int x = i + dir[0];
						int y = j + dir[1];
						if(x < 0 || x >= N || y < 0 || y >= N || map[x][y] == '+')
							continue;

						int next = x * size + y;
						addEdge(pos + max, next, INF);
					}
					break;
				case 'X':
					addEdge(source, pos, INF);
					for(int[] dir : DIRECTIONS) {
						int x = i + dir[0];
						int y = j + dir[1];
						if(x < 0 || x >= N || y < 0 || y >= N || map[x][y] == '+')
							continue;

						int next = x * size + y;
						addEdge(pos, next, INF);
					}
				}
			}
		}
		
        int res = dinic(source, sink);
		if(res > num)
			System.out.print("No");
        else
		    System.out.print("Yes\n" + res);
	}
	
	static void addEdge(int from, int to, int capacity) {
		Edge forward = new Edge(to, capacity);
		Edge backward = new Edge(from, 0);

		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	private static int dinic(int source, int sink) {
		queue = new ArrayDeque<>();
		level = new int[sink + 1];
		int total = 0, flow;
		while(BFS(source, sink)) {
			work = new int[sink + 1];
			do {
				flow = DFS(source, sink, INF);
				total += flow;
			} while(flow > 0);
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