import java.io.*;

public class Main {
	static final int MOD = 1_000_000;
	static int[] dp, coins;
	static boolean[] visit;
	static int N, total;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		coins = new int[N];
		total = 0;
		for(int i = 0; i < N; i++) {
			coins[i] = Integer.parseInt(br.readLine());
			total += coins[i];
		}

		dp = new int[total + 1];
		visit = new boolean[total + 1];
		int x = knapsack();
        int res = 2 * x - total;
		System.out.println(res + "\n" + dp[x]);
	}

	static int knapsack() {
		dp[0] = 1;
		visit[0] = true;
		for(int coin : coins) {
			for(int j = total; j >= coin; j--) {
				dp[j] += dp[j - coin];
				if(visit[j - coin])
					visit[j] = true;
				dp[j] %= MOD;
			}
		}

		int x = (total + 1) / 2;
		while(x <= total && !visit[x])
			x++;
        return x;
	}
}