import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		boolean[][] dp = new boolean[N + 1][7];
		boolean res = false;
		dp[0][0] = true;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			int A = Integer.parseInt(st.nextToken());

			for(int j = 6; j >= 0; j--) {
				if(!dp[i - 1][j])
					continue;
				dp[i][j] = dp[i - 1][j];
				int na = (A + j) % 7;
				dp[i][na] = true;
			}
			res |= dp[i][4];
		}
		System.out.println(res ? "YES" : "NO");
    }
}