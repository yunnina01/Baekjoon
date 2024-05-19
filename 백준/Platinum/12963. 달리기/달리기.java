import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 1_000_000_007;
	static Edge[] edges;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		edges = new Edge[M];
		long w = 1;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(a, b, w);
			w *= 3;
			w %= MOD;
		}

        parent = new int[N];
		for(int i = 0; i < N; i++)
			parent[i] = i;
		int res = 0;
		for(int i = M - 1; i >= 0; i--) {
			Edge now = edges[i];
			int x = find(now.a);
			int y = find(now.b);
			int e = find(N - 1);
			if(x == e && y == 0 || x == 0 && y == e) {
				res += now.val;
				res%= MOD;
			} else 
				union(now.a, now.b);
		}
		System.out.println(res);
    }

	static int find(int x) {
		if(x == parent[x])
			return x;
		return parent[x] = find(parent[x]);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x < y)
			parent[y] = x;
		else
			parent[x] = y;
	}
}

class Edge {
	int a, b;
	long val;

	Edge(int a, int b, long val) {
		this.a = a;
		this.b = b;
		this.val = val;
	}
}