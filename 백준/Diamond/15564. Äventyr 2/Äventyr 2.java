import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static Node[] adj;
	static int[][] dp;
	static int[] ct, depth, subSize, minDist;
	static boolean[] visit;
	static int logN;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

		adj = new Node[N + 1];
        st = new StringTokenizer(br.readLine());
		for(int i = 2; i <= N; i++) {
			int A = Integer.parseInt(st.nextToken());
			adj[A] = new Node(i, adj[A]);
			adj[i] = new Node(A, adj[i]);
		}

		logN = (int)Math.ceil(Math.log(N) / Math.log(2));
		dp = new int[logN + 1][N + 1];
		depth = new int[N + 1];
		DFS(1, 0);

		for(int i = 1; i <= logN; i++) {
			for(int j = 1; j <= N; j++)
				dp[i][j] = dp[i - 1][dp[i - 1][j]];
		}

		ct = new int[N + 1];
		subSize = new int[N + 1];
		minDist = new int[N + 1];
		visit = new boolean[N + 1];
		buildCentroidTree(1, 0);

		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int v = Integer.parseInt(st.nextToken());
			if(c == '1')
				update(v);
			else
				sb.append(query(v) + "\n");
		}
		bw.write(sb.toString());
        bw.flush();
	}
	
	static void DFS(int now, int parent) {
		dp[0][now] = parent;
		depth[now] = depth[parent] + 1;
		for(Node child = adj[now]; child != null; child = child.next) {
			if(child.idx == parent)
				continue;
			DFS(child.idx, now);
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
			if (subSize[child.idx] > threshold)
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
		for(int i = v; i != 0; i = ct[i])
			minDist[i] = Math.min(minDist[i], getDist(i, v));
	}
	
	static int getDist(int u, int v) {
		return depth[u] + depth[v] - (depth[LCA(u, v)] << 1);
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
	
	static int query(int v) {
		int ret = INF;
		for(int i = v; i != 0; i = ct[i]) {
			if(minDist[i] != INF)
				ret = Math.min(ret, getDist(i, v) + minDist[i]);
		}
		return ret == INF ? -1 : ret;
	}
}

class Node {
    int idx;
    Node next;
    
    Node(int idx, Node next) {
        this.idx = idx;
        this.next = next;
    }
}