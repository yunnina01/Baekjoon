import java.io.*;
import java.util.*;

public class Main {
	static int[][][] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		dp = new int[101][101][2];
		for(int i = 0; i <= 100; i++) {
			for(int j = 0; j <= 100; j++)
				Arrays.fill(dp[i][j], -1);
		}

		int T = Integer.parseInt(br.readLine());

		while(T -- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			sb.append((recur(n, k, 0) + recur(n, k, 1)) + "\n");
		}
		System.out.print(sb);
	}
	
	static int recur(int num, int k, int last) {
		if(k < 0)
            return 0;
		if(dp[num][k][last] != -1)
            return dp[num][k][last];  
		if(num == 1) {
			if(k == 0)
                return dp[num][k][last] = 1;
			return dp[num][k][last] = 0;
		}

		int sum = 0;
		sum += recur(num - 1, k, 0);
		if(last == 1)
			sum += recur(num - 1, k - 1, 1);
		else
			sum += recur(num - 1, k, 1);
		return dp[num][k][last] = sum; 
	}
}