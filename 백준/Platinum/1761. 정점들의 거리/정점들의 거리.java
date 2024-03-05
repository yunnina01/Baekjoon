import java.util.*;
import java.io.*;

public class Main {
	static List<Edge>[] tree;
	static int[][] parent;
	static int[] height, dist;
	static boolean[] visited;
	static int H;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		H = (int)Math.ceil(Math.log(N) / Math.log(2));
		tree = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++)
			tree[i] = new ArrayList<>();
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			tree[u].add(new Edge(v, w));
			tree[v].add(new Edge(u, w));
		}

		parent = new int[N + 1][H + 1];
		visited = new boolean[N + 1];
		height = new int[N + 1];
		dist = new int[N + 1];
		DFS(1, 0, 0);
		for(int h = 1; h <= H; h++)
			for(int v = 1; v <= N; v++)
				parent[v][h] = parent[parent[v][h - 1]][h - 1];

		int M = Integer.parseInt(br.readLine());
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			int r = LCA(u, v);
			bw.write((dist[u] + dist[v] - 2 * dist[r]) + "\n");
		}
        br.close();
        bw.flush();
		bw.close();
	}

	static void DFS(int u, int h, int d) {
		visited[u] = true;
		height[u] = h;

		for(Edge next : tree[u]) {
			if(!visited[next.v]) {
				parent[next.v][0] = u;
				dist[next.v] = d + next.w;
				DFS(next.v, h + 1, dist[next.v]);
			}
		}
	}

	static int LCA(int u, int v) {
		if(height[u] > height[v]) {
			int temp = v;
			v = u;
			u = temp;
		}

		for(int i = H; i >= 0; i--)
			if(height[v] - height[u] >= (1 << i))
				v = parent[v][i];
		if(u == v)
			return u;

		for(int i = H; i >= 0; i--) {
			if(parent[u][i] == parent[v][i])
				continue;
			u = parent[u][i];
			v = parent[v][i];
		}
		return parent[u][0];
	}
}

class Edge {
	int v, w;

	Edge(int v, int w) {
		this.v = v;
		this.w = w;
	}
}