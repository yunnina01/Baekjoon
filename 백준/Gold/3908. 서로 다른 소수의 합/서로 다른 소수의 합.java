import java.io.*;
import java.util.*;

public class Main {
	static int dp[][][];
	static boolean[] ch;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		dp = new int[15][1121][1121];
		for(int i = 1; i <= 14; i++) {
			for(int j = 0; j <= 1120; j++)
				Arrays.fill(dp[i][j], -1);
		}

		ch = new boolean[1121];
		ch[0] = ch[1] = true;
		for(int i = 2; i <= Math.sqrt(1120); i++) {
			for(int k = i * i; k <= 1120; k += i)
				ch[k] = true;
		}

		int T = Integer.parseInt(br.readLine());

		while(T -- > 0) {
			st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
			sb.append(recur(n, k, 0) + "\n");
		}
		System.out.print(sb);
	}
	
	static int recur(int n, int k, int last) {
		if(n < 0)
            return 0;
		if(k == 0) {
			if(n == 0)
                return 1;
			return 0;
		}
		if(dp[k][n][last] != -1)
            return dp[k][n][last];

		int sum = 0;
		for(int i = last + 1; i <= n; i++) {
			if(!ch[i])
				sum += recur(n - i, k - 1, i);
		}
		return dp[k][n][last] = sum;
	}
}