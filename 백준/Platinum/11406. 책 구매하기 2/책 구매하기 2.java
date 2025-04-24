import java.io.*;
import java.util.*;

public class Main {
	static List<List<Edge>> adj;
	static Edge[] path;
    static int[] prev;

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
		for(int i = 1; i <= N; i++)
			addAdj(source, i, Integer.parseInt(st.nextToken()));

		st = new StringTokenizer(br.readLine());
		for(int i = N + 1; i < sink; i++)
			addAdj(i, sink, Integer.parseInt(st.nextToken()));

		for(int i = N + 1; i < sink; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int C = Integer.parseInt(st.nextToken());
				if(C > 0)
					addAdj(j, i, C);
			}
		}
		
		path = new Edge[sink + 1];
		prev = new int[sink + 1];
		System.out.println(maxFlow(source, sink));
	}

	static void addAdj(int from, int to, int capacity) {
		Edge forward = null, backward;
		for(Edge line : adj.get(from)) {
			if(line.to == to) {
				forward = line;
				forward.capacity = capacity;
				break;
			}
		}
		if(forward == null) {
			forward = new Edge(to, capacity);
			backward = new Edge(from, 0);

			forward.reverse = backward;
			backward.reverse = forward;
			adj.get(from).add(forward);
			adj.get(to).add(backward);
		}
	}
	
	static int maxFlow(int source, int sink) {
		int totalFlow = 0;
		while(BFS(source, sink)) {
			int minFlow = 101;
			for(int i = sink; i != source; i = prev[i])
				minFlow = Math.min(minFlow, path[i].capacity - path[i].flow);
			for(int i = sink; i != source; i = prev[i]) {
				path[i].flow += minFlow;
				path[i].reverse.flow -= minFlow;
			}
			totalFlow += minFlow;
		}
		return totalFlow;
	}

    static boolean BFS(int source, int sink) {
		Queue<Integer> queue = new LinkedList<>();
		Arrays.fill(prev, -1);
		queue.offer(source);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(Edge line : adj.get(now)) {
				if(line.capacity - line.flow > 0 && prev[line.to] == -1) {
					queue.offer(line.to);
					path[line.to] = line;
					prev[line.to] = now;
					if(line.to == sink)
						return true;
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
        this.flow = 0;
    }
}