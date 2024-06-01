import java.io.*;
import java.util.*;

public class Main {
	static final int INF = Integer.MAX_VALUE >> 1;
	static ArrayDeque<Integer> copy, updated;
	static Node[] adj;
	static int[] subSize, minDepth, subDepth;
	static boolean[] visit;
	static int K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		adj = new Node[N];
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());

			adj[H] = new Node(T, L, adj[H]);
			adj[T] = new Node(H, L, adj[T]);
		}

		copy = new ArrayDeque<>();
		updated = new ArrayDeque<>();
		subSize = new int[N];
		minDepth = new int[K + 1];
		subDepth = new int[K + 1];
		visit = new boolean[N];
		for(int i = 1; i <= K; i++) {
			minDepth[i] = INF;
			subDepth[i] = INF;
		}
		int res = DNC(0);
		System.out.print(res == INF ? "-1" : res);
	}

	static int DNC(int now) {
		int centroid = getCentroid(now, -1, getSize(now, -1) >> 1);
		visit[centroid] = true;
		while(!updated.isEmpty())
			minDepth[updated.pollFirst()] = INF;
		while(!copy.isEmpty())
			subDepth[copy.pollFirst()] = INF;

		int ret = INF;
		for(Node child = adj[centroid]; child != null; child = child.next) {
			if(visit[child.idx])
				continue;

			while(!copy.isEmpty()) {
				int dist = copy.pollFirst();
				if(subDepth[dist] < minDepth[dist]) {
					minDepth[dist] = subDepth[dist];
					updated.addLast(dist);
				}
				subDepth[dist] = INF;
			}
			ret = Math.min(ret, DFS(child.idx, centroid, child.weight, 1));
		}

		for(Node child = adj[centroid]; child != null; child = child.next) {
			if(visit[child.idx])
				continue;
			ret = Math.min(ret, DNC(child.idx));
		}
		return ret;
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

	static int DFS(int now, int parent, int dist, int depth) {
		if(dist > K)
			return INF;

		int ret = INF;
		if(depth < subDepth[dist]) {
			subDepth[dist] = depth;
			copy.addLast(dist);
			updated.addLast(dist);
		}
		ret = Math.min(ret, minDepth[K - dist] + depth);

		for(Node child = adj[now]; child != null; child = child.next) {
			if(visit[child.idx] || child.idx == parent)
				continue;
			ret = Math.min(ret, DFS(child.idx, now, dist + child.weight, depth + 1));
		}
		return ret;
	}
}

class Node {
    int idx, weight;
    Node next;

    Node(int idx, int weight, Node next) {
        this.idx = idx;
        this.weight = weight;
        this.next = next;
    }
}