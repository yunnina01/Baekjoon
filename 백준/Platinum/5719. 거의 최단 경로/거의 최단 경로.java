import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 1_000_000_001;
	static List<Node>[] map;
	static int[][] removed;
	static int[] cost;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N + M == 0)
				break;

			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());

			map = new ArrayList[N];
			cost = new int[N];
			removed = new int[N][N];
			for(int i = 0; i < N; i++){
				map[i] = new ArrayList<>();
				cost[i] = INF;
				Arrays.fill(removed[i], -1);
			}
			for(int i = 0; i < M; i++){
				st = new StringTokenizer(br.readLine());
				int U = Integer.parseInt(st.nextToken());
				int V = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());

				map[U].add(new Node(V, P));
				removed[U][V] = P;
			}

			dijkstra(S);
			BFS(D);

			Arrays.fill(cost, INF);
			dijkstra(S);

			sb.append((cost[D] != INF ? cost[D] : -1) + "\n");
		}
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
	}

	static void dijkstra(int start){
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		cost[start] = 0;

		while(!pq.isEmpty()){
			Node now = pq.poll();
			if(cost[now.v] < now.w)
				continue;
			for(Node next : map[now.v]) {
				if(removed[now.v][next.v] == -1)
					continue;
				if(cost[next.v] > cost[now.v] + next.w ) {
					cost[next.v] = cost[now.v] + next.w;
					pq.offer(new Node(next.v, cost[next.v]));
				}
			}
		}
	}

	static void BFS(int x) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(x);

		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int next = 0; next < N; next++) {
				if(cost[now] == cost[next] + removed[next][now] && removed[next][now] != -1) {
					removed[next][now] = -1;
					queue.offer(next);
				}
			}
		}
	}
}

class Node implements Comparable<Node> {
	int v, w;

	Node(int v, int w) {
		this.v = v;
		this.w = w;
	}

	@Override
	public int compareTo(Node o) {
		return this.w - o.w;
	}
}