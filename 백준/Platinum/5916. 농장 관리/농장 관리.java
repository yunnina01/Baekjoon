import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj, child;
	static SegTree seg;
	static int[] tSize, dfsn, cn, depth, cHead, cTail, parent;
	static int C, dcnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken())
;
		seg = new SegTree();
		seg.init(N);
		
		adj = new ArrayList<>();
		child = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			adj.add(new ArrayList<>());
			child.add(new ArrayList<>());
		}

		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;

			adj.get(u).add(v);
			adj.get(v).add(u);
		}

		tSize = new int[N];
		DFS1(0, -1);

		dfsn = new int[N];
		cn = new int[N];
		depth = new int[N];
		cHead = new int[N];
		cTail = new int[N];
		parent = new int[N];
		
		Arrays.fill(cHead, -1);
		Arrays.fill(cTail, -1);
		parent[0] = -1;
		DFS2(0, -1, 0);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().equals("P")) {
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;
				update(u, v);
			} else {
				int u = Integer.parseInt(st.nextToken()) - 1;
				int v = Integer.parseInt(st.nextToken()) - 1;

				int idx = Math.max(dfsn[u], dfsn[v]);
				sb.append(seg.getSum(1, 0, seg.size / 2 - 1, idx, idx) + "\n");
			}
		}
		System.out.print(sb);
	}
	
	static void DFS1(int now, int pre) {
		tSize[now] = 1;
		for(int next : adj.get(now)) {
			if(next != pre) {
				DFS1(next, now);
				child.get(now).add(next);
				tSize[now] += tSize[next];
			}
		}
	}

	static void DFS2(int now, int pre, int nowDepth) {
		int u = dfsn[now] = dcnt++;
		cn[u] = C;
		depth[u] = nowDepth;
		if(cHead[C] < 0)
            cHead[C] = u;
		cTail[C] = u;
		if(child.get(now).isEmpty()) {
			C++;
			return;
		}
		
		int chained = child.get(now).get(0);
		for(int i = 1; i < child.get(now).size(); i++) {
			int next = child.get(now).get(i);
			if(tSize[next] > tSize[chained])
                chained = next;
		}
		
		DFS2(chained, now, nowDepth + 1);
		for(int next : child.get(now)) {
			if(next != chained)
                DFS2(next, now, nowDepth + 1);
			int v = dfsn[next];
			parent[v] = u;
		}
 	}
	
     static void update(int u, int v) {
        u = dfsn[u];
        v = dfsn[v];
        if(cn[u] != cn[v]) {
            while(true) {
                int uHead = cHead[cn[u]];
                int vHead = cHead[cn[v]];
                if(depth[uHead] > depth[vHead]) {
                    seg.update(1, 0, seg.size / 2 - 1, uHead, u);
                    u = parent[uHead];
                } else {
                    seg.update(1, 0, seg.size / 2 - 1, vHead, v);
                    v = parent[vHead];
                }
                if(cn[u] == cn[v])
                    break;
            }
        }
        seg.update(1, 0, seg.size / 2 - 1, Math.min(u, v) + 1, Math.max(u, v));
    }
	
	static long getSum(int u, int v) {
		int res = 0;
		u = dfsn[u];
		v = dfsn[v];
		
		if(cn[u] != cn[v]) {
			while(true) {
				int uHead = cHead[cn[u]];
				int vHead = cHead[cn[v]];
				if(depth[uHead] > depth[vHead]) {
					res += seg.getSum(1, 0, seg.size / 2 - 1, uHead, u);
					u = parent[uHead];
				} else {
					res += seg.getSum(1, 0, seg.size / 2 - 1, vHead, v);
					v = parent[vHead];
				}
				if(cn[u] == cn[v])
                    break;
			}
		}
		res += seg.getSum(1, 0, seg.size / 2 - 1, Math.min(u, v) + 1, Math.max(u, v));
		return res;
	}
}

class SegTree {
	long[] tree;
	int[] lazy;
	int size = 2;

	void init(int N) {
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		lazy = new int[size];
	}

	void propagate(int node, int start, int end) {
		if(lazy[node] != 0) {
			if(start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			tree[node] += (end - start + 1) * lazy[node];
			lazy[node] = 0;
		}
	}

	void update(int node, int start, int end, int left, int right) {
		propagate(node, start, end);
		if(left > end || right < start)
			return;
		if(left <= start && right >= end) {
			lazy[node]++;
			propagate(node, start, end);
			return;
		}

		int mid = (start + end) / 2;
		update(node * 2, start, mid, left, right);
		update(node * 2 + 1, mid + 1, end, left, right);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	long getSum(int node, int start, int end, int left, int right) {
		propagate(node, start, end);
		if(left > end || right < start)
			return 0;
		if(left <= start && right >= end)
			return tree[node];
		int mid = (start + end) / 2;
		return getSum(node * 2, start, mid, left, right) + getSum(node * 2 + 1, mid + 1, end, left, right);
	}
}