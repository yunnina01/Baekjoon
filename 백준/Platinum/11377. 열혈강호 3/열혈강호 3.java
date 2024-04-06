import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> workList;
	static int[] work;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		workList = new ArrayList<>();
        for(int i = 0; i <= 2 * N; i++)
            workList.add(new ArrayList<>());

		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int c = Integer.parseInt(st.nextToken());
			while(c-- > 0) {
				int x = Integer.parseInt(st.nextToken());
				workList.get(i).add(x);
				workList.get(i + N).add(x);
			}
		}

		work = new int[M + 1];
		visit = new boolean[M + 1];
		int cnt = 0;
        int res = 0;
		for(int i = 1; i <= 2 * N; i++) {
			Arrays.fill(visit, false);
			if((i <= N || (i > N && cnt < K)) && dfs(i)) {
				res++;
				if(i > N)
					cnt++;
			}
		}
		System.out.println(res);
	}
    
	static boolean dfs(int x) {
		for(int w : workList.get(x)) {
			if(visit[w])
				continue;
			visit[w] = true;
			if(work[w] == 0 || dfs(work[w])) {
				work[w] = x;
				return true;
			}
		}
		return false;
	}
}