import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> graph;
	static int[] order;
	static int cnt, idx;
	static boolean[] res;
	
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
		res = new boolean[V + 1];
		cnt = 0;
		idx = 1;
		for(int i = 1; i <= V; i++) {
			if(order[i] == 0)
				DFS(i, true);
		}
		sb.append(cnt + "\n");

		for(int i = 1; i <= V; i++) {
			if(res[i])
                sb.append(i + " ");
		}
        bw.write(sb + "\n");
        br.close();
		bw.flush();
        bw.close();
	}

	static int DFS(int now, boolean start){
		int min = order[now] = idx++;
        int child = 0;
		for(int next : graph.get(now)) {
			if(order[next] == 0) {
				child++;
				int nextMin = DFS(next, false);
				min = Math.min(min, nextMin);
				if(!start && nextMin >= order[now] && !res[now]) {
					res[now] = true;
					cnt++;
				}
			} else
				min = Math.min(min,order[next]);
		}
		if(start && !res[now] && child > 1) {
			res[now] = true;
			cnt++;
		}
		return min;
	}
}