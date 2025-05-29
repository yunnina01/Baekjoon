import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N - 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N - 1; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        int num = Integer.parseInt(st.nextToken());
        
        long[][] dp = new long[N - 1][21];
        dp[0][arr[0]] = 1;
		for(int i = 1; i < N - 1; i++) {
			for(int k = 0; k <= 20; k++) {
				if(dp[i - 1][k] != 0) {
					if(k + arr[i] >= 0 && k + arr[i] <= 20)
                        dp[i][k + arr[i]] += dp[i - 1][k];
					if(k - arr[i] >= 0 && k - arr[i] <= 20)
                        dp[i][k - arr[i]] += dp[i - 1][k];
				}
			}
		}
		System.out.println(dp[N - 2][num]);
	}
}