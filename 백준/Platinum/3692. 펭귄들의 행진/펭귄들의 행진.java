import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static List<List<Edge>> adj;
	static List<Edge> edgeList;
	static Queue<Integer> queue;
	static double[][] ices;
	static int[] level, work;
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            double D = Double.parseDouble(st.nextToken());

            int source = 2 * N;
            size = source + 1;
            
            adj = new ArrayList<>();
            for(int i = 0; i < size; i++)
                adj.add(new ArrayList<>());
            edgeList = new ArrayList<>();
            ices = new double[100][2];
            int sum = 0;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                ices[i][0] = Double.parseDouble(st.nextToken());
                ices[i][1] = Double.parseDouble(st.nextToken());
                int n = Integer.parseInt(st.nextToken());
                int m = Integer.parseInt(st.nextToken());

                if(n > 0) {
                    addAdj(source, i, n);
                    sum += n;
                }
                addAdj(i, i + N, m);
            }

            for(int i = 0; i < N; i++) {
                for(int j = i + 1; j < N; j++) {
                    if(canJump(ices[i][0], ices[i][1], ices[j][0], ices[j][1], D)) {
                        addAdj(i + N, j, INF);
                        addAdj(j + N, i, INF);
                    }
                }
            }

            boolean flag = false;
            for(int i = 0; i < N; i++) {
                for(Edge edge : edgeList)
                    edge.flow = 0;
                if(dinic(source, i) == sum) {
                    sb.append(i + " ");
                    flag = true;
                }
            }
            if(!flag)
                sb.append("-1");
			sb.append('\n');
		}
		bw.write(sb.toString());
        bw.flush();
	}

	static void addAdj(int from, int to, int capacity) {
		Edge forward = new Edge(to, capacity);
		Edge backward = new Edge(from, 0);

		forward.reverse = backward;
		backward.reverse = forward;
		adj.get(from).add(forward);
		adj.get(to).add(backward);
		edgeList.add(forward);
		edgeList.add(backward);
	}
	
	static boolean canJump(double x1, double y1, double x2, double y2, double d) {
        double dx = x2 - x1;
        double dy = y2 - y1;
		return Math.sqrt(dx * dx + dy * dy) <= d;
	}
	
	static int dinic(int source, int sink) {
		queue = new ArrayDeque<>();
		level = new int[201];
		int total = 0, flow;
		while(BFS(source, sink)) {
			work = new int[size];
			do {
				flow = DFS(source, sink, INF);
				total += flow;
			} while(flow != 0);
		}
		return total;
	}

	static boolean BFS(int source, int sink) {
		Arrays.fill(level, 0, size, INF);
		queue.offer(source);
		level[source] = 0;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(Edge edge : adj.get(now)) {
				if(level[edge.to] == INF && edge.capacity > edge.flow) {
					level[edge.to] = level[now] + 1;
					queue.offer(edge.to);
				}
			}
		}
		return level[sink] != INF;
	}
	
	static int DFS(int now, int sink, int max) {
		if(now == sink)
			return max;

		List<Edge> edges = adj.get(now);
		for(; work[now] < edges.size(); work[now]++) {
			Edge edge = edges.get(work[now]);
			if(level[edge.to] == level[now] + 1 && edge.capacity > edge.flow) {
				int flow = DFS(edge.to, sink, Math.min(max, edge.capacity - edge.flow));
				if(flow > 0) {
					edge.flow += flow;
					edge.reverse.flow -= flow;
					return flow;
				}
			}
		}
		return 0;
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