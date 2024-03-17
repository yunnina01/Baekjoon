import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> child = new ArrayList<>();
	static long[] tree, lazy;
	static int[] rangeE, newIdx;
	static int rangeS = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		for(int i = 0; i <= N; i++)
			child.add(new ArrayList<>());

		st = new StringTokenizer(br.readLine());
		st.nextToken();
		for(int i = 2; i <= N; i++)
			child.get(Integer.parseInt(st.nextToken())).add(i);
		
		newIdx = new int[N + 1];
		rangeE = new int[N + 1];
		assignRange(1);
		
		tree = new long[N * 4 + 1];
		lazy = new long[N * 4+ 1];
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
			if(op == 1) {
				int i = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				update(1, 1, N, newIdx[i], rangeE[i], w);
			} else {
				int i = Integer.parseInt(st.nextToken());
				sb.append(query(1, 1, N, newIdx[i]) + "\n");
			}
		}
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
	}

	static int assignRange(int idx) {
		newIdx[idx] = ++rangeS;
		rangeE[idx] = rangeS;
		for(int next : child.get(idx))
			rangeE[idx] = Math.max(rangeE[idx], assignRange(next));
		return rangeE[idx];
	}
	
	static void update(int node, int start, int end, int left, int right, int diff) {
		propagate(node, start, end);
		if(left <= start && end <= right) {
			lazy[node] += diff;
			propagate(node, start, end);
		} else if(!(end < left || right < start)) {
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
	
	static long query(int node, int start, int end, int idx) {
		propagate(node, start, end);
		if(start == end)
			return tree[node];
		else {
			int mid = (start + end) >> 1;
			if(idx <= mid)
				return query(node * 2, start, mid, idx);
			return query(node * 2 + 1, mid + 1, end, idx);
		}
	}
}