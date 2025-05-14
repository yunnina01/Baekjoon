import java.io.*;
import java.util.*;

class Main {	
	static List<List<Point>> adj;
	static long[] sum;
	static int[] childNode;
	static boolean[] visit;
    static long min;
    static int N;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
        StringTokenizer st;

		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) 
				break;
			
            adj = new ArrayList<>();
			for(int i = 0; i < N; i++)
				adj.add(new ArrayList<>());
			
			for(int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());

                adj.get(a).add(new Point(b, w));
				adj.get(b).add(new Point(a, w));
			}

            sum = new long[N];
            childNode = new int[N];
			visit = new boolean[N];
			visit[0] = true;
			init(0);

			visit = new boolean[N];
			visit[0] = true;
            min = Long.MAX_VALUE;
			DFS(0, sum[0]);
			sb.append(min + "\n");
		}
		System.out.print(sb);
	}

	static void init(int now) {
		childNode[now] = 1;
		for(Point next : adj.get(now)) {
			if(!visit[next.node]) {
				visit[next.node] = true;
				init(next.node);
				childNode[now] += childNode[next.node];
				sum[now] += sum[next.node] + childNode[next.node] * next.dist;
			}
		}
	}

	static void DFS(int now, long total) {
		if(min > total)
			min = total;
		for(Point next : adj.get(now)) {
			if(!visit[next.node]) {
				visit[next.node]= true; 
				DFS(next.node, total - (childNode[next.node] * next.dist) + (N - childNode[next.node]) * next.dist);
			}
		}
	}
}


class Point {
	int node, dist;

	Point(int node, int dist) {
        this.node = node;
        this.dist = dist;
    }
}