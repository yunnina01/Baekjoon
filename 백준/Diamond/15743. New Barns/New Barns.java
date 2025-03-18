import java.io.*;
import java.util.*;

public class Main {
	static Edge[] adj;
	static int[][] dp;
	static int[] ct, max1, max2, maxFrom, subSize, distance;
	static boolean[] visit;
	static int logN;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int Q = Integer.parseInt(br.readLine());

		List<Integer> roots = new ArrayList<>();
		adj = new Edge[100_001];
		int[][] lines = new int[Q][2];
		int size = 0;
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			lines[i][0] = st.nextToken().charAt(0);
			if(lines[i][0] == 'B') {
				int idx = Integer.parseInt(st.nextToken());
				lines[i][1] = ++size;
				if(idx == -1)
					roots.add(size);
				else {
					adj[idx] = new Edge(size, adj[idx]);
					adj[size] = new Edge(idx, adj[size]);
				}
			} else
				lines[i][1] = Integer.parseInt(st.nextToken());
		}

		logN = (int)Math.ceil(Math.log(size) / Math.log(2));
		dp = new int[logN + 1][size + 1];
		distance = new int[size + 1];
		for(int root : roots)
			DFS(root, 0);

		for(int i = 1; i <= logN; i++) {
			for(int j = 1; j <= size; j++)
				dp[i][j] = dp[i - 1][dp[i - 1][j]];
		}

		ct = new int[size + 1];
		subSize = new int[size + 1];
		visit = new boolean[size + 1];
		for(int root : roots)
			buildCentroidTree(root, -1);

		max1 = new int[size + 1];
		max2 = new int[size + 1];
		maxFrom = new int[size + 1];
		for(int i = 0; i <= size; i++) {
			max1[i] = -1;
			max2[i] = -1;
        }

		for(int[] line : lines) {
			if(line[0] == 'B')
				update(line[1]);
			else
				sb.append(query(line[1]) + "\n");
		}
		bw.write(sb.toString());
        bw.flush();
	}
	
	static void DFS(int now, int parent) {
		dp[0][now] = parent;
		distance[now] = distance[parent] + 1;
		for(Edge edge = adj[now]; edge != null; edge = edge.next) {
			if(edge.to == parent)
				continue;
			DFS(edge.to, now);
		}
	}
	
	static void buildCentroidTree(int now, int parent) {
		int centroid = getCentroid(now, -1, getSize(now, -1) >> 1);
		ct[centroid] = parent;
		visit[centroid] = true;
		for(Edge edge = adj[centroid]; edge != null; edge = edge.next) {
			if(visit[edge.to])
				continue;
			buildCentroidTree(edge.to, centroid);
		}
	}
	
	static int getCentroid(int now, int parent, int thr) {
		for(Edge edge = adj[now]; edge != null; edge = edge.next) {
			if(visit[edge.to] || edge.to == parent)
				continue;
			if (subSize[edge.to] > thr)
				return getCentroid(edge.to, now, thr);
		}
		return now;
	}
	
	static int getSize(int now, int parent) {
		subSize[now] = 1;
		for(Edge edge = adj[now]; edge != null; edge = edge.next) {
			if(visit[edge.to] || edge.to == parent)
				continue;
			subSize[now] += getSize(edge.to, now);
		}
		return subSize[now];
	}
	
	static void update(int v) {
		int i, prev;
		for(prev = v, i = v; i != -1; prev = i, i = ct[i]) {
			int dist = getDist(i, v);
			if(prev == maxFrom[i]) {
				if(dist > max1[i])
					max1[i] = dist;
			} else if (dist > max1[i]) {
				max2[i] = max1[i];
				max1[i] = dist;
				maxFrom[i] = prev;
			} else if (dist > max2[i])
				max2[i] = dist;
		}
	}
	
	static int getDist(int u, int v) {
		return distance[u] + distance[v] - (distance[LCA(u, v)] << 1);
	}
	
	static int LCA(int u, int v) {
		if(distance[u] < distance[v]) {
			int temp = u;
			u = v;
			v = temp;
		}
		for(int diff = distance[u] - distance[v], i = 0; diff != 0; diff >>= 1, i++) {
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
		int ret = 0, i, prev;
		for(prev = v, i = v; i != -1; prev = i, i = ct[i]) {
			int dist = getDist(i, v);
			if(prev == maxFrom[i]) {
				if(max2[i] != -1)
					ret = Math.max(ret, dist + max2[i]);
			} else {
				if(max1[i] != -1)
					ret = Math.max(ret, dist + max1[i]);
			}
		}
		return ret;
	}
}
	
class Edge {
    int to;
    Edge next;
    
    Edge(int to, Edge next) {
        this.to = to;
        this.next = next;
    }
}