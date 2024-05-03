import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> graph;
	static int[] tree, rangeS, rangeE;
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

		tree = new int[n * 4];
		for(int j = 0; j < m; j++) {
			st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			if(q == 1) {
				int w = Integer.parseInt(st.nextToken());
				update(1, 1, n, rangeS[i], w);
			} else
				sb.append(query(1, 1, n, rangeS[i], rangeE[i]) + "\n");
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
	
	static void update(int node, int start, int end, int num, int diff) {
		if(start <= num && num <= end) {
			tree[node] += diff;
			if(start < end) {
				int mid = (start + end) >> 1;
				update(node * 2, start, mid, num, diff);
				update(node * 2 + 1, mid + 1, end, num, diff);
			}
		}
	}

	static int query(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
            return 0;
        if(left <= start && end <= right)
			return tree[node];
		int mid = (start + end) >> 1;
		return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
	}
}