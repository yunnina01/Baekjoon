import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> workList;
	static int[][] capacity, flow, cost;
	static int[] prev;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int len = N + M + 2;
        int src = N + M;
        int sink = N + M + 1;
		
		workList = new ArrayList<>();
		for(int i = 0; i < len; i++)
			workList.add(new ArrayList<>());
		
        flow = new int[len][len];
        capacity = new int[len][len];
        cost = new int[len][len];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			
			workList.get(src).add(i);
			workList.get(i).add(src);
			capacity[src][i] = 1;
			
			while(c-- > 0) {
				int work = Integer.parseInt(st.nextToken()) - 1 + N;
				int value = Integer.parseInt(st.nextToken());
				
				workList.get(i).add(work);
				workList.get(work).add(i);
				
				capacity[i][work] = 1;
				cost[i][work] = value;
				cost[work][i] = -value;
			}
		}
		for(int i = N; i < M + N; i++) {
			capacity[i][sink] = 1;
			workList.get(i).add(sink);
			workList.get(sink).add(i);
		}

		prev = new int[len];
		int maxFlow = 0;
        int res = 0;
		while(true) {
			SPFA(src, len);	
			if(prev[sink] == -1)
                break;
			for(int i = sink; i != src; i = prev[i]) {
				maxFlow += cost[prev[i]][i];
				flow[prev[i]][i] += 1;
				flow[i][prev[i]] -= 1;
			}
			res++;
		}
		System.out.println(res + "\n" + maxFlow);
	}
	
	static void SPFA(int start, int size) {
		Queue<Integer> queue = new LinkedList<>();
		int[] dist = new int[size];
		boolean[] visit = new boolean[size];
		Arrays.fill(prev, -1);
		Arrays.fill(dist, Integer.MIN_VALUE);
		queue.offer(start);
		visit[start] = true;
		dist[start] = 0;
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			visit[now] = false;
			for(int next : workList.get(now)) {
				if(dist[next] < dist[now] + cost[now][next] && capacity[now][next] - flow[now][next] > 0) {
					dist[next] = dist[now] + cost[now][next];
					prev[next] = now;
					if(!visit[next]) {
						queue.offer(next);
						visit[next] = true;
					}
				}
			}
		}
	}
}