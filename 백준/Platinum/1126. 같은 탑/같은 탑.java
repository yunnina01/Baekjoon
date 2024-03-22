import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 500001;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
		
        int N = Integer.parseInt(br.readLine());
		
		int[] height = new int[N + 1];
        st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++)
            height[i] = Integer.parseInt(st.nextToken());
        
        int[][] dp = new int[N + 1][INF];
		Arrays.fill(dp[0], -1);
		dp[0][0] = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < INF; j++) {
				dp[i][j] = dp[i - 1][j];
				if(j - height[i] >= 0 && dp[i - 1][j - height[i]] != -1)
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - height[i]] + height[i]);
				if(height[i] - j >= 0 && dp[i - 1][height[i] - j] != -1)
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][height[i] - j] + j);
				if(j + height[i] < INF && dp[i - 1][j + height[i]] != -1)
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j + height[i]]);
			}
		}
		bw.write((dp[N][0] > 0 ? dp[N][0] : -1) + "\n");
        br.close();
		bw.flush();
        bw.close();
	}
}