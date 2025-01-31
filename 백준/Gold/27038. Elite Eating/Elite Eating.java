import java.io.*;
import java.util.*;

public class Main {
	static int[][] dp;
	static int N, S, sqrtS;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());

		dp = new int[N + 1][S];
		sqrtS = (int) Math.sqrt(S);
		long res = getCnt();
		System.out.println(res);
	}

	static long getCnt() {
		dp[0][0] = 1;
		for (int i = 1; i <= sqrtS; i++) {
			int v = i * i;
			for (int j = N; j > 0; j--) {
				for (int k = S - 1; k >= v; k--)
					dp[j][k] += dp[j - 1][k - v];
			}
		}

		long res = 0;
		for (int i = 0; i < S; i++)
			res += dp[N][i];
		return res;
	}
}