import java.io.*;
import java.util.*;

public class Main {
    static int[] parent, dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		while(true) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			if(N == 0 && M == 0)
				break;

			parent = new int[100001];
			for(int i = 1; i <= N; i++)
				parent[i] = i;
			dist = new int[100001];
			
			while(M-- > 0) {
				st = new StringTokenizer(br.readLine());
				String q = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if(q.charAt(0) == '!') {
					int w = Integer.parseInt(st.nextToken());
					union(a, b, w);
				}
				else {
					if(find(a) == find(b))
						sb.append(dist[b] - dist[a] + "\n");
					else
						sb.append("UNKNOWN\n");
				}
			}
		}
		bw.write(sb.toString());
        br.close();
		bw.flush();
		bw.close();
	}
	
	static void union(int x, int y, int diff) {
		if(find(x) != find(y)) {
			dist[parent[y]] = dist[x] + diff - dist[y];
			parent[parent[y]] = parent[x];
		}
	}

	static int find(int x) {
		if(parent[x] == x)
			return x;
		else {
			int ancestor = find(parent[x]);
			dist[x] += dist[parent[x]];
			parent[x] = ancestor;
			return parent[x];
		}
	}
}