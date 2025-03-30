import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static List<List<Edge>> adj;
	static Edge[][] exist;
	static Edge[] path;
	static int[] prev;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		adj = new ArrayList<>();
		for(int i = 0; i < n; i++)
			adj.add(new ArrayList<>());

		exist = new Edge[n][n];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());
			addAdj(a, b, c);
		}

		st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()) - 1;
		int t = Integer.parseInt(st.nextToken()) - 1;

		path = new Edge[n];
		prev = new int[n];
		System.out.print(MCMF(s, t));
	}
	
	static void addAdj(int from, int to, int capacity) {
		if(exist[from][to] != null) {
			exist[from][to].capacity += capacity;
			exist[to][from].capacity += capacity;
			return;
		}

		Edge forward = new Edge(to, capacity);
		Edge backward = new Edge(from, capacity);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
		exist[from][to] = forward;
		exist[to][from] = backward;
	}
	
	static int MCMF(int source, int sink) {
		int totalFlow = 0;
		while(BFS(source, sink)) {
			int flow = INF;
			for(int i = sink; i != source; i = prev[i])
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			for(int i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
			}
			totalFlow += flow;
		}
		return totalFlow;
	}
	
	static boolean BFS(int source, int sink) {
		Arrays.fill(prev, -1);
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(source);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(Edge edge : adj.get(now)) {
				if(prev[edge.to] == -1 && edge.capacity > edge.flow) {
					path[edge.to] = edge;
					prev[edge.to] = now;
					if(edge.to == sink)
						return true;
					queue.offer(edge.to);
				}
			}
		}
		return false;
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