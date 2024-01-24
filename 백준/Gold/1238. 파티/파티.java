import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		final int INF = 100_000_000;
		
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
		List<List<Road>> roads = new ArrayList<>();
		List<List<Road>> revRoads = new ArrayList<>();
		for(int i = 0; i <= N; i++) {
			roads.add(new ArrayList<>());
			revRoads.add(new ArrayList<>());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			roads.get(s).add(new Road(e, t));
			revRoads.get(e).add(new Road(s, t));
		}
		
		int[] cost1 = new int[N + 1];
		int[] cost2 = new int[N + 1];
		Arrays.fill(cost1, INF);
		Arrays.fill(cost2, INF);
		cost1[X] = 0;
		cost2[X] = 0;
		
		PriorityQueue<Road> pq = new PriorityQueue<>();
		pq.offer(new Road(X, 0));
		while(!pq.isEmpty()) {
			Road now = pq.poll();
			if(now.t != cost1[now.v])
                continue;
			for(Road next : roads.get(now.v)) {
                int time = now.t + next.t;
				if(time >= cost1[next.v])
                    continue;
				pq.offer(new Road(next.v, time));
				cost1[next.v] = time;
			}
		}
		
		pq.offer(new Road(X, 0));
		while(!pq.isEmpty()) {
			Road now = pq.poll();
			if(now.t != cost2[now.v])
                continue;
			for(Road next : revRoads.get(now.v)) {
				int time = now.t + next.t;
                if(time >= cost2[next.v])
                    continue;
				pq.offer(new Road(next.v, time));
				cost2[next.v] = now.t + next.t;
			}
		}
		
		int res = 0;
		for(int i = 1; i <= N; i++) {
			if(cost1[i] + cost2[i] > res)
                res = cost1[i] + cost2[i];
		}
		bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
	}
}

class Road implements Comparable<Road> {
    int v, t;

    Road(int v, int t) {
        this.v = v;
        this.t = t;
    }

    @Override
    public int compareTo(Road o) {
        return this.t != o.t ? this.t - o.t : this.v - o.v;
    }
}