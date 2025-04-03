import java.io.*;
import java.util.*;

public class Main {
	static Node[] tree;
	static int[] A;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());

		A = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		tree = new Node[4 * N];
		init(1, 1, N);

		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			sb.append(getMax(x1, y1, x2, y2) + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	
	static Node init(int node, int start, int end) {
		if(start == end) {
			tree[node] = new Node();
			tree[node].sum = A[start];
			tree[node].max = A[start];
			tree[node].lMax = A[start];
			tree[node].rMax = A[start];
			return tree[node];
		}
		int mid = (start + end) >> 1;
		return tree[node] = merge(init(node << 1, start, mid), init(node << 1 | 1, mid + 1, end));
	}
	
	static Node merge(Node left, Node right) {
		Node result = new Node();
		result.sum = left.sum + right.sum;
		result.max = Math.max(Math.max(left.max, right.max), left.rMax + right.lMax);
		result.lMax = Math.max(left.lMax, left.sum + right.lMax);
		result.rMax = Math.max(right.rMax, right.sum + left.rMax);
		return result;
	}
	
	static long getMax(int x1, int y1, int x2, int y2) {
		if(y1 < x2)
			return query(1, 1, N, x1, y1).rMax + query(1, 1, N, x2, y2).lMax + (y1 + 1 < x2 ? query(1, 1, N, y1 + 1, x2 - 1).sum : 0);

		Node left;
		if(x1 < x2)
			left = query(1, 1, N, x1, x2 - 1);
		else
			left = new Node();

		Node right;
		if(y1 < y2)
			right = query(1, 1, N, y1 + 1, y2);
		else
			right = new Node();

		Node mid = query(1, 1, N, x2, y1);
		return Math.max(mid.max, Math.max(left.rMax + mid.lMax, Math.max(mid.rMax + right.lMax, left.rMax + mid.sum + right.lMax)));
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
}
	
class Node {
	long sum, max, lMax, rMax;
}