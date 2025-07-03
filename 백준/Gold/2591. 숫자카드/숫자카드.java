import java.io.*;
import java.util.*;

public class Main {
	static int[] dp;
	static String input;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		input = br.readLine();

		dp = new int[input.length()];
		Arrays.fill(dp, -1);

		System.out.println(recur(0));
	}
	
	static int recur(int idx) {
		if(idx > input.length())
            return 0;
		if(idx == input.length())
            return 1;
		if(dp[idx] != -1)
            return dp[idx];
		if(input.charAt(idx) == '0')
            return 0;

		int sum = 0;
		sum += recur(idx + 1);
		if(idx < input.length() - 1 && (input.charAt(idx) - '0') * 10 + input.charAt(idx + 1) - '0' <= 34)
			sum += recur(idx + 2);
		return dp[idx] = sum;
	}
}