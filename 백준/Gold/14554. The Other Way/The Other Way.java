import java.io.*;
import java.util.*;

public class Main {
	static final long INF = Long.MAX_VALUE;
    static final int MOD = 1_000_000_009;
	static List<List<Node>> adj;
	static PriorityQueue<Node> pq;
	static long dist[];
	static int dp[];
	static int N, M, S, E;
    
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		adj = new ArrayList<>();
		dist = new long[N + 1];
		for(int i = 0; i <= N; i++) {
			adj.add(new ArrayList<>());
			dist[i]	= INF;
		}

		for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

			adj.get(A).add(new Node(B, C));
			adj.get(B).add(new Node(A, C));
		}
		
		dp = new int[N + 1];
		Dijkstra();

		System.out.println(dp[E]);
	}

	static void Dijkstra() {
		pq = new PriorityQueue<Node>((a, b) -> (int)(a.dist - b.dist));
		pq.offer(new Node(S, 0));
		dist[S] = 0;
		dp[S] = 1;
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if(now.node == E)
                return;
			
			for(Node next : adj.get(now.node)) {
				long nextDist = now.dist + next.dist;
				if(dist[next.node] > nextDist) {
					dp[next.node] = dp[now.node];
					dist[next.node] = nextDist;
					pq.add(new Node(next.node, nextDist));
				} else if(dist[next.node] == nextDist)
					dp[next.node] = (dp[next.node] + dp[now.node]) % MOD;
			}
		}
	}
}

class Node {
	int node;
    long dist;

	Node(int node, long dist) {
		this.node = node;
        this.dist = dist;
	}
}