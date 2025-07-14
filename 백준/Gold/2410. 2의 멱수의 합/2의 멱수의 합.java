import java.io.*;
import java.util.*;

public class Main {
	static int dp[][];
 	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		dp = new int[N + 1][21];
		for(int i = 0; i <= N; i++)
            Arrays.fill(dp[i], -1);

		int k = 0;
		for(int i = 1; i <= N; i *= 2)
            k++;
		System.out.println(recur(N, k));
	}
	
	static int recur(int num, int last) {
		if(num < 0)
            return 0;
		if(dp[num][last] != -1)
            return dp[num][last];
		if(num == 0)
            return dp[num][last] = 1;

		int sum = 0;
		for(int i = last; i >= 0; i--) {
			sum += recur(num - (int)Math.pow(2, i), i);
			sum %= 1000000000;
		}
		return dp[num][last] = sum;
	}
}