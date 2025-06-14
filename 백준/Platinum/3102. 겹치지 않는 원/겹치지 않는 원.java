import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

        int[][] link = new int[301][301];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int C = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
			link[100 + C - R][100 + C + R] = 1;
		}

        int[][] dp = new int[301][301];
		for(int diff = 1; diff < 300; diff++) {
			for(int start = 1, end = start + diff; end <= 300; start++, end++) {
				for(int mid = start + 1; mid < end; mid++)
					dp[start][end] = Math.max(dp[start][end], dp[start][mid] + dp[mid][end] + link[start][end]);
			}
		}
		System.out.println(N - dp[1][300]);
	}
}