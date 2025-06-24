import java.io.*;
import java.util.*;

public class Main {
	static long[] tree;
	static int[] lazy;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st, startst, endst;

        int size = 2;
		while(true) {
			if(size >= 172800) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new long[size];
		lazy = new int[size];

		int N = Integer.parseInt(br.readLine());

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			startst = new StringTokenizer(st.nextToken(), ":");
			st.nextToken();
			endst = new StringTokenizer(st.nextToken(), ":");

			int start = 0, end = 0;
			start += Integer.parseInt(startst.nextToken()) * 3600;
			start += Integer.parseInt(startst.nextToken()) * 60;
			start += Integer.parseInt(startst.nextToken());
			end += Integer.parseInt(endst.nextToken()) * 3600;
			end += Integer.parseInt(endst.nextToken()) * 60;
			end += Integer.parseInt(endst.nextToken());

			if(end < start) {
				update(1, 0, size / 2 - 1, start, 86399);
				update(1, 0, size / 2 - 1, 0, end);
			} else
				update(1, 0, size / 2 - 1, start, end);
		}

		int M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			startst = new StringTokenizer(st.nextToken(), ":");
			st.nextToken();
			endst = new StringTokenizer(st.nextToken(), ":");

			int start = 0, end = 0;
			start += Integer.parseInt(startst.nextToken()) * 3600;
			start += Integer.parseInt(startst.nextToken()) * 60;
			start += Integer.parseInt(startst.nextToken());
			end += Integer.parseInt(endst.nextToken()) * 3600;
			end += Integer.parseInt(endst.nextToken()) * 60;
			end += Integer.parseInt(endst.nextToken());

			double sum = 0;
			int range = 0;
			if(end < start) {
				range = end + 86401 - start;
				sum += query(1, 0, size / 2 - 1, start, 86399);
				sum += query(1, 0, size / 2 - 1, 0, end);
			} else {
				range = end - start + 1;
				sum += query(1, 0, size / 2 - 1, start, end);
			}
			sb.append((sum / range) + "\n");
		}	
		System.out.print(sb);
	}
	
	static void update(int node, int start, int end, int left, int right) {
		propagate(node, start, end);
		if(left > end || right < start)
            return;
		if(left <= start && right >= end) {
			lazy[node] ++;
			propagate(node, start, end);
			return;
		}

		int mid = (end + start) / 2;
		update(node * 2, start, mid, left, right);
		update(node * 2 + 1, mid + 1, end, left, right);
		tree[node] = tree[node * 2] + tree[node * 2 + 1];
	}

	static void propagate(int node, int start, int end) {
		if(lazy[node] != 0) {
			if(start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			tree[node] += (end - start + 1) * lazy[node];
			lazy[node] = 0;
		}
	}
	
	static long query(int node, int start, int end, int left, int right) {
		propagate(node, start, end);
		if(left > end || right < start)
            return 0;
		if(left <= start && right >= end)
            return tree[node];
		int mid = (start + end) / 2;
		return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
	}
}