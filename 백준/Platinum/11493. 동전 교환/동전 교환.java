import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static List<List<Edge>> coinList;
	static Edge[] path;
	static int[] prev;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int src = 0;
            int sink = n + 1;

            coinList = new ArrayList<>();
            for(int i = 0; i <= sink; i++)
                coinList.add(new ArrayList<>());

            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                addCoin(x, y, INF, 1);
                addCoin(y, x, INF, 1);
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++) {
                if(Integer.parseInt(st.nextToken()) == 0)
                    addCoin(i, sink, 1, 0);
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 1; i <= n; i++) {
                if(Integer.parseInt(st.nextToken()) == 0)
                    addCoin(src, i, 1, 0);
            }

            path = new Edge[sink + 1];
            prev = new int[sink + 1];
            int res = 0;
            while(SPFA(src, sink)) {
                int minFlow = INF;
                for(int i = sink; i != src; i = prev[i])
                    minFlow = Math.min(minFlow, path[i].capacity - path[i].flow);
                for(int i = sink; i != src; i = prev[i]) {
                    path[i].flow += minFlow;
                    path[i].reverse.flow -= minFlow;
                    res += path[i].cost * minFlow;
                }
            }
			sb.append(res + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static void addCoin(int from, int to, int capacity, int cost) {
		Edge forward = new Edge(to, capacity, cost);
		Edge backward = new Edge(from, 0, -cost);

		forward.reverse = backward;
		backward.reverse = forward;
		coinList.get(from).add(forward);
		coinList.get(to).add(backward);
	}
	
	static boolean SPFA(int start, int sink) {
		Queue<Integer> queue = new LinkedList<>();
        int[] cost = new int[sink + 1];
        boolean[] visit = new boolean[sink + 1];
		Arrays.fill(cost, INF);
		Arrays.fill(visit, false);
		queue.add(start);
		cost[start] = 0;
		visit[start] = true;

		while(!queue.isEmpty()) {
			int now = queue.poll();
			visit[now] = false;
			for(Edge edge : coinList.get(now)) {
				if(edge.capacity - edge.flow > 0) {
					int dist = cost[now] + edge.cost;
					if(dist < cost[edge.to]) {
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
		return cost[sink] < INF;
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