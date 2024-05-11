import java.io.*;
import java.util.*;

public class Main {
	static final int LOG = 17;
	static List<List<Integer>> adj;
	static int[][] parent;
	static int[] depth;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		adj = new ArrayList<>();
		parent = new int[N][LOG];
		for(int i = 0; i < N; i ++) {
			adj.add(new ArrayList<>());
			Arrays.fill(parent[i], -1);
		}

		for(int i = 1; i < N; i ++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()) - 1;
			int Y = Integer.parseInt(st.nextToken()) - 1;

			adj.get(X).add(Y);
			adj.get(Y).add(X);
		}

		depth = new int[N];
		Arrays.fill(depth, -1);
		depth[0] = 0;
		DFS(0);
		
		for(int i = 0; i < LOG - 1; i ++) {
			for(int k = 1; k < N; k ++) {
				if(parent[k][i] != -1)
					parent[k][i + 1] = parent[parent[k][i]][i];
			}
		}

		int Q = Integer.parseInt(br.readLine());

		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			int res = Integer.MIN_VALUE;
			res = Math.max(res, getCenter(LCA(a,b), a, b, c));
			res = Math.max(res, getCenter(LCA(b, c), b, c, a));
			res = Math.max(res, getCenter(LCA(c, a), c, a, b));

            if(res != -1)
                res ++;
			sb.append(res + "\n");
		}
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
	}

	static void DFS(int x) {
		for(int next : adj.get(x)) {
			if(depth[next] == -1) {
				depth[next] = depth[x] + 1;
				parent[next][0] = x;
				DFS(next);
			}
		}
	}

	static int LCA(int u, int v) {
		if(depth[u] < depth[v]) {
			int tmp = u;
			u = v;
			v = tmp;
		}
		
		int dif = depth[u] - depth[v];
		for(int i = 0; dif != 0; i ++) {
			if(dif % 2 == 1)
                u = parent[u][i];
			dif >>= 1;
		}

		if(u != v) {
			for(int i = LOG - 1; i >= 0; i --) {
				if(parent[u][i] != -1 && parent[u][i] != parent[v][i]) {
					u = parent[u][i];
					v = parent[v][i];
				}
			}
			u = parent[u][0];
		}
		return u;
	}	
	
	static int getCenter(int lca, int u, int v, int k) {
		if(depth[u] < depth[v]) {
			int tmp = u;
			u = v;
			v = tmp;
		}

		int dif = 0;
		if(v == lca)
			dif = depth[u] - depth[v];
		else
		    dif = (depth[v] + depth[u] - 2 * depth[lca]);
		if(dif % 2 == 1)
            return -1;

		dif >>= 1;
		int tmp = dif;
		for(int i = 0; dif != 0; i ++) { 
			if(dif % 2 == 1)
                u = parent[u][i];
			dif /= 2;
		}

		int center = u;
		int lcaK = LCA(center, k);
		if(lcaK == center || lcaK == k) {
			if((Math.max(depth[center], depth[k]) - Math.min(depth[center], depth[k]))  == tmp)
				return center;
			return -1;
		} else {
			if((depth[center] + depth[k] - 2 * depth[lcaK]) == tmp)
				return center;
			return - 1;
		}
	}
}