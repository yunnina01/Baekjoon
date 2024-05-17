import java.io.*;
import java.util.*;

public class Main {
	static final int MAX = 17;
	static List<List<Integer>> edges;
	static int[][] parent;
	static int[] depth, childs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

        for(int i = 1; i <= T; i++) {
			sb.append("Case #" + i + ":\n");

			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int Q = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken()) - 1;

			edges = new ArrayList<>();
			parent = new int[N][MAX];
			for(int j = 0; j < N; j++) {
				edges.add(new ArrayList<>());
				Arrays.fill(parent[j], -1);
			}

			for (int j = 0; j < N - 1; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()) - 1;
				int b = Integer.parseInt(st.nextToken()) - 1;

				edges.get(a).add(b);
				edges.get(b).add(a);
			}

			childs = new int[N];
			depth = new int[N];
			Arrays.fill(depth, -1);
			depth[R] = 0;
			DFS(R);

			for(int j = 0; j < MAX - 1; j++) {
				for(int k = 0; k < N; k++) {
					if(parent[k][j] != -1)
						parent[k][j + 1] = parent[parent[k][j]][j];
				}
			}

			for(int j = 0; j < Q; j++) {
				st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());

				if(S == 0)
					R = Integer.parseInt(st.nextToken()) - 1;
				else {
					int x = Integer.parseInt(st.nextToken()) - 1;
					if(x == R) {
						sb.append(N + "\n");
						continue;
					}

					int lca = LCA(x, R);
					if(lca == x) {
						int tmp = R;
						int dif = depth[R] - depth[x] - 1;
						for(int k = 0; dif != 0; k++) {
							if((dif & 1) == 1)
								tmp = parent[tmp][k];
							dif >>= 1;
						}
						sb.append(N - childs[tmp] + "\n");

					} else
						sb.append(childs[x] + "\n");
				}
			}
		}
		bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
	}
    
	static int DFS(int x) {
		int cnt = 1;
		for(int next : edges.get(x)) {
			if(depth[next] == -1) {
				depth[next] = depth[x] + 1;
				parent[next][0] = x;
				cnt += DFS(next);
			}
		}
		childs[x] = cnt;
		return cnt;
	}

	static int LCA(int u, int v) {
		if(depth[u] < depth[v]) {
			int tmp = u;
			u = v;
			v = tmp;
		}

		int dif = depth[u] - depth[v];
		for(int i = 0; dif != 0; i++) {
			if((dif & 1) == 1)
				u = parent[u][i];
			dif >>= 1;
		}

		if(u != v) {
			for(int i = MAX - 1; i >= 0; i--) {
				if(parent[u][i] != -1 && parent[u][i] != parent[v][i]) {
					u = parent[u][i];
					v = parent[v][i];
				}
			}
		}
		return u != v ? parent[u][0] : u;
	}
}