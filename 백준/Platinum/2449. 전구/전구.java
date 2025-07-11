import java.io.*;
import java.util.*;

public class Main {
	static int[][] dp;
	static int[] color;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st.nextToken();

		dp = new int[N][N];
		for(int i = 0; i < N; i++)
			Arrays.fill(dp[i], 200);

		color = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			color[i] = Integer.parseInt(st.nextToken());

		for(int i = 0; i < N; i++)
			dp[i][i] = 0;

		for(int i = 0; i < N - 1; i++) {
			int j = i;
			while(++j < N && color[j] == color[j - 1])
				dp[i][j] = 0;
		}
        System.out.println(recur(0, N - 1));
	}
	
	static int recur(int left, int right) {
		if(dp[left][right] != 200)
			return dp[left][right];

		dp[left][right] = 200;
		for(int i = left; i < right; i++) {
			if(color[left] == color[i + 1])
				dp[left][right] = Math.min(dp[left][right], recur(left, i) + recur(i + 1, right));
			else
				dp[left][right] = Math.min(dp[left][right], recur(left, i) + recur(i + 1, right) + 1);
		}
		return dp[left][right];
	}
}