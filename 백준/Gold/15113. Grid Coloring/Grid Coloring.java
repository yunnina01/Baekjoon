import java.io.*;
import java.util.*;

public class Main {
	static char[][] map;
	static int[] blueIdx;
	static int m, n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		map = new char[m][n];
		for(int i = 0; i < m; i++)
			map[i] = br.readLine().toCharArray();

		blueIdx = new int[n];
		for(int i = 0; i < n; i++)
			blueIdx[i] = -1;

		if(isPossible()) {
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(map[i][j] == 'B') {
                        for(int k = 0; k <= j; k++)
                            blueIdx[k] = Math.max(blueIdx[k], i);
                    }
                }
            }
			System.out.println(calcDP());
		} else
			System.out.println(0);
	}

	static boolean isPossible() {
		for(int i = 0; i < m; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] != 'R')
                    continue;
				for(int x = i; x < m; x++) {
					for(int y = j; y < n; y++) {
						if(x == i && y == j)
                            continue;
						if(map[x][y] == 'B')
							return false;
					}
				}
			}
		}
		return true;
	}

	static long calcDP() {
		long[] dp = new long[m + 1];
		dp[m] = 1;
		for(int j = 0; j < n; j++) {
			long[] next = new long[m + 1];
			for(int i = blueIdx[j]; i < m; i++) {
				if(i >= 0 && map[i][j] == 'R')
                    break;
				for(int x = i + 1; x <= m; x++)
					next[i + 1] += dp[x];
			}
			dp = next;
		}

		long ret = 0;
		for(int i = 0; i <= m; i++)
			ret += dp[i];
		return ret;
	}
}