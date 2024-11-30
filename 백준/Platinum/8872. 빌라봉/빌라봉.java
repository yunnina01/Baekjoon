import java.io.*;
import java.util.*;

public class Main {
	static List<List<Edge>> edges;
	static List<Integer> nodes;
	static int[] dp, treeIdx;
	static int maxDiameter = Integer.MIN_VALUE;
	static int treeCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();
		for(int i = 0; i < N; i++)
			edges.add(new ArrayList<>());

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int T = Integer.parseInt(st.nextToken());

			edges.get(A).add(new Edge(B, T));
			edges.get(B).add(new Edge(A, T));
		}

		List<Integer> results = new ArrayList<>();
		dp = new int[N];
		treeIdx = new int[N];
		Arrays.fill(treeIdx, -1);
		for(int i = 0; i < N; i++) {
			if(treeIdx[i] == -1) {
				results.add(getRadius(i));
				treeCnt++;
			}
		}

		Collections.sort(results, Collections.reverseOrder());

		int res = maxDiameter;
		if(results.size() > 1) {
			res = Math.max(res, results.get(0) + results.get(1) + L);
			if (results.size() > 2)
				res = Math.max(res, results.get(1) + L + results.get(2) + L);
		}
		System.out.println(res);
	}

	static int getRadius(int node) {
		DFS(node, -1);
		Edge ret = getDis(node, -1, 0);
		maxDiameter = Math.max(maxDiameter, ret.v);
		return ret.t;
	}

	static int DFS(int x, int last) {
		treeIdx[x] = treeCnt;
		for(Edge next : edges.get(x)) {
			if(next.v != last)
				dp[x] = Math.max(dp[x], DFS(next.v, x) + next.t);
		}
		return dp[x];
	}

	static Edge getDis(int x, int last, int sum) {
		Edge edge = null;
		int dis1 = sum;
		int dis2 = -1;

		for(Edge next : edges.get(x)) {
			if(next.v != last) {
				int dist = dp[next.v] + next.t;
				if (dis1 <= dist) {
					dis2 = dis1;
					dis1 = dist;
					edge = next;
				} else if (dist >= dis2)
					dis2 = dist;
			}
		}

		if(edge == null)
			return new Edge(dis1, dis1);
		if(dis1 < Math.max(dis1 - edge.t, dis2 + edge.t))
			return new Edge(dis1 + dis2, dis1);

		Edge ret = getDis(edge.v, x, dis2 + edge.t);
		ret.v = Math.max(ret.v, dis1 + dis2);
		return ret;
	}
}

class Edge {
	int v, t;

	Edge(int v, int t) {
		this.v = v;
		this.t = t;
	}
}