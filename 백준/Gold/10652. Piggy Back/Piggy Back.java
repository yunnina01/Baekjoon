import java.io.*;
import java.util.*;

public class Main {
	static final long INF = 1_000_000_000_000_001L;
	static List<List<Integer>> connList;
	static long[][] cost;
	static int[] energy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		connList = new ArrayList<>();
		cost = new long[N + 1][3];
		for(int i = 0; i <= N; i++) {
			connList.add(new ArrayList<>());
			Arrays.fill(cost[i], INF);
		}

		energy = new int[] {B, E, P};
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			connList.get(x).add(y);
			connList.get(y).add(x);
		}

		BFS(0, 1);
		BFS(1, 2);
		BFS(2, N);

		long res = INF;
		for(int i = 1; i <= N; i++) {
			long val = cost[i][0] + cost[i][1] + cost[i][2];
			res = Math.min(res, val);
		}
		System.out.println(res);
	}

	static void BFS(int ind, int x) {
		LinkedList<Integer> queue = new LinkedList<>();
		queue.offer(x);
		cost[x][ind] = 0;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int next : connList.get(now)) {
				if(cost[next][ind] != INF)
					continue;
				cost[next][ind] = cost[now][ind] + energy[ind];
				queue.offer(next);
			}
		}
	}
}