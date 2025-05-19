import java.io.*;
import java.util.*;

public class Main {
	static int[][] tree;
	static int[] arr;
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		arr = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		tree = new int[4 * n][];
		init(1, 1, n);
		for(int l = 0; l < m; l++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			sb.append(tree[1][upperBound(i, j, k) - 1] + "\n");
		}
		bw.write(sb.toString());
        bw.flush();
	}
	
	static void init(int node, int start, int end) {
		if(start == end) {
			tree[node] = new int[1];
			tree[node][0] = arr[start];
			return;
		}

		int mid = (start + end) >> 1;
		init(node << 1, start, mid);
		init(node << 1 | 1, mid + 1, end);
		tree[node] = merge(tree[node << 1], tree[node << 1 | 1]);
	}
	
	static int[] merge(int[] arr1, int[] arr2) {
		int size1 = arr1.length;
		int size2 = arr2.length;
		int size = size1 + size2;

		int[] result = new int[size];
		for(int i = 0, i1 = 0, i2 = 0; i < size; i++) {
			if(i1 < size1 && (i2 == size2 || arr1[i1] < arr2[i2]))
				result[i] = arr1[i1++];
			else
				result[i] = arr2[i2++];
		}
		return result;
	}
	
	static int upperBound(int i, int j, int k) {
		int left = 0;
		int right = n;
		while(left < right) {
			int mid = (left + right) >> 1;
			if(query(1, 1, n, i, j, tree[1][mid]) <= k - 1)
				left = mid + 1;
			else
				right = mid;
		}
		return left;
	}
	
	static int lowerBound(int[] arr, int num) {
		int left = 0;
		int right = arr.length;
		while(left < right) {
			int mid = (left + right) >> 1;
			if(arr[mid] < num)
				left = mid + 1;
			else
				right = mid;
		}
		return right;
	}
	
	static int query(int node, int start, int end, int left, int right, int num) {
		if(left > end || right < start)
			return 0;
		if(left <= start && right >= end)
			return lowerBound(tree[node], num);
		int mid = (start + end) >> 1;
		return query(node << 1, start, mid, left, right, num) + query(node << 1 | 1, mid + 1, end, left, right, num);
	}
}