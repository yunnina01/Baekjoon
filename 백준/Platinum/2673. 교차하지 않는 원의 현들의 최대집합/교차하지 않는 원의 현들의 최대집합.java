import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] link = new int[101][101];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
			link[a][b] = 1;
			link[b][a] = 1;
		}

        int[][] dp = new int[101][101];
		for(int diff = 1; diff < 100; diff++) {
			for(int start = 1, end = start + diff; end <= 100; start++, end++) {
				for(int mid = start; mid < end; mid++)
					dp[start][end] = Math.max(dp[start][end], dp[start][mid] + dp[mid + 1][end] + link[start][end]);
			}
		}
		System.out.println(dp[1][100]);
	}
}