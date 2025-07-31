import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1000000;
	static int[] dp;
	static String password;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		password = br.readLine();

		dp = new int[password.length()];
		Arrays.fill(dp, -1);

		System.out.println(recur(0));
	}
	
	static int recur(int idx) {
		if(idx > password.length())
            return 0;
		if(idx == password.length())
            return 1;
		if(dp[idx] != -1)
            return dp[idx];
		if(password.charAt(idx) == '0')
            return 0;

		int sum = 0;
		sum += recur(idx + 1);
		sum %= MOD;
		if(idx < password.length() - 1 && (password.charAt(idx) - '0') * 10 + (password.charAt(idx + 1) - '0') <= 26) {
			sum += recur(idx + 2);
			sum %= MOD;
		}
		return dp[idx] = sum;
	}
}