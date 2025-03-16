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
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int size = N * M;
		int source = size;
		int sink = source + 1;

		char[] map = new char[size];
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			char[] str = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				map[i * M + j] = str[j];
				if(str[j] == '.')
					cnt++;
			}
		}

		adj = new ArrayList<>();
		for(int i = 0; i <= sink; i++)
			adj.add(new LinkedList<>());

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int pos = i * M + j;
				if(map[pos] == '.') {
					if((i + j) % 2 == 0) {
						addAdj(source, pos, 1);
						if(i - 1 >= 0 && map[pos - M] == '.')
							addAdj(pos, pos - M, 1);
						if(i + 1 < N && map[pos + M] == '.')
							addAdj(pos, pos + M, 1);
						if(j - 1 >= 0 && map[pos - 1] == '.')
							addAdj(pos, pos - 1, 1);
						if(j + 1 < M && map[pos + 1] == '.')
							addAdj(pos, pos + 1, 1);
					} else
						addAdj(pos, sink, 1);
				}
			}
		}
        
		path = new Edge[sink + 1];
		prev = new int[sink + 1];
		System.out.print(cnt - MCMF(source, sink));
	}
	
	static void addAdj(int from, int to, int capacity) {
		Edge forward = new Edge(to, capacity);
		Edge backward = new Edge(from, 0);

		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	private static int MCMF(int source, int sink) {
		int total = 0;
		while(BFS(source, sink)) {
			int flow = INF;
			for(int i = sink; i != source; i = prev[i])
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			for(int i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
			}
			total += flow;
		}
		return total;
	}
	
	static boolean BFS(int source, int sink) {
		Queue<Integer> queue = new ArrayDeque<>();
		Arrays.fill(prev, -1);
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