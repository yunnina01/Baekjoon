import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1,0}, {0, -1}};
	static List<List<Edge>> adj;
	static Queue<Integer> queue;
	static int[] level, work;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int size = n;
		int max = m * size;
		int source = 2 * max;
		int sink = source + 1;

		String[] map = new String[m];
		for(int i = 0; i < m; i++)
			map[i] = br.readLine();

		int[] costs = new int[c];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < c; i++)
			costs[i] = Integer.parseInt(st.nextToken());

		adj = new ArrayList<>();
		for(int i = 0; i <= sink; i++)
			adj.add(new ArrayList<>());

		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				int pos = i * size + j;
				switch (map[i].charAt(j)) {
                    case 'B':
                        addEdge(source, pos, INF);
                    case '.':
                        addEdge(pos, pos + max, INF);
                        break;
                    default:
                        addEdge(pos, pos + max, costs[map[i].charAt(j) - 'a']);
                }
                if(i == 0 || i == m - 1 || j == 0 || j == n - 1)
                    addEdge(pos + max, sink, INF);

                for(int[] dir : DIRECTIONS) {
                    int ni = i + dir[0];
                    int nj = j + dir[1];
                    if(ni < 0 || ni >= m || nj < 0 || nj >= n)
                        continue;

                    int next = ni * size + nj;
                    addEdge(pos + max, next, INF);
                }
			}
		}

		int res = dinic(source, sink);
		System.out.print(res == INF ? "-1" : res);
	}

	static void addEdge(int from, int to, int capacity) {
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