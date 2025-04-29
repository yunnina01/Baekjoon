import java.io.*;
import java.util.*;

public class Main {
	static final int MAX_TIME = 100;
	static List<List<Integer>> adj;
	static int[] matched;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            adj = new ArrayList<>();
            for(int i = 0; i <= MAX_TIME; i++)
                adj.add(new ArrayList<>());

            boolean[][] inAdj = new boolean[MAX_TIME + 1][n];
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int t1 = Integer.parseInt(st.nextToken());
                int t2 = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                for(int j = 0; j < a; j++) {
                    int q = Integer.parseInt(st.nextToken()) - 1;
                    for(int k = t1 + 1; k <= t2; k++) {
                        if(!inAdj[k][q]) {
                            adj.get(k).add(q);
                            inAdj[k][q] = true;
                        }
                    }
                }
            }

            matched = new int[n];
            visit = new boolean[MAX_TIME + 1];
            int cnt = 0;
            int res = -1;
            Arrays.fill(matched, -1);
            for(int i = 1; i <= MAX_TIME; i++) {
                Arrays.fill(visit, false);
                if(DFS(i) && ++cnt == n) {
                    res = i;
                    break;
                }
            }
            sb.append(res + "\n");
		}
		bw.write(sb.toString());
        bw.flush();
	}

	static boolean DFS(int time) {
		if(visit[time])
			return false;
		visit[time] = true;
		for(int piece : adj.get(time)) {
			if(matched[piece] == -1 || DFS(matched[piece])) {
				matched[piece] = time;
				return true;
			}
		}
		return false;
	}
}