import java.io.*;
import java.util.*;

public class Main {
	static final int MAX = 16;
	static List<List<Integer>> adj;
	static int[][] parent;
	static int[] depth;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		adj = new ArrayList<>();
		parent = new int[n + 1][MAX];
		for(int i = 0; i <= n; i++) {
			adj.add(new ArrayList<>());
			Arrays.fill(parent[i], -1);
		}

		for(int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adj.get(a).add(b);
			adj.get(b).add(a);		}
        
		depth = new int[n + 1];
		Arrays.fill(depth, -1);
		depth[1] = 0;
		DFS(1);

		for(int i = 0; i < MAX - 1; i++) {
			for(int j = 1; j <= n; j++) {
				if(parent[j][i] != -1)
					parent[j][i + 1] = parent[parent[j][i]][i];
			}
		}

		int m = Integer.parseInt(br.readLine());

		int now = Integer.parseInt(br.readLine());
		int res = 0;
		for(int i = 1; i < m; i++) {
			int next = Integer.parseInt(br.readLine());
			int lca = LCA(now, next);
			res += depth[now] + depth[next] - 2 * depth[lca];
			now = next;
		}
		System.out.println(res);
	}

	static void DFS(int now) {
		for(int next : adj.get(now)) {
			if(depth[next] == -1) {
				depth[next] = depth[now] + 1;
				parent[next][0] = now;
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
		for(int k = 0; dif != 0; k++) {
			if(dif % 2 == 1)
				u = parent[u][k];
			dif /= 2;
		}

		if(u != v) {
			for(int k = MAX - 1; k >= 0; k--) {
				if(parent[u][k] != -1 && parent[u][k] != parent[v][k]) {
					u = parent[u][k];
					v = parent[v][k];
				}
			}
			return parent[u][0];
		}
		return u;
	}
}