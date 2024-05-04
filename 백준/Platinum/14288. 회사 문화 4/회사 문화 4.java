import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> graph;
	static int[] rangeS, rangeE, front, back, lazy;
	static int idx;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		graph = new ArrayList<>();
		for(int i = 0; i <= n; i++)
			graph.add(new ArrayList<>());
		st.nextToken();
		for(int i = 2; i <= n; i++)
			graph.get(Integer.parseInt(st.nextToken())).add(i);
		
		rangeS = new int[n + 1];
		rangeE = new int[n + 1];
		euler(1);
		
		front = new int[4 * n];
		back = new int[4 * n];
		lazy = new int[4 * n];
		boolean down = true;
		for(int j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			if(q == 1) {
				int i = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				if(down)
					updateFront(1, 1, n, rangeS[i], rangeE[i], w);
				else
					updateBack(1, 1, n, rangeS[i], w);
			} else if(q == 2) {
				int i = Integer.parseInt(st.nextToken());
				sb.append(queryFront(1, 1, n, rangeS[i]) + queryBack(1, 1, n, rangeS[i], rangeE[i]) + "\n");
			} else
				down ^= true;
		}
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
	}
	
	static int euler(int node) {
		rangeS[node] = ++idx;
		rangeE[node] = idx;
		for(int child : graph.get(node))
			rangeE[node] = Math.max(rangeE[node], euler(child));
		return rangeE[node];
	}

	static void propagate(int node, int start, int end) {
		if(lazy[node] != 0) {
			front[node] += (end - start + 1) * lazy[node];
			if(start < end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}
	
	static void updateFront(int node, int start, int end, int left, int right, int diff) {
		propagate(node, start, end);
        if(left > end || right < start)
            return;
		if(left <= start && end <= right) {
			lazy[node] += diff;
			propagate(node, start, end);
            return;
		}
        int mid = (start + end) >> 1;
		updateFront(node * 2, start, mid, left, right, diff);
		updateFront(node * 2 + 1, mid + 1, end, left, right, diff);
		front[node] = front[node * 2] + front[node * 2 + 1];
	}
	
	static void updateBack(int node, int start, int end, int idx, int diff) {
		if(idx >= start && idx <= end) {
			back[node] += diff;
			if(start < end) {
				int mid = (start + end) / 2;
				updateBack(node * 2, start, mid, idx, diff);
				updateBack(node * 2 + 1, mid + 1, end, idx, diff);
			}
		}
	}
	
	static int queryFront(int node, int start, int end, int idx) {
		propagate(node, start, end);
        if(idx < start || idx > end)
            return 0;
		if(start == idx && idx == end)
			return front[node];
		int mid = (start + end) >> 1;
		return queryFront(node * 2, start, mid, idx) + queryFront(node * 2 + 1, mid + 1, end, idx);
	}
	
	static int queryBack(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
            return 0;
        if(left <= start && end <= right)
			return back[node];
		int mid = (start + end) >> 1;
		return queryBack(node * 2, start, mid, left, right) + queryBack(node * 2 + 1, mid + 1, end, left, right);
	}
}