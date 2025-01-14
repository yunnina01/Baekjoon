import java.io.*;
import java.util.*;

public class Main {
	static long[] prefix;
	static int[] tree;
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		prefix = new long[N + 1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
			prefix[i] = prefix[i - 1] + Integer.parseInt(st.nextToken()); 

        size = 2;
		while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}

		tree = new int[size];
		for(int i = 0 ; i < Q ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				int d = Integer.parseInt(st.nextToken());
				if(d == N)
                    continue;
				update(d);
			} else {
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());

				int sum = query(1, 0, size / 2 - 1, 0, e - 1);
				int last = sum == 0 ? s : Math.max(find(1, sum) + 1, s);
				sb.append(prefix[e] - prefix[last - 1] + "\n");
			}
		}
		System.out.print(sb);
	}
    
	static void update(int i) {
		i += size / 2;
		tree[i] = 1;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static int query(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
            return 0;
		if(left <= start && right >= end)
            return tree[node];
		int mid = (start + end) / 2;
		return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
	}
	
	static int find(int node, int val) {
		if(node >= size / 2)
            return node - size / 2;
		if(tree[node * 2] < val)
			return find(node * 2 + 1, val - tree[node * 2]);
		return find(node * 2, val);
	}
}