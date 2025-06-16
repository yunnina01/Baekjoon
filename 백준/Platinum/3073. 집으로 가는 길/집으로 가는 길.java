import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static final int MAX = 202;
	static List<List<Edge>> adj;
	static Edge[] path;
	static int[] prev, dist;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			if(N == 0 && M == 0)
				break;

			List<int[]> children = new ArrayList<>();
			List<int[]> homes = new ArrayList<>();
			children.add(null);
			homes.add(null);
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				char[] str = br.readLine().toCharArray();
				for(int j = 0; j < M; j++) {
					if(str[j] == 'm')
						children.add(new int[] {i, j});
					else if(str[j] == 'H') {
						homes.add(new int[] {i, j});
						cnt++;
					}
				}
			}

			int source = 0;
			int sink = cnt * 2 + 1;

			adj = new ArrayList<>();
			for(int i = 0; i <= sink; i++)
				adj.add(new ArrayList<>());

			for(int i = 1; i <= cnt; i++)
				addAdj(source, i, 1, 0);
			for(int i = 1; i <= cnt; i++) {
				for(int j = 1; j <= cnt; j++)
					addAdj(i, cnt + j, 1, getDist(children.get(i), homes.get(j)));
			}
			for(int i = 1; i <= cnt; i++)
				addAdj(cnt + i, sink, 1, 0);

			path = new Edge[MAX];
			prev = new int[MAX];
			dist = new int[sink + 1];
			visit = new boolean[MAX];
			sb.append(MCMF(source, sink) + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	static void addAdj(int from, int to, int capacity, int cost) {
		Edge forward = new Edge(to, capacity, cost);
		Edge backward = new Edge(from, 0, -cost);

		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	static int getDist(int[] child, int[] home) {
		return Math.abs(child[0] - home[0]) + Math.abs(child[1] - home[1]);
	}
	
	static int MCMF(int source, int sink) {
		int cost = 0;
		while(SPFA(source, sink)) {
			int flow = INF;
			for(int i = sink; i != source; i = prev[i])
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			for(int i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				cost += flow * path[i].cost;
			}
		}
		return cost;
	}
	
	static boolean SPFA(int source, int sink) {
		Queue<Integer> queue = new ArrayDeque<>();
		Arrays.fill(dist, INF);
		queue.offer(source);
		dist[source] = 0;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			visit[now] = false;
			for(Edge edge : adj.get(now)) {
				if(edge.capacity > edge.flow && dist[now] + edge.cost < dist[edge.to]) {
					dist[edge.to] = dist[now] + edge.cost;
					prev[edge.to] = now;
					path[edge.to] = edge;
					if(!visit[edge.to]) {
						queue.offer(edge.to);
						visit[edge.to] = true;
					}
				}
			}
		}
		if(dist[sink] == INF)
			return false;
		return true;
	}
}
	
class Edge {
	int to, capacity, flow, cost;
	Edge reverse;
	
	Edge(int to, int capacity, int cost) {
		this.to = to;
		this.capacity = capacity;
		this.cost = cost;
	}
}