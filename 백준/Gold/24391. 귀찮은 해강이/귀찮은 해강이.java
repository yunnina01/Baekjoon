import java.io.*;
import java.util.*;

public class Main {
	static int[] parent;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		for(int i = 1; i <= N; i++)
			parent[i] = i;

		int res = 0;
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			if(find(i) != find(j))
                union(i, j);
		}

		st = new StringTokenizer(br.readLine());
		int prev = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N - 1; i++) {
			int A = Integer.parseInt(st.nextToken());
			if(find(prev) != find(A))
                res++;
			prev = A;
		}
		System.out.println(res);
	}
	
	static int find(int x) {
		return parent[x] = parent[x] == x ? x : find(parent[x]);
	}
	
	static void union(int a, int b) {
		int fa = find(a);
		int fb = find(b);
		if(fa < fb)
            parent[fb] = fa;
		else
            parent[fa] = fb;
	}
}
