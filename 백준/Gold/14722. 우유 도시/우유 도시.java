import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());

        int[][][] dp = new int[3][N + 1][N + 1];
        int[][] map = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0)
                    dp[0][i][j] = 1;
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int now = map[i][j];
                for(int k = 0; k < 3; k++) {
                    int prev = (k + 2) % 3;
                    dp[k][i][j] = Math.max(dp[k][i][j],Math.max(dp[k][i - 1][j], dp[k][i][j - 1]));
                    if(now == k) {
                        if(dp[prev][i - 1][j] != 0 || dp[prev][i][j - 1] != 0)
                            dp[k][i][j] = Math.max(dp[k][i][j], Math.max(dp[prev][i - 1][j], dp[prev][i][j - 1]) + 1);
                    }
                }
            }
        }
        System.out.println(Math.max(dp[0][N][N], Math.max(dp[1][N][N], dp[2][N][N])));
    }
}