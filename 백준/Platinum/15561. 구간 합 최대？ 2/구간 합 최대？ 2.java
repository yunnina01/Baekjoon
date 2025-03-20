import java.io.*;
import java.util.*;

public class Main {
	static Node[] tree;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int U = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
			arr[i] = U * Integer.parseInt(st.nextToken()) + V;

		tree = new Node[4 * N];
		init(1, 1, N);

		while(Q-- > 0) {
			st = new StringTokenizer(br.readLine());
            int C = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

			if(C == 0)
				sb.append((query(1, 1, N, A, B).max - V) + "\n");
			else
				update(1, 1, N, A, U * B + V);
		}
        bw.write(sb.toString());
        bw.flush();
	}
	
	static Node init(int node, int start, int end) {
		if(start == end) {
			tree[node] = new Node();
			tree[node].sum = arr[start];
			tree[node].max = arr[start];
			tree[node].lMax = arr[start];
			tree[node].rMax = arr[start];
			return tree[node];
		}

		int mid = (start + end) >> 1;
		return tree[node] = merge(init(node << 1, start, mid), init(node << 1 | 1, mid + 1, end));
	}
	
	static Node query(int node, int start, int end, int left, int right) {
		if(left <= start && right >= end)
			return tree[node];

		int mid = (start + end) >> 1;
		if(right <= mid)
			return query(node << 1, start, mid, left, right);
		if(mid < left)
			return query(node << 1 | 1, mid + 1, end, left, right);
		return merge(query(node << 1, start, mid, left, right), query(node << 1 | 1, mid + 1, end, left, right));
	}
	
	static Node merge(Node left, Node right) {
		Node result = new Node();
		result.sum = left.sum + right.sum;
		result.max = Math.max(Math.max(left.max, right.max), left.rMax + right.lMax);
		result.lMax = Math.max(left.lMax, left.sum + right.lMax);
		result.rMax = Math.max(right.rMax, right.sum + left.rMax);
		return result;
	}
	
	static void update(int node, int start, int end, int idx, int num) {
		if(start == end) {
			tree[node].sum = num;
			tree[node].max = num;
			tree[node].lMax = num;
			tree[node].rMax = num;
			return;
		}

		int mid = (start + end) >> 1;
		if(idx <= mid)
			update(node << 1, start, mid, idx, num);
		else
			update(node << 1 | 1, mid + 1, end, idx, num);
		merge(tree[node << 1], tree[node << 1 | 1], tree[node]);
	}
	
	static void merge(Node left, Node right, Node dest) {
		dest.sum = left.sum + right.sum;
		dest.max = Math.max(Math.max(left.max, right.max), left.rMax + right.lMax);
		dest.lMax = Math.max(left.lMax, left.sum + right.lMax);
		dest.rMax = Math.max(right.rMax, right.sum + left.rMax);
	}
}

class Node {
    int sum, max, lMax, rMax;
}