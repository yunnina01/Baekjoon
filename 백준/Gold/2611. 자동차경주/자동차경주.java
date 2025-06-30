import java.io.*;
import java.util.*;

public class Main {
	static List<List<Edge>> edges;
	static int[] id, score, pre;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine()); 
		int M = Integer.parseInt(br.readLine());

		edges = new ArrayList<>();
		for(int i = 0; i <= N; i++)
			edges.add(new ArrayList<>());
        id = new int[N + 1];

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());

			edges.get(p).add(new Edge(q, r));
			id[q]++;
		}

		score = new int[N + 1];
		pre = new int[N + 1];
		BFS();

		Stack<Integer> stack = new Stack<>();
		stack.add(1);
		int now = pre[1];
		while(true) {
			stack.add(now);
			if(now == 1)
                break;
			now = pre[now];
		}

		int length = stack.size();
		sb.append(score[1] + "\n");
		for(int i = 0; i < length; i++)
			sb.append(stack.pop() + " ");
		System.out.println(sb);
	}
	
	static void BFS() {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(1);
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(Edge edge : edges.get(now)) {
				if(edge.cost + score[now] > score[edge.node]) {
					score[edge.node] = edge.cost + score[now];
					pre[edge.node] = now;
				}
				if(--id[edge.node] == 0) { 
					if(edge.node == 1)
                        return;
					queue.offer(edge.node);
				}
			}
		}
	}
}

class Edge {
	int node, cost;

	Edge(int node, int cost) {
		this.cost = cost;
		this.node = node;
	}
}