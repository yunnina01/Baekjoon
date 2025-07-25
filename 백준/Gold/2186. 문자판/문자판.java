import java.io.*;
import java.util.*;

public class Main {
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, -1, 0, 1};
	static int[][][] dp;
	static char[][] map;
	static boolean[][] visit;
	static String word;
	static int N, M, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		visit = new boolean[N][M];
		for(int i = 0; i < N; i ++) {
			String input = br.readLine();
			for(int j = 0; j < M; j ++)
				map[i][j] = input.charAt(j);
		}
		word = br.readLine();

		dp = new int[N][M][word.length()];
		for(int i = 0; i < N; i ++) {
			for(int j = 0; j < M; j ++)
				Arrays.fill(dp[i][j], -1);
		}

		int res = 0;
		for(int i = 0; i < N; i ++) {
			for(int j = 0; j < M; j ++) {
				if(map[i][j] == word.charAt(0))
					res += recur(i, j, 0);
			}
		}
		System.out.println(res);
	}
	
	static int recur(int y, int x, int idx) {
		if(dp[y][x][idx] != -1)
            return dp[y][x][idx];
		if(idx == word.length() - 1)
            return dp[y][x][idx] = 1;

		int sum = 0;
		char next = word.charAt(idx + 1);
		for(int i = 0; i < 4; i ++) {
			for(int j = 1; j <= K; j ++) {
				int nx = x + dx[i] * j;
				int ny = y + dy[i] * j;
				if(nx >= 0 && nx < M && ny >= 0 && ny < N && !visit[ny][nx] && map[ny][nx] == next)
					sum += recur(ny, nx, idx + 1);
			}
		}
		return dp[y][x][idx] = sum;
	}
}