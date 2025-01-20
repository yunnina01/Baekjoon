import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static final int[][] DIRECTIONS = {{-1, 0}, {0, -1}};
	static List<List<Edge>> adj;
	static Queue<Integer> queue;
	static int[] level, work;
	static char[] res;
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int size = M;
		int max = N * size;
		int source = max;
		int sink = source + 1;

		int[][] lands = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
				lands[i][j] = Integer.parseInt(st.nextToken()) - 1;
		}

		int[][] bonus = new int[K][K];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < K; j++)
				bonus[i][j] = Integer.parseInt(st.nextToken());
		}

		int[][] a = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
				a[i][j] = Integer.parseInt(st.nextToken());
		}

		int[][] b = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
				b[i][j] = Integer.parseInt(st.nextToken());
		}

		adj = new ArrayList<>();
		for(int i = 0; i <= sink; i++)
			adj.add(new ArrayList<>());

		int sum = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int pos = i * size + j;
				if(a[i][j] > b[i][j]) {
					addEdge(source, pos, a[i][j] - b[i][j], false);
					sum += a[i][j];
				} else if (b[i][j] > a[i][j]) {
					sum += b[i][j];
					addEdge(pos, sink, b[i][j] - a[i][j], false);
				} else
					sum += a[i][j];

				for(int[] dir : DIRECTIONS) {
					int ni = i + dir[0];
					int nj = j + dir[1];
					if(ni < 0 || nj < 0)
						continue;

					if(bonus[lands[i][j]][lands[ni][nj]] > 0) {
						sum += bonus[lands[i][j]][lands[ni][nj]];
						int next = ni * size + nj;
						addEdge(pos, next, bonus[lands[i][j]][lands[ni][nj]], true);
					}
				}
			}
		}
		sb.append((sum - dinic(source, sink)) + "\n");

		res = new char[sink + 1];
		Arrays.fill(res, 'R');
		visit(source, source, sink);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++)
				sb.append(res[i * size + j]);
			sb.append('\n');
		}
		bw.write(sb.toString());
        bw.flush();
	}
	
	static void addEdge(int from, int to, int capacity, boolean twoWay) {
		Edge forward = new Edge(to, capacity);
		Edge backward = new Edge(from, twoWay ? capacity : 0);

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
	
	static void visit(int node, int source, int sink) {
		if(res[node] == 'L')
			return;

		res[node] = 'L';
		for(Edge edge : adj.get(node)) {
			if(edge.capacity > edge.flow)
				visit(edge.to, source, sink);
		}
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