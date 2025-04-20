import java.io.*;
import java.util.*;

public class Main {
	static final long INF = Long.MAX_VALUE;
	static ArrayDeque<Integer> updated;
	static Node[] adj;
	static int[][] dp;
	static long[] minDist, distance;
	static int[] ct, depth, subSize;
	static boolean[] visit;
	static int N, logN;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

		adj = new Node[N];
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());

			adj[A] = new Node(B, D, adj[A]);
			adj[B] = new Node(A, D, adj[B]);
		}

		logN = (int) Math.ceil(Math.log(N) / Math.log(2));
		dp = new int[logN + 1][N + 1];
		distance = new long[N + 1];
		depth = new int[N + 1];
		DFS(0, N, 0L);

		for(int i = 1; i <= logN; i++) {
			for(int j = 0; j < N; j++)
				dp[i][j] = dp[i - 1][dp[i - 1][j]];
		}

		minDist = new long[N];
		ct = new int[N];
		subSize = new int[N];
		visit = new boolean[N];
		buildCentroidTree(0, N);

		updated = new ArrayDeque<>();
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());

			while(!updated.isEmpty())
				minDist[updated.pollFirst()] = INF;

			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < S; j++)
				update(Integer.parseInt(st.nextToken()));

			long min = INF;
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < T; j++)
				min = Math.min(min, query(Integer.parseInt(st.nextToken())));
			sb.append(min + "\n");
		}
        bw.write(sb.toString());
        bw.flush();
	}
	
	static void DFS(int now, int parent, long weight) {
		dp[0][now] = parent;
		depth[now] = depth[parent] + 1;
		distance[now] = distance[parent] + weight;
		for(Node child = adj[now]; child != null; child = child.next) {
			if(child.idx == parent)
				continue;
			DFS(child.idx, now, child.weight);
		}
	}
	
	static void buildCentroidTree(int now, int parent) {
		int centroid = getCentroid(now, -1, getSize(now, -1) >> 1);
		ct[centroid] = parent;
		visit[centroid] = true;
		minDist[centroid] = INF;
		for(Node child = adj[centroid]; child != null; child = child.next) {
			if(visit[child.idx])
				continue;
			buildCentroidTree(child.idx, centroid);
		}
	}
	
	static int getCentroid(int now, int parent, int threshold) {
		for(Node child = adj[now]; child != null; child = child.next) {
			if(visit[child.idx] || child.idx == parent)
				continue;
			if(subSize[child.idx] > threshold)
				return getCentroid(child.idx, now, threshold);
		}
		return now;
	}
	
	static int getSize(int now, int parent) {
		subSize[now] = 1;
		for(Node child = adj[now]; child != null; child = child.next) {
			if(visit[child.idx] || child.idx == parent)
				continue;
			subSize[now] += getSize(child.idx, now);
		}
		return subSize[now];
	}
	
	static void update(int v) {
		for(int i = v; i != N; i = ct[i]) {
			long dist = getDist(i, v);
			if(dist < minDist[i]) {
				minDist[i] = dist;
				updated.addFirst(i);
			}
		}
	}
	
	static long getDist(int u, int v) {
		return distance[u] + distance[v] - (distance[LCA(u, v)] << 1);
	}
	
	static int LCA(int u, int v) {
		if(depth[u] < depth[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		for(int diff = depth[u] - depth[v], i = 0; diff != 0; diff >>= 1, i++) {
			if((diff & 1) == 1)
				u = dp[i][u];
		}
		if(u == v)
			return u;

		for(int i = logN; i >= 0; i--) {
			if(dp[i][u] != dp[i][v]) {
				u = dp[i][u];
				v = dp[i][v];
			}
		}
		return dp[0][u];
	}
	
	static long query(int v) {
		long ret = INF;
		for(int i = v; i != N; i = ct[i]) {
			if(minDist[i] != INF)
				ret = Math.min(ret, getDist(i, v) + minDist[i]);
		}
		return ret == INF ? -1L : ret;
	}
}
	
class Node {
    int idx;
    long weight;
    Node next;
    
    Node(int idx, long weight, Node next) {
        this.idx = idx;
        this.weight = weight;
        this.next = next;
    }
}