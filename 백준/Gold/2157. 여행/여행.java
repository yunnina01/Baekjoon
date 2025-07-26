import java.io.*;
import java.util.*;

public class Main {
	static int[][] dp, adj;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		dp = new int[N + 1][M + 1];
		adj = new int[N + 1][N + 1];
		for(int i = 0; i <= N; i++) 
			Arrays.fill(dp[i], -1);

		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if(a > b)
                continue;
			adj[a][b] = Math.max(adj[a][b], c);
		}
		System.out.println(recur(1, 1));
	}
	
	static int recur(int now, int cnt) {
		if(cnt > M)
            return Integer.MIN_VALUE;
		if(now == N)
            return 0;
		if(dp[now][cnt] != -1)
            return dp[now][cnt];

		int sum = Integer.MIN_VALUE;
		for(int i = now + 1; i <= N; i++) {
			if(adj[now][i] != 0)
				sum = Math.max(sum, recur(i, cnt + 1) + adj[now][i]);
		}
		return dp[now][cnt] = sum;
	}
}