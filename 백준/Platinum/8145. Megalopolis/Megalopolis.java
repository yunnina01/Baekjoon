import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj, child;
	static SegTree seg = new SegTree();
	static int[] tSize, parent, cHead, cTail, dfsn, cn, depth;
    static int C, dcnt;
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		seg.init(N);

		adj = new ArrayList<>();
		child = new ArrayList<>();
		for(int i = 0 ; i < N ; i ++) {
			adj.add(new ArrayList<>());
			child.add(new ArrayList<>());
		}
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			adj.get(Integer.parseInt(st.nextToken()) - 1).add(Integer.parseInt(st.nextToken()) - 1);
		}
		
		tSize = new int[N];
		DFS1(0, -1);
		
		parent = new int[N];
		cHead = new int[N];
		cTail = new int[N];
		dfsn = new int[N];
		depth = new int[N];
		cn = new int[N];

		parent[0] = -1;
		Arrays.fill(cHead, -1);
		Arrays.fill(cTail, -1);		
		DFS2(0, -1, 0);
		
		int M = Integer.parseInt(br.readLine()) + N - 1;

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			if(st.nextToken().equals("W"))
				sb.append(getSum(0, Integer.parseInt(st.nextToken()) - 1) + "\n");
			else {
				st.nextToken();
				seg.update(dfsn[Integer.parseInt(st.nextToken()) - 1]);
			}
		}
		bw.write(sb.toString());
        bw.flush();
	}
	
	static void DFS1(int now, int pre) {
		tSize[now] = 1;
		for(int next : adj.get(now)) {
			DFS1(next, now);
			child.get(now).add(next);
			tSize[now] += tSize[next];
		}
	}
    
	static void DFS2(int now, int pre, int nowDepth) {
		int u = dfsn[now] = dcnt++;
		depth[u] = nowDepth;
		cn[u] = C;
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
			if(tSize[chained] < tSize[next])
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
	
	static int getSum(int u, int v) {
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
	int[] tree;
	int size = 2;
	
	void init(int N) {
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}

		tree = new int[size];
		for(int i = size / 2; i < size; i++)
			tree[i] = 1;
		for(int i = size / 2 - 1; i > 0; i--)
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
	}

	void update(int i) {
		i += size / 2;
		tree[i] = 0;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}

	int getSum(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
            return 0;
		if(left <= start && right >= end)
            return tree[node];
		int mid = (start + end) / 2;
		return getSum(node * 2, start, mid, left, right) + getSum(node * 2 + 1, mid + 1, end, left, right);
	}
}