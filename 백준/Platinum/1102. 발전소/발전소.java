import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 1_023_525_232;
	static int[][] dp, cost;
	static int N, P;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		
        dp = new int[N + 1][1 << 16];
		for(int i = 0; i <= N; i++)
			Arrays.fill(dp[i], -1);
		cost = new int[N][N];
		for(int i = 0; i < N; i++)
			cost[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		String status = br.readLine();
		P = Integer.parseInt(br.readLine());
		
		int cnt = 0;
		int bit = 0;
		for(int i = 0; i < N; i++) {
			if(status.charAt(i) == 'Y') {
				bit = bit | (1 << i);
				cnt++;
			}
		}
		int res = DFS(cnt, bit);
		bw.write((res != INF ? res : -1) + "\n");		
        bw.flush();
	}
	
	static int DFS(int cnt, int bit) {
		if(cnt >= P)
            return 0;
		if(dp[cnt][bit] != -1)
            return dp[cnt][bit];
		
		dp[cnt][bit] = INF;
		for(int i = 0; i < N; i++) {
			if((bit & (1 << i)) == (1 << i)) {
				for(int j = 0; j < N; j++) {
					if(i == j || (bit & (1 << j)) == (1 << j))
                        continue;
					dp[cnt][bit] = Math.min(dp[cnt][bit], DFS(cnt + 1, bit | (1 << j)) + cost[i][j]);
				}
			}
		}
		return dp[cnt][bit];
	}
}