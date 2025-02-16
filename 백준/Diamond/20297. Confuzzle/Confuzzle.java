import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE >> 1;
	static ArrayDeque<Integer> copy, updated;
	static Node[] adj;
	static int[] c, subSize, minDepth, subDepth;
	static boolean[] visit;
	static int res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		c = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
			c[i] = Integer.parseInt(st.nextToken());

		adj = new Node[N + 1];
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			adj[u] = new Node(v, adj[u]);
			adj[v] = new Node(u, adj[v]);
		}

		copy = new ArrayDeque<>();
		updated = new ArrayDeque<>();
		subSize = new int[N + 1];
		minDepth = new int[N + 1];
		subDepth = new int[N + 1];
		visit = new boolean[N + 1];
		for(int i = 1; i <= N; i++) {
			minDepth[i] = INF;
			subDepth[i] = INF;
		}
		res = INF;
		DNC(1);
		System.out.print(res);
	}
	
	static void DNC(int now) {
		int centroid = getCentroid(now, 0, getSize(now, 0) >> 1);
		visit[centroid] = true;
		while(!updated.isEmpty())
			minDepth[updated.pollFirst()] = INF;
		while(!copy.isEmpty())
			subDepth[copy.pollFirst()] = INF;

		minDepth[c[centroid]] = 0;
		updated.addLast(c[centroid]);
		for(Node child = adj[centroid]; child != null; child = child.next) {
			if(visit[child.idx])
				continue;

			while(!copy.isEmpty()) {
				int num = copy.pollFirst();
				if(subDepth[num] < minDepth[num]) {
					minDepth[num] = subDepth[num];
					updated.addLast(num);
				}
				subDepth[num] = INF;
			}
			DFS(child.idx, centroid, 1);
		}

		for(Node child = adj[centroid]; child != null; child = child.next) {
			if(visit[child.idx])
				continue;
			DNC(child.idx);
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
	
	static void DFS(int now, int parent, int depth) {
		if(depth == res)
			return;
		if(depth < subDepth[c[now]]) {
			subDepth[c[now]] = depth;
			copy.addLast(c[now]);
		}
		res = Math.min(res, minDepth[c[now]] + depth);

		for(Node child = adj[now]; child != null; child = child.next) {
			if(visit[child.idx] || child.idx == parent)
				continue;
			DFS(child.idx, now, depth + 1);
		}
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