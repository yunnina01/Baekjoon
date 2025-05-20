import java.io.*;
import java.util.*;

public class Main {
	static final int[][] matrix = {{4, 3, 2, 1}, {8, 7, 6, 5}, {12, 11, 10, 9}};
	static final int INF = Integer.MAX_VALUE;
	static final int MIN = Integer.MIN_VALUE;
	static List<List<Edge>> adj;
	static Queue<Integer> queue;
	static Edge[] path;
	static int[] prev, dist;
	static boolean[] visit;
	static int n, m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		while(true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0)
                break;

            int source = 0;
            int sink = m + n + 1;

            int[] p = new int[n + 1];
            for(int i = 1; i <= n; i++)
                p[i] = Integer.parseInt(br.readLine());

            adj = new ArrayList<>();
            for(int i = 0; i <= sink; i++)
                adj.add(new ArrayList<>());

            for(int i = 1; i <= m; i++)
                addAdj(source, i, 1, 0);

            for(int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken()) - 1;
                for(int j = 0; j < 4; j++) {
                    int c = Integer.parseInt(st.nextToken());
                    addAdj(i, m + 1 + c, 1, matrix[y][j]);
                }
            }

            for(int i = 1; i <= n; i++)
                addAdj(m + i, sink, p[i], 0);

            queue = new ArrayDeque<>();
            path = new Edge[sink + 1];
            prev = new int[sink + 1];
            dist = new int[sink + 1];
            visit = new boolean[sink + 1];
            
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
	
	static int MCMF(int source, int sink) {
		int cost = 0;
		while(LPFA(source, sink)) {
			int flow = INF;
			for(int i = sink; i != source; i = prev[i])
				flow = Math.min(flow, path[i].capacity - path[i].flow);

			for(int i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
				cost += path[i].cost * flow;
			}
		}
		return cost;
	}
	
	static boolean LPFA(int source, int sink) {
		Arrays.fill(dist, MIN);
		queue.offer(source);
		dist[source] = 0;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			visit[now] = false;
			for(Edge edge : adj.get(now)) {
				if(edge.capacity > edge.flow && dist[now] + edge.cost > dist[edge.to]) {
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
		if(dist[sink] == MIN)
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