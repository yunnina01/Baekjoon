import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static List<List<Integer>> stations;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		stations = new ArrayList<>();
		for(int i = 0; i < N; i++)
			stations.add(new ArrayList<>());
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int K = Integer.parseInt(st.nextToken());
			while(K-- > 0)
				stations.get(i).add(Integer.parseInt(st.nextToken()));
		}
		int finish = Integer.parseInt(br.readLine());

		System.out.println(transfer(N, finish));
	}

	static int transfer(int cnt, int end) {
		int[] lines = new int[cnt];
		int[] visit = new int[cnt];
		Arrays.fill(lines, INF);
		Arrays.fill(visit, INF);
		
		List<Integer> startLine = getStartLine(cnt, 0);
		for(int station : startLine) {
			if(stations.get(station).contains(end))
                return 0;
			lines[station] = 0;
		}

		Queue<Integer> queue = new LinkedList<>();
		for(int i = 0; i < cnt; i++) {
			if(lines[i] != 0)
                continue;
			visit[i] = 1;
			queue.offer(i);
		}
		
		int min = INF;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int station: stations.get(now)) {
				for(int next = 0; next < cnt; next++) {
					if(stations.get(next).contains(station)) {
						if(visit[next] != INF)
                            continue;
						visit[next] = visit[now] + 1;
						if(stations.get(next).contains(end))
                            min = Math.min(min, visit[next]);
						queue.offer(next);
					}
				}
			}
		}
		return min == INF ? -1 : min - 1;
	}

	static List<Integer> getStartLine(int cnt, int node) {
		List<Integer> ret = new ArrayList<>();
		for(int i = 0; i < cnt; i++) {
			for(int station : stations.get(i)) {
				if(station == node)
                    ret.add(i);
			}
		}
		return ret;
	}
}