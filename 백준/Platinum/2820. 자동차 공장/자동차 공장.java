import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj;
	static int[] tree, lazy, rangeS, rangeE, salary;
	static int idx;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		adj = new ArrayList<>();
		for(int i = 0; i <= N; i++)
			adj.add(new ArrayList<>());

		salary = new int[N + 1];
		salary[1] = Integer.parseInt(br.readLine());
		for(int i = 2; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			salary[i] = Integer.parseInt(st.nextToken());
			adj.get(Integer.parseInt(st.nextToken())).add(i);
		}

		rangeS = new int[N + 1];
		rangeE = new int[N + 1];
		idx = 0;
		assign(1);

		tree = new int[N * 4];
		lazy = new int[N * 4];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			char q = st.nextToken().charAt(0);
			int a = Integer.parseInt(st.nextToken());
			if(q == 'p') {
				int x = Integer.parseInt(st.nextToken());
				update(1, 1, N, rangeS[a] + 1, rangeE[a], x);
			} else
				sb.append((salary[a] + sum(1, 1, N, rangeS[a], rangeS[a])) + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	static int assign(int node) {
		rangeS[node] = ++idx;
		rangeE[node] = idx;
		for(int child : adj.get(node))
			rangeE[node] = Math.max(rangeE[node], assign(child));
		return rangeE[node];
	}
	
	static void update(int node, int start, int end, int left, int right, int diff) {
		propagate(node, start, end);
		if(left <= start && right >= end) {
			lazy[node] += diff;
			propagate(node, start, end);
		} else if (!(end < left || right < start)) {
			int mid = (start + end) >> 1;
			update(node * 2, start, mid, left, right, diff);
			update(node * 2 + 1, mid + 1, end, left, right, diff);
		}
	}

	static void propagate(int node, int start, int end) {
		if(lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node];
			if(start < end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}

	static int sum(int node, int start, int end, int left, int right) {
		propagate(node, start, end);
        if(left > end || right < start)
            return 0;
		if(left <= start && right >= end)
			return tree[node];
		int mid = (start + end) >> 1;
		return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
	}
}