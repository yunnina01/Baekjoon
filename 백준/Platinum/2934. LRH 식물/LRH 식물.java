import java.io.*;
import java.util.*;

public class Main {
	static int[] arr, lazy;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

        int size = 2;
		while(true) {
			if(size >= 100000) {
				size *= 2;
				break;
			}
			size *= 2;
		}

		arr = new int[size];
		lazy = new int[size];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int L = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());

			int res = 0;
			if(R - L > 1)
				update(1, 0, size / 2 - 1, L + 1, R - 1);

			if(query(1, 0, size / 2 - 1, L, L) != 0) {
				res += arr[size / 2 + L];
				arr[size / 2 + L] = 0;
			}
			if(query(1, 0, size / 2 - 1, R, R) != 0) {
				res += arr[size / 2 + R];
				arr[size / 2 + R] = 0;
			}
			sb.append(res + "\n");
		}  
		System.out.print(sb);
	}
	
	static void update(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
            return;
		if(left <= start && right >= end) {
			lazy[node]++;
			return;
		}
		int mid = (start + end) / 2;
		update(node * 2, start, mid, left, right);
		update(node * 2 + 1, mid + 1, end, left, right);
	}
	
	static int query(int node, int start, int end, int left, int right) {
		propagation(node, start, end);
		if(left > end || right < start)
            return 0;
		if(left <= start && right >= end)
            return arr[node];
		int mid = (start + end) / 2;
		return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
	}
	
	static void propagation(int node, int start, int end) {
		if(lazy[node] != 0) {
			if(start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			} else
				arr[node] += lazy[node];
			lazy[node] = 0;
		}
	}
}