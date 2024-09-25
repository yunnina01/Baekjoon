import java.io.*;
import java.util.*;

public class Main {
	static List<List<Edge>> adj;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

        adj = new ArrayList<>();
		for(int i = 0; i < 52; i++)
			adj.add(new ArrayList<>());

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = toI(st.nextToken().charAt(0));
			int b = toI(st.nextToken().charAt(0));
			int F = Integer.parseInt(st.nextToken());

			Edge edge1 = new Edge(b, F);
			Edge edge2 = new Edge(a, F);
			edge1.dual = edge2;
			edge2.dual = edge1;
			adj.get(a).add(edge1);
			adj.get(b).add(edge2);
		}

		int start = toI('A');
		int end = toI('Z');
		int res = 0;
		while(true) {
			Queue<Integer> queue = new LinkedList<>();
			Edge[] path = new Edge[52];
			int[] prev = new int[52];
			Arrays.fill(prev, -1);
			queue.offer(start);
			while(!queue.isEmpty() && prev[end] == -1) {
				int now = queue.poll();
				for(Edge edge : adj.get(now)) {
					int next = edge.to;
					if(edge.spare() > 0 && prev[next] == -1) {
						queue.offer(next);
						prev[next] = now;
						path[next] = edge;
						if(next == end)
                            break;
					}
				}
			}
			if(prev[end] == -1)
                break;
			
			int flow = Integer.MAX_VALUE;
			for(int i = end; i != start; i = prev[i])
				flow = Math.min(flow, path[i].spare());
			for(int i = end; i != start; i = prev[i])
				path[i].addFlow(flow);
			res += flow;
		}
		System.out.println(res);
	}
	
	static int toI(char x) {
		if(x > 'Z')
            return x - 'a' + 26;
		return x - 'A';
	}
}

class Edge {
	Edge dual;
	int to, cost, flow = 0;

    Edge(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	int spare() {
		return cost - flow;
	}

    void addFlow(int flow) {
		this.flow += flow;
		dual.flow -= flow;
	}
}