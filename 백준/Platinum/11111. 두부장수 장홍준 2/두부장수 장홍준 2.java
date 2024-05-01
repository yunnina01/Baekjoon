import java.io.*;
import java.util.*;

public class Main {
	static final int MINDIST = Integer.MIN_VALUE;
    static final int[][] TOFU = {{10, 8, 7, 5, 1},
                                {8, 6, 4, 3, 1},
                                {7, 4, 3, 2, 1},
                                {5, 3, 2, 2, 1},
                                {1, 1, 1, 1, 0}};
	static List<List<Edge>> tofuList;
	static Edge[] path;
	static int[] prev;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
        int src = N * M;
		int sink = N * M + 1;
		char[] board = new char[N * M];
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				board[i * M + j] = str.charAt(j);
				if(board[i * M + j] == 'F')
					board[i * M + j]--;
			}
		}

		tofuList = new ArrayList<>();
		for(int i = 0; i <= sink; i++)
			tofuList.add(new ArrayList<>());

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				int pos = i * M + j;
				addTofu(src, pos, 2, 0);
				if((i + j) % 2 == 0) {
					if(j < M - 1) 
						addTofu(pos, pos + 1, 1, TOFU[board[pos] - 'A'][board[pos + 1] - 'A']);
					if(i < N - 1)
						addTofu(pos, pos + M, 1, TOFU[board[pos] - 'A'][board[pos + M] - 'A']);
					if(j > 0)
						addTofu(pos, pos - 1, 1, TOFU[board[pos] - 'A'][board[pos - 1] - 'A']);
					if(i > 0)
						addTofu(pos, pos - M, 1, TOFU[board[pos] - 'A'][board[pos - M] - 'A']);
				}
				addTofu(pos, sink, 1, 0);
			}
		}

		path = new Edge[sink + 1];
        prev = new int[sink + 1];
		int res = 0;
		while (SPFA(src, sink)) {
			int minFlow = 3;
			for(int i = sink; i != src; i = prev[i])
				minFlow = Math.min(minFlow, path[i].capacity - path[i].flow);
			for(int i = sink; i != src; i = prev[i]) {
				path[i].flow += minFlow;
				path[i].reverse.flow -= minFlow;
				res += path[i].cost;
			}
		}
		System.out.println(res);
    }

	static void addTofu(int from, int to, int capacity, int cost) {
		Edge forward = new Edge(to, capacity, cost);
		Edge backward = new Edge(from, 0, -cost);

		forward.reverse = backward;
		backward.reverse = forward;
		tofuList.get(from).add(forward);
		tofuList.get(to).add(backward);
	}
	
	static boolean SPFA(int source, int sink) {
		Queue<Integer> queue = new LinkedList<>();
		int[] cost = new int[sink + 1];
        boolean[] visit = new boolean[sink + 1];
		Arrays.fill(cost, MINDIST);
		Arrays.fill(visit, false);
		queue.add(source);
		cost[source] = 0;
		visit[source] = true;

		while(!queue.isEmpty()) {
			int now = queue.poll();
			visit[now] = false;
			for(Edge edge : tofuList.get(now)) {
				if(edge.capacity - edge.flow > 0) {
					int dist = cost[now] + edge.cost;
					if(cost[edge.to] < dist) {
						cost[edge.to] = dist;
						prev[edge.to] = now;
						path[edge.to] = edge;
						if(!visit[edge.to]) {
							queue.add(edge.to);
							visit[edge.to] = true;
						}
					}
				}
			}
		}
		return cost[sink] > MINDIST;
	}
}

class Edge {
    int to, capacity, flow, cost;
    Edge reverse;
    
    Edge(int to, int capacity, int cost) {
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
        this.cost = cost;
    }
}