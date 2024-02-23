import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 10007;
	static int[][] dp = new int[1000][1000];
	static char[] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for(int i = 0; i < 1000; i++)
			Arrays.fill(dp[i], -1);

		S = br.readLine().toCharArray();

		bw.write(countPalindromes(0, S.length - 1) + "\n");
        br.close();
        bw.flush();
        bw.close();
	}

	static int countPalindromes(int left, int right) {
		if(left > right)
        	return 0;
		if(left == right)
			return 1;
        if(dp[left][right] != -1)
			return dp[left][right];
		
		int ret = 0;
		ret += countPalindromes(left + 1, right);
		ret %= MOD;
		
        ret += countPalindromes(left, right - 1);
		ret %= MOD;
		
        ret -= countPalindromes(left + 1, right - 1);
		ret = (ret + MOD) % MOD;
		
		if(S[left] == S[right]) {
			ret += countPalindromes(left + 1, right - 1) + 1;
		    ret %= MOD;
        }
		return dp[left][right] = ret;
	}
}