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

		int N = Integer.parseInt(br.readLine());

		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		tree = new Node[4 * N];
		init(1, 1, N);

		int M = Integer.parseInt(br.readLine());

		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
			sb.append(query(1, 1, N, i, j).max + "\n");
		}
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
	}
	
	static final Node init(int node, int start, int end) {
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

	static final Node merge(Node left, Node right) {
		Node result = new Node();
		result.sum = left.sum + right.sum;
		result.max = Math.max(Math.max(left.max, right.max), left.rMax + right.lMax);
		result.lMax = Math.max(left.lMax, left.sum + right.lMax);
		result.rMax = Math.max(right.rMax, right.sum + left.rMax);
		return result;
	}
	
	static final Node query(int node, int start, int end, int left, int right) {
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
    int sum, max, lMax, rMax;
}