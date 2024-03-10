import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj;
	static int[] newIdx, rangeE, depth, tree;
	static int rangeS;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		adj = new ArrayList<>();
		for(int i = 0; i <= N; i++)
			adj.add(new ArrayList<>());
		for(int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			adj.get(x).add(y);
			adj.get(y).add(x);
		}
		
		newIdx = new int[N + 1];
		rangeE = new int[N + 1];
		depth = new int[N + 1];
		assignNewIdx(C, 0);
		
		int Q = Integer.parseInt(br.readLine());

		tree = new int[N * 4 + 1];
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());

			if(q == 1)
				update(1, 1, N, newIdx[A]);
			else
				sb.append(depth[A] * query(1, 1, N, newIdx[A], rangeE[A]) + "\n");
		}
		bw.write(sb.toString());
        br.close();
		bw.flush();
		bw.close();
	}

	static void assignNewIdx(int node, int from) {
		newIdx[node] = ++rangeS;
		rangeE[node] = rangeS;
		depth[node] = depth[from] + 1;
		for(int next : adj.get(node)) {
			if(next != from) {
				assignNewIdx(next, node);
				rangeE[node] = Math.max(rangeE[node], rangeE[next]);
			}
		}
	}

    static void update(int node, int start, int end, int idx) {
		tree[node]++;
		if(start < end) {
			int mid = (start + end) >> 1;
			if(idx <= mid)
				update(node * 2, start, mid, idx);
			else
				update(node * 2 + 1, mid + 1, end, idx);
		}
	}

	static long query(int node, int start, int end, int left, int right) {
		if(left <= start && end <= right)
			return tree[node];
		if(end < left || right < start)
			return 0;
        int mid = (start + end) >> 1;
		return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
	}
}