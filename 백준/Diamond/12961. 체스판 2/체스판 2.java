import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static List<List<Edge>> adj;
	static Edge[] path;
	static int[] prev;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

        int size = R * C;
		int source = size * 2;
		int sink = size * 2+ 1;

		adj = new ArrayList<>();
		for(int i = 0; i <= sink; i++)
			adj.add(new ArrayList<>());

		char[] chess = new char[size];
		for(int i = 0; i < R; i++) {
			String state = br.readLine();
			for(int j = 0; j < C; j++) {
				int node = i * C + j;
				chess[node] = state.charAt(j);
				if(chess[node] == '.') {
					addAdj(node, node + size, 1);
					if((i + j) % 2 == 1) {
						if(i % 2 == 0)
							addAdj(source, node, 1);
						else
							addAdj(node + size, sink, 1);
					}
				}
			}
		}

		for(int i = 0; i < R; i += 2) {
			for(int j = 1; j < C; j += 2) {
				int node = i * C + j;
				if(chess[node] == '.') {
					if(i > 0 && chess[node - C] == '.')
						addAdj(node + size, node - C, 1);
					if(i < R - 1 && chess[node + C] == '.')
						addAdj(node + size, node + C, 1);
					if(j > 0 && chess[node - 1] == '.')
						addAdj(node + size, node - 1, 1);
					if(j < C - 1 && chess[node + 1] == '.')
						addAdj(node + size, node + 1, 1);
				}
			}
		}

		for(int i = 0; i < R; i += 2) {
			for(int j = 0; j < C; j += 2) {
				int node = i * C + j;
				if(chess[node] == '.') {
					if(i > 0 && chess[node - C] == '.')
						addAdj(node + size, node - C, 1);
					if(i < R - 1 && chess[node + C] == '.')
						addAdj(node + size, node + C, 1);
				}
			}
		}

		for(int i = 1; i < R; i += 2) {
			for(int j = 1; j < C; j += 2) {
				int node = i * C + j;
				if(chess[node] == '.') {
					if(j > 0 && chess[node - 1] == '.')
						addAdj(node + size, node - 1, 1);
					if(j < C - 1 && chess[node + 1] == '.')
						addAdj(node + size, node + 1, 1);
				}
			}
		}

		prev = new int[sink + 1];
		path = new Edge[sink + 1];
		System.out.print(maxFlow(source, sink));
	}
	
	static void addAdj(int from, int to, int capacity) {
		Edge forward = new Edge(to, capacity);
		Edge backward = new Edge(from, 0);
		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	static int maxFlow(int source, int sink) {
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
		Queue<Integer> queue = new LinkedList<>();
		Arrays.fill(prev, -1);
		queue.offer(source);

		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(Edge edge : adj.get(now)) {
				if(edge.capacity - edge.flow > 0 && prev[edge.to] == -1) {
					prev[edge.to] = now;
					path[edge.to] = edge;
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
    Edge reverse;
    int to, capacity, flow;
    
    Edge(int to, int capacity) {
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }
}