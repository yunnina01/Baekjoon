import java.io.*;

public class Main {
	static int[] prefix, tree, lazy;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();

		int len = S.length();
		int[] arr = new int[len + 1];
		prefix = new int[len + 1];
		for(int i = 1; i <= len; i++) {
			arr[i] = (-2 * (S.charAt(i - 1) - '(')) + 1;
			prefix[i] = prefix[i - 1] + arr[i];
		}

		tree = new int[4 * len];
    	lazy = new int[4 * len];
		cnt = 1;
		init(1, 1, len);

		int M = Integer.parseInt(br.readLine());

		int end = prefix[len];
		int res = 0;
		while(M-- > 0) {
			int idx = Integer.parseInt(br.readLine());
			arr[idx] *= -1;
			update(1, 1, len, idx, len, arr[idx] * 2);

			end += arr[idx] * 2;
			if(end == 0 && tree[1] >= 0)
				res++;
		}
        System.out.println(res);
	}
	
	static int init(int node, int start, int end) {
		if(start == end)
			tree[node] = prefix[cnt++];
		else {
			int mid = (start + end) >> 1;
			tree[node] = Math.min(init(node * 2, start, mid), init(node * 2 + 1, mid + 1, end));
		}
		return tree[node];
	}
	
	static void update(int node, int start, int end, int left, int right, int diff) {
		propagate(node, start, end);
		if(left <= start && end <= right) {
			lazy[node] += diff;
			propagate(node, start, end);
		} else if (!(end < left || right < start)) {
			int mid = (start + end) >> 1;
			update(node * 2, start, mid, left, right, diff);
			update(node * 2 + 1, mid + 1, end, left, right, diff);
			tree[node] = Math.min(tree[node * 2], tree[node * 2 + 1]);
		}
	}
	
	static void propagate(int node, int start, int end) {
		if(lazy[node] != 0) {
			tree[node] += lazy[node];
			if(start < end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}
}