import java.io.*;

public class Main {
	static final int LIMIT = 90;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		long K = Long.parseLong(br.readLine());

		long[][] dp = new long[2][LIMIT + 1];
		long[] sum = new long[LIMIT + 1];

		dp[1][1] = 1;
		sum[1] = 1;
		for(int i = 2; i <= LIMIT; i++) {
			dp[0][i] = dp[0][i - 1] + dp[1][i - 1];
			dp[1][i] = dp[0][i - 1];

			sum[i] = dp[0][i] + dp[1][i] + sum[i - 1];
		}

		int idx = -1;
		for(int i = 1; i <= LIMIT; i++) {
			if(K > sum[i])
				continue;
			idx = i;
			break;
		}

		while(idx-- > 0) {
			if(K > sum[idx]) {
				sb.append(1);
				K -= (sum[idx] + 1);
			} else
				sb.append(0);
		}
		System.out.println(sb.toString());
	}
}