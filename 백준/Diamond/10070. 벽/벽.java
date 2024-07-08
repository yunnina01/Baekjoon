import java.io.*;
import java.util.*;

public class Main {
	static int[] min, max, res;
	static int N, K, size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

        size = 2;
		while(size < N << 1)
			size <<= 1;

		min = new int[size];
		max = new int[size];
		res = new int[size / 2];
		Arrays.fill(min, Integer.MAX_VALUE);

		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int op = Integer.parseInt(st.nextToken());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			update(1, 0, size / 2 - 1, left, right, height, op);
		}

		for(int i = 0; i < N; i++)
			sb.append(query(1, 0, size / 2 - 1, i, i) + "\n");
		bw.write(sb.toString());
        bw.flush();
	}

	static void update(int node, int start, int end, int left, int right, int val, int op) {
		propagate(node, start, end);

		if(left > end || right < start)
			return;
		if(left <= start && right >= end) {
			if(op == 1) {
				if(start == end) {
					max[node] = Math.max(max[node], val);
					min[node] = Math.max(min[node], val);
				} else
					max[node] = val;
			} else {
				if(start == end) {
					max[node] = Math.min(max[node], val);
					min[node] = Math.min(min[node], val);
				} else
					min[node] = val;
			}
			propagate(node, start, end);
			return;
		}

		int mid = (start + end) >> 1;
		update(node << 1, start, mid, left, right, val, op);
		update(node << 1 | 1, mid + 1, end, left, right, val, op);
	}

	static void propagate(int node, int start, int end) {
		if(start != end) {
			if(max[node] != 0) {
				for(int i = node << 1; i <= (node << 1 | 1); i++) {
					max[i] = Math.max(max[i], max[node]);
					min[i] = Math.max(min[i], max[node]);
				}
			}
			if(min[node] != Integer.MAX_VALUE) {
				for(int i = node << 1; i <= (node << 1 | 1); i++) {
					max[i] = Math.min(max[i], min[node]);
					min[i] = Math.min(min[i], min[node]);
				}
			}
			max[node] = 0;
			min[node] = Integer.MAX_VALUE;
		}
	}

	static int query(int node, int start, int end, int left, int right) {
		propagate(node, start, end);

		if(left > end || right < start)
			return 0;
		if(left <= start && right >= end)
			return Math.min(min[node], max[node]);

		int mid = (start + end) >> 1;
		return query(node << 1, start, mid, left, right) + query(node << 1 | 1, mid + 1, end, left, right);
	}
}