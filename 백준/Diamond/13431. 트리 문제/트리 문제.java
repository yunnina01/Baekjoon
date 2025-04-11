import java.io.*;
import java.util.*;

public class Main {
	static Node[] adj;
	static int[][] dp;
	static long[] distance;
	static int[] ct, depth, subSize;
	static boolean[] blue, visit;
	static Result[] results;
	static int N, logN;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

		adj = new Node[N + 1];
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			long w = Long.parseLong(st.nextToken());

			adj[u] = new Node(v, w, adj[u]);
			adj[v] = new Node(u, w, adj[v]);
		}

		logN = (int)Math.ceil(Math.log(N) / Math.log(2.0));
		dp = new int[logN + 1][N + 1];
		depth = new int[N + 1];
		distance = new long[N + 1];
		DFS(0, N, 0L);

		for(int i = 1; i <= logN; i++) {
			for(int j = 0; j < N; j++)
				dp[i][j] = dp[i - 1][dp[i - 1][j]];
		}

		ct = new int[N];
		subSize = new int[N];
		visit = new boolean[N];
		buildCentroidTree(0, N);

		blue = new boolean[N];
		results = new Result[N];
		for(int i = 0; i < N; i++)
			results[i] = new Result();

		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().charAt(0) == '1')
				update(Integer.parseInt(st.nextToken()));
			else
				sb.append(query(Integer.parseInt(st.nextToken())) + "\n");
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
        if(blue[v])
			return;

        blue[v] = true;
		results[v].add(v, 0L);
		int i, prev;
        for(prev = v, i = ct[v]; i != N; prev = i, i = ct[i])
			results[i].add(prev, getDist(i, v));
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
		long ret = 0L;
        int i, prev;
		for(prev = N, i = v; i != N; prev = i, i = ct[i]) {
			long dist = getDist(i, v);
			ret += results[i].calc(prev, dist);
		}
		return ret;
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

class Result {
    int cnt;
    long sum;
    HashMap<Integer, Integer> cnts;
    HashMap<Integer, Long> sums;
    
    Result() {
        cnts = new HashMap<>();
        sums = new HashMap<>();
    }
    
    void add(int idx, long dist) {
        cnt++;
        sum += dist;
        cnts.put(idx, cnts.getOrDefault(idx, 0) + 1);
        sums.put(idx, sums.getOrDefault(idx, 0L) + dist);
    }
    
    long calc(int idx, long dist) {
        return (cnt - cnts.getOrDefault(idx, 0)) * dist + sum - sums.getOrDefault(idx, 0L);
    }
}