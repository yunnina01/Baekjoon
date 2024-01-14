import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());

			int[][] sticker = new int[2][n + 1];
			for(int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1; j <= n; j++)
					sticker[i][j] = Integer.parseInt(st.nextToken());
			}
			
			int[][] dp = new int[n + 1][3];
			for (int i = 1; i <= n; i++) {
				dp[i][0] = sticker[0][i] + Math.max(dp[i - 1][1], dp[i - 1][2]);
				dp[i][1] = sticker[1][i] + Math.max(dp[i - 1][0], dp[i - 1][2]);
				dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]);
			}
			sb.append(Math.max(dp[n][0], Math.max(dp[n][1], dp[n][2])) + "\n");
		}
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
	}
}