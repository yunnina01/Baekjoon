import java.io.*;
import java.util.*;

public class Main {
	private static final int INF = 1_000_000_001;
	static List<List<Integer>> workList;
	static int[][] capacity, flow, dist;
	static int[] parent;
    static int cnt, res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int size = N + M + 2;
		int source = N + M;
		int sink = N + M + 1;
		
		workList = new ArrayList<>();
		for(int i = 0; i < size; i++) 
			workList.add(new ArrayList<>());
		capacity = new int[size][size];
		dist = new int[size][size];
		flow = new int[size][size];
		
		for(int i = 0; i < N; i++) {
			capacity[source][i] = 1;
			workList.get(source).add(i);
			workList.get(i).add(source);
		}
		
		for(int i = N; i < M + N; i++) {
			capacity[i][sink] = 1;
			workList.get(sink).add(i);
			workList.get(i).add(sink);
		}
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());
			while(cnt-- > 0) {
				int work = Integer.parseInt(st.nextToken()) + N - 1;
				int cost = Integer.parseInt(st.nextToken());
				
				workList.get(i).add(work);
				workList.get(work).add(i);
				
				dist[i][work] = cost;
				dist[work][i] = -cost;
				
				capacity[i][work] = 1;
			}
		}

		networkFlow(source, sink, size);
		System.out.println(cnt + "\n" + res);
	}
	
	static void networkFlow(int S ,int T, int size) {
		while(true) {
			parent = new int[size];
			SPFA(S, size);
			if(parent[T] == -1)
                break;
			
			int minFlow = INF;
			for(int i = T; i != S; i = parent[i])
				minFlow = Math.min(minFlow, capacity[parent[i]][i] - flow[parent[i]][i]);
			for(int i = T; i != S; i = parent[i]) {
				res += minFlow * dist[parent[i]][i];
				flow[parent[i]][i] += minFlow;
				flow[i][parent[i]] -= minFlow;
			}
			cnt++;
		}
	}
	
	static void SPFA(int S, int size) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visit = new boolean[size];
		int[] cost = new int[size];
		Arrays.fill(parent, -1);
		Arrays.fill(cost, INF);
		queue.offer(S);
		visit[S] = true;
		cost[S] = 0;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			visit[now] = false;
			for(int next : workList.get(now)) {
				if(capacity[now][next] - flow[now][next] > 0 && cost[next] > cost[now] + dist[now][next]) {
					cost[next] = cost[now] + dist[now][next];
					parent[next] = now;
					if(!visit[next]) {
						queue.offer(next);
						visit[next] = true;
					}
				}
			}
		}
	}
}