import java.io.*;
import java.util.*;

public class Main {
	static final int MIN = Integer.MIN_VALUE + 1;
	static final int INF = Integer.MAX_VALUE - 1;
	static List<List<Edge>> adj;
	static Edge[] path;
	static int[] prev, dist;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		while(true) {
            int n = Integer.parseInt(br.readLine());

            if(n == 0)
                break;

            int source = 0;
            int sink = 366;

            adj = new ArrayList<>();
            for(int i = 0; i <= sink; i++)
                adj.add(new ArrayList<>());

            for(int i = 0; i <= 365; i++)
                addAdj(i, i + 1, 2, 0);

            for(int k = 0; k < n; k++) {
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken()) + 1;
                int w = Integer.parseInt(st.nextToken());

                addAdj(i, j, 1, w);
            }

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
		Queue<Integer> queue = new LinkedList<>();
		Arrays.fill(visit, false);
		Arrays.fill(dist, MIN);
		queue.offer(source);
		dist[source] = 0;
		visit[source] = true;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			visit[now] = false;
			for(Edge line : adj.get(now)) {
				if(line.capacity - line.flow > 0) {
					int cost = dist[now] + line.cost;
					if(cost > dist[line.to]) {
						dist[line.to] = cost;
						prev[line.to] = now;
						path[line.to] = line;
						if(!visit[line.to]) {
							queue.offer(line.to);
							visit[line.to] = true;
						}
					}
				}
			}
		}
		return dist[sink] > MIN;
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