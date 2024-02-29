import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> graph;
	static int[] order;
	static int idx;
	static List<Edge> res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
	    StringTokenizer st;

	    st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for(int i = 0; i <= V; i++)
            graph.add(new ArrayList<>());
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
			graph.get(A).add(B);
			graph.get(B).add(A);
		}
		
		order = new int[V + 1];
		res = new ArrayList<>();
        idx = 1;
		for(int i = 1; i <= V; i++) {
			if(order[i] == 0)
				DFS(i, 0);
		}
		sb.append(res.size() + "\n");
		
        Collections.sort(res, (o1, o2) -> {
            if(o1.a != o2.a)
                return o1.a - o2.a;
            return o1.b - o2.b;
        });
		for(Edge edge : res)
			sb.append(edge.a + " " + edge.b + "\n");
		bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
	}

	static int DFS(int now, int parent){
		int min = order[now] = idx++;
		for(int next : graph.get(now)) {
			if(order[next] == 0) {
				int nextMin = DFS(next, now);
				min = Math.min(min, nextMin);
				if(nextMin > order[now])
					res.add(new Edge(Math.min(now, next), Math.max(now, next)));
			} else if(next != parent)
				min = Math.min(min, order[next]);
		}
		return min;
	}
}

class Edge {
    int a, b;

    Edge(int a, int b) {
        this.a = a;
        this.b = b;
    }
}