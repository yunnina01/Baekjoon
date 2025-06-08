import java.io.*;
import java.util.*;

public class Main {
	static int[][][] dp;
 	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

        dp = new int[(int)Math.pow(2, 15) + 1][182][5];
		for(int i = 0; i < (int)Math.pow(2, 15); i++) {
			for(int j = 0; j < 182; j++)
				Arrays.fill(dp[i][j], -1);
		}

		while(true) {
			int now = Integer.parseInt(br.readLine());
			if(now == 0)
                break;

			int sum = 0;
			for(int i = 1; i <= (int)Math.sqrt(now); i++)
				sum += recur(now - i * i, i, 1);
			sb.append(sum + "\n");
		}
		System.out.print(sb);
	}
	
	static int recur(int cur, int last, int num) {
		if(num == 4 && cur > 0)
            return 0;
		if(cur == 0)
            return 1;
		if(dp[cur][last][num] != -1)
            return dp[cur][last][num];

		int sum = 0;
		for(int i = last; i >= 1; i--) {
			if(cur - (i * i) >= 0)
				sum += recur(cur - (i * i), i, num + 1);
		}
		return dp[cur][last][num] = sum;
	}
}