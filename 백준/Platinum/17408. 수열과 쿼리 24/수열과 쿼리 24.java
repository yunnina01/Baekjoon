import java.io.*;
import java.util.*;

public class Main {
	static int[][] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
        int N = Integer.parseInt(br.readLine());

		tree = new int[4 * N][2];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
			update(1, 1, N, i, Integer.parseInt(st.nextToken()));
		
		int M = Integer.parseInt(br.readLine());

		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			if(q == 1) {
				int i = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				update(1, 1, N, i, v);
			} else {
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());

				int[] res = query(1, 1, N, l, r);
				sb.append(res[0] + res[1] + "\n");
			}
		}
        bw.write(sb.toString());
		bw.flush();
	}

	static void update(int node, int start, int end, int idx, int val) {
		if(start == end)
			tree[node][0] = val;
		else {
			int mid = (start + end) >> 1;
			if(idx <= mid)
				update(node * 2, start, mid, idx, val);
			else
				update(node * 2 + 1, mid + 1, end, idx, val);
			tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
		}
	}
	
	static int[] query(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
			return new int[] {0, 0};
		if(left <= start && right >= end)
			return tree[node];
        int mid = (start + end) >> 1;
		return merge(query(node * 2, start, mid, left, right), query(node * 2 + 1, mid + 1, end, left, right));
	}

	static int[] merge(int[] arr1, int[] arr2) {
		int[] merged = new int[4];
		merged[0] = arr1[0];
		merged[1] = arr1[1];
		merged[2] = arr2[0];
		merged[3] = arr2[1];
		Arrays.sort(merged);
		return new int[] {merged[3], merged[2]};
	}
}