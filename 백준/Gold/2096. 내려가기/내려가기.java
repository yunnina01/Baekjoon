import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 10_000_001;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		
		int[][] map = new int[N][3];
		for(int i = 0; i < N; i++)
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		bw.write(downStair(N, map));
        br.close();
        bw.flush();
        bw.close();
	}
	
	static String downStair(int n, int[][] arr) {
		int[][][] dp = new int[n][3][2];
		dp[0][0][0] = dp[0][0][1] = arr[0][0];
		dp[0][1][0] = dp[0][1][1] = arr[0][1];
		dp[0][2][0] = dp[0][2][1] = arr[0][2];
		
		for(int i = 1; i < n; i++) {
			dp[i][0][0] = getMin(dp[i - 1][0][0], dp[i - 1][1][0], INF) + arr[i][0];
			dp[i][1][0] = getMin(dp[i - 1][0][0], dp[i - 1][1][0], dp[i - 1][2][0]) + arr[i][1];
			dp[i][2][0] = getMin(dp[i - 1][1][0], dp[i - 1][2][0], INF) + arr[i][2];
			
			dp[i][0][1] = getMax(dp[i - 1][0][1], dp[i - 1][1][1], 0) + arr[i][0];
			dp[i][1][1] = getMax(dp[i - 1][0][1], dp[i - 1][1][1], dp[i - 1][2][1]) + arr[i][1];
			dp[i][2][1] = getMax(dp[i - 1][1][1], dp[i - 1][2][1], 0) + arr[i][2];
		}
		
		int min = INF, max = 0;
		for(int i = 0; i < 3; i++) {
            min = Math.min(min, dp[n - 1][i][0]);
            max = Math.max(max, dp[n - 1][i][1]);
		}
		return max + " " + min + "\n";
	}
	
	static int getMin(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
	}

    static int getMax(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
}