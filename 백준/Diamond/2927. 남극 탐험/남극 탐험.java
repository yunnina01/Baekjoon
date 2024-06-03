import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj, child;
	static Query[] queries;
	static int[] cn, cHead, cTail, depth, parent; 
	static int[] first, uf, dfsn, tSize, tree;
	static int C, dcnt, size = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		adj = new ArrayList<>();
		child = new ArrayList<>();
		first = new int[N];
		uf = new int[N];
        st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			adj.add(new ArrayList<>());
			child.add(new ArrayList<>());
			first[i] = Integer.parseInt(st.nextToken());
			uf[i] = i;
		}
		
		int Q = Integer.parseInt(br.readLine());

		queries = new Query[Q];
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			Query query = new Query(st.nextToken(), Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
			if(query.s.equals("bridge")) {
				if(find(query.a) != find(query.b)) {
					union(query.a, query.b);
					adj.get(query.a).add(query.b);
					adj.get(query.b).add(query.a);
				}
			} else if(query.s.equals("penguins"))
                query.b++;
			queries[i] = query;
		}
		
		for(int i = 0; i < N; i++) {
			if(find(0) != find(i)) {
				union(0, i);
				adj.get(0).add(i);
				adj.get(i).add(0);
			}
		}
		
		for(int i = 0; i < N; i++)
			uf[i] = i;
		tSize = new int[N];
		DFS1(0, -1);

		dfsn = new int[N];
		cn = new int[N];
		cHead = new int[N];
		cTail = new int[N];
		depth = new int[N];
		parent = new int[N];
		while(true) {
			if(size >= N) {
				size <<= 1;
				break;
			}
			size <<= 1;
		}
		tree = new int[size];
		
		parent[0] = -1;
		Arrays.fill(cTail, -1);
		Arrays.fill(cHead, -1);
		DFS2(0, -1, 0);
		
		for(int i = 0; i < N; i++)
			tree[dfsn[i] + size / 2] = first[i];
		
		construct();

		for(int i = 0; i < Q; i++) {
			Query query = queries[i];
			if(query.s.equals("bridge")) {
				if(find(query.a) == find(query.b))
					sb.append("no" + "\n");
				else {
					union(query.a, query.b);
					sb.append("yes" + "\n");
				}
			} else if(query.s.equals("penguins"))
				update(dfsn[query.a], query.b);
			else {
				if(find(query.a) != find(query.b))
					sb.append("impossible" + "\n");
				else
					sb.append(getSum(query.a, query.b) + "\n");
			}
		}
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
	}
	
	static int find(int x) {
		return uf[x] = x == uf[x] ? x : find(uf[x]); 
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb)
            uf[fb] = fa;
		else
            uf[fa] = fb;
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
	
	static void update(int i, int val) {
		i += size >> 1;
		tree[i] = val;
		while(i > 1) {
			i >>= 1;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static void construct() {
		for(int i = size / 2 - 1; i > 0; i--)
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
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
					res += getSumT(1, 0, size / 2 - 1, uHead, u);
					u = parent[uHead];
				} else {
					res += getSumT(1, 0, size / 2 - 1, vHead, v);
					v = parent[vHead];
				}
				if(cn[u] == cn[v])
                    break;
			}
		}
		res += getSumT(1, 0, size / 2 - 1, Math.min(u, v), Math.max(u, v));
		return res;
	}
	
	static int getSumT(int node, int start, int end, int left, int right) {
		if(start > right || end < left)
            return 0;
		if(start >= left && end <= right)
            return tree[node];
		int mid = (start + end) >> 1;
		return getSumT(node * 2, start, mid, left, right) + getSumT(node * 2 + 1, mid + 1, end, left, right);
	}
}

class Query{
	String s;
	int a, b;

    Query(String s, int a, int b) {
		this.s = s;
		this.a = a;
		this.b = b;
	}
}