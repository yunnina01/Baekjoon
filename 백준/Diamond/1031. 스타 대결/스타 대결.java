import java.io.*;
import java.util.*;

public class Main {
	static final int MAX_CAPACITY = 51;
	static List<List<Edge>> adj;
	static Edge[] path;
	static int[] prev;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

        int source = 0;
		int sink = N + M + 1;
		
		adj = new ArrayList<>();
		for(int i = source; i <= sink; i++)
			adj.add(new ArrayList<>());
        int[] sum = new int[2];

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			int jcnt = Integer.parseInt(st.nextToken());
			addAdj(source, i, jcnt);
			sum[0] += jcnt;
		}
		for(int i = 1; i <= N; i++) {
			for(int j = N + 1; j <= N + M; j++)
				addAdj(i, j, 1);
		}

		st = new StringTokenizer(br.readLine());
		for(int i = N + 1; i <= N + M; i++) {
			int hcnt = Integer.parseInt(st.nextToken());
			addAdj(i, sink, hcnt);
			sum[1] += hcnt;
		}
		
		if(sum[0] != sum[1] || N * M < sum[0])
			sb.append(-1);
		else {
			prev = new int[sink + 1];
			path = new Edge[sink + 1];
			int maxFlow = MCMF(source, sink, N, M);
			if(maxFlow != sum[0])
				sb.append(-1);
            else {              
                int[][] res = new int[N][M];
                for(int i = 1; i <= N; i++) {
                    for(Edge line : adj.get(i)) {
                        if(line.forward)
                            res[i - 1][line.to - N - 1] = line.flow;
                    }
                }

                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < M; j++)
                        sb.append(res[i][j]);
                    sb.append('\n');
                }
            }
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	static void addAdj(int from, int to, int capacity) {
		Edge forward = new Edge(to, capacity, true);
		Edge backward = new Edge(from, 0, false);

		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
	}
	
	static int MCMF(int source, int sink, int n, int m) {
		int totalFlow = 0;
		Arrays.fill(prev, -1);
		while(BFS(source, sink)) {
			int flow = MAX_CAPACITY;
			for(int i = sink; i != source; i = prev[i])
				flow = Math.min(flow, path[i].capacity - path[i].flow);
			for(int i = sink; i != source; i = prev[i]) {
				path[i].flow += flow;
				path[i].reverse.flow -= flow;
			}
			totalFlow += flow;
			Arrays.fill(prev, -1);
		}
		
		for(int i = 1; i <= n; i++) {
			for(int j = n + 1; j <= n + m; j++) {
				for(Edge line : adj.get(i)) {
					if(line.to == j && line.flow == 1) {
						line.flow = 0;
						if(fix(i, j)) {
							int flow = MAX_CAPACITY;
							for(int k = j; k != i; k = prev[k])
								flow = Math.min(flow, path[k].capacity - path[k].flow);

							for(int k = j; k != i; k = prev[k]) {
								path[k].flow += flow;
								path[k].reverse.flow -= flow;
							}
						} else
							line.flow = 1;
					}
				}
			}
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
					prev[line.to] = now;
					path[line.to] = line;
					if(line.to == sink)
						return true;
					queue.offer(line.to);
				}
			}
		}
		return false;
	}

	static boolean fix(int source, int sink) {
		Queue<Integer> queue = new LinkedList<>();
		Arrays.fill(prev, -1);
		queue.offer(source);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(Edge line : adj.get(now)) {
				if(now < source || (now == source && line.to <= sink))
					continue;

				if(line.capacity - line.flow > 0 && prev[line.to] == -1) {
					prev[line.to] = now;
					path[line.to] = line;
					if(line.to == sink)
						return true;
					queue.offer(line.to);
				}
			}
		}
		return false;
	}
}

class Edge {
    Edge reverse;
    int to, capacity, flow;
    boolean forward;
    
    Edge(int to, int capacity, boolean forward) {
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
        this.forward = forward;
    }
}