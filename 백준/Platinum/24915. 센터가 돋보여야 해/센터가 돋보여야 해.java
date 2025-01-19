import java.io.*;
import java.util.*;

public class Main {
	static Node[] tree;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		arr = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		tree = new Node[4 * N];
		init(1, 1, N);
		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
				update(1, 1, N, x, y);
			} else {
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
				sb.append(query(1, 1, N, l, r).minMaxMin + "\n");
			}
		}
		System.out.print(sb);
	}
	
	static final void init(int node, int start, int end) {
		if(start == end) {
			tree[node] = new Node(arr[start]);
			return;
		}
		int mid = (start + end) >> 1;
		init(node << 1, start, mid);
		init(node << 1 | 1, mid + 1, end);
		tree[node] = merge(tree[node << 1], tree[node << 1 | 1]);
	}
	
	static final Node merge(Node left, Node right) {
		Node result = new Node();
		result.min = Math.min(left.min, right.min);
		result.max = Math.max(left.max, right.max);
		result.minMax = Math.max(Math.max(left.minMax, right.minMax), right.max - left.min);
		result.maxMin = Math.max(Math.max(left.maxMin, right.maxMin), left.max - right.min);
		result.minMaxMin = Math.max(Math.max(left.minMaxMin, right.minMaxMin), Math.max(left.minMax - right.min, right.maxMin - left.min));
		return result;
	}
	
	static final void merge(Node left, Node right, Node dest) {
		dest.min = Math.min(left.min, right.min);
		dest.max = Math.max(left.max, right.max);
		dest.minMax = Math.max(Math.max(left.minMax, right.minMax), right.max - left.min);
		dest.maxMin = Math.max(Math.max(left.maxMin, right.maxMin), left.max - right.min);
		dest.minMaxMin = Math.max(Math.max(left.minMaxMin, right.minMaxMin), Math.max(left.minMax - right.min, right.maxMin - left.min));
	}
	
	static final void update(int node, int start, int end, int idx, int num) {
		if(start == end) {
			tree[node].min = num;
			tree[node].max = num;
			return;
		}
		int mid = (start + end) >> 1;
		if(idx <= mid)
			update(node << 1, start, mid, idx, num);
		else
			update(node << 1 | 1, mid + 1, end, idx, num);
		merge(tree[node << 1], tree[node << 1 | 1], tree[node]);
	}
	
	static final Node query(int node, int start, int end, int left, int right) {
		if(left <= start && end <= right)
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
    int min, max, minMax, maxMin, minMaxMin;

    Node() {

    }
    
    Node(int num) {
        min = num;
        max = num;
        minMax = Integer.MIN_VALUE >> 2;
        maxMin = Integer.MIN_VALUE >> 2;
        minMaxMin = Integer.MIN_VALUE >> 2;
    }
}