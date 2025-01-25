import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj;
	static List<HashSet<Integer>> dirAdj;
	static long[] tree, cnt;
	static int[] startIdx, endIdx;
	static int size = 2, idx = -1, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		while(size < N << 1)
			size <<= 1;
		tree = new long[size];
		cnt = new long[size];

		adj = new ArrayList<>();
		dirAdj = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			adj.add(new ArrayList<>());
			dirAdj.add(new HashSet<>());
		}

		List<Query> queries = new ArrayList<>();
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			String dirStr = st.nextToken();
			int b = Integer.parseInt(st.nextToken()) - 1;
			if(dirStr.charAt(0) == '<') {
				dirAdj.get(b).add(a);
				queries.add(new Query(b, a));
			} else if(dirStr.charAt(1) == '>') {
				dirAdj.get(a).add(b);
				queries.add(new Query(a, b));
			}
			adj.get(a).add(b);
			adj.get(b).add(a);
		}

		startIdx = new int[N];
		endIdx = new int[N];
		Arrays.fill(startIdx, -1);

		DFS(0);

		for(Query q : queries)
			updateEdge(q.a, q.b, 1);

		int Q = Integer.parseInt(br.readLine());

		while (Q-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int dir = 0;
			String dirStr = st.nextToken();
			if(dirStr.charAt(0) == '<')
				dir = -1;
			else if (dirStr.charAt(1) == '>')
				dir = 1;

			int b = Integer.parseInt(st.nextToken()) - 1;

			if(dir == -1) {
				int tmp = a;
				a = b;
				b = tmp;
				dir = 1;
			}

			if((dir == 1 && (dirAdj.get(a).contains(b))) || (dir == 0 && (!dirAdj.get(a).contains(b) && !dirAdj.get(b).contains(a)))) {
				sb.append(N - cnt[1] + "\n");
				continue;
			}

			if(dirAdj.get(a).contains(b)) {
				updateEdge(a, b, -1);
				dirAdj.get(a).remove(b);
			}
			if(dirAdj.get(b).contains(a)) {
				updateEdge(b, a, -1);
				dirAdj.get(b).remove(a);
			}

			if(dir == 1) {
				updateEdge(a, b, 1);
				dirAdj.get(a).add(b);
			}
			sb.append((N - cnt[1]) + "\n");
		}
		bw.write(sb.toString());
        bw.flush();
	}

	static void DFS(int x) {
		startIdx[x] = ++idx;
		for(int next : adj.get(x)) {
			if(startIdx[next] == -1)
				DFS(next);
		}
		endIdx[x] = idx;
	}

	static void updateEdge(int a, int b, int val) {
		if(startIdx[a] > startIdx[b]) {
			update(1, 0, size / 2 - 1, 0, startIdx[a] - 1, val);
			if(endIdx[a] + 1 <= N - 1)
				update(1, 0, size / 2 - 1, endIdx[a] + 1, N - 1, val);
		} else
			update(1, 0, size / 2 - 1, startIdx[b], endIdx[b], val);
	}

	static void update(int node, int start, int end, int left, int right, int val) {
		if(start > right || end < left)
			return;
		if(start >= left && end <= right) {
			tree[node] += val;
		} else {
			int mid = (start + end) >> 1;
			update(node << 1, start, mid, left, right, val);
			update(node << 1 | 1, mid + 1, end, left, right, val);
		}

		if(tree[node] > 0)
			cnt[node] = end - start + 1;
		else if(start != end)
			cnt[node] = cnt[node << 1] + cnt[node << 1 | 1];
		else
			cnt[node] = 0;
	}
}

class Query {
	int a, b;

	Query(int a, int b) {
		this.a = a;
		this.b = b;
	}
}