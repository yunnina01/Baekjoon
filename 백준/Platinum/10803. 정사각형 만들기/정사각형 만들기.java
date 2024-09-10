import java.io.*;
import java.util.*;

public class Main {
    static int[][] dp;
    static int tmp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if(N < M) {
            tmp = N;
            N = M;
            M = tmp;
        }
        dp = new int[N + 1][M + 1];
        System.out.println(recur(N, M));
    }

    static int recur(int N, int M) {
        if(N == M)
            return dp[N][M] = 1;

        if(N < M) {
            tmp = N;
            N = M;
            M = tmp;
        }
        if(dp[N][M] != 0)
            return dp[N][M];
        if(N > 3 * M)
            return dp[N][M] = recur(N - M, M) + 1;

        dp[N][M] = N * M;
        for(int i = N >> 1; i > 0; i--)
            dp[N][M] = Math.min(dp[N][M], recur(i, M) + recur(N - i, M));
        for(int i = M >> 1; i > 0; i--)
            dp[N][M] = Math.min(dp[N][M], recur(N, i) + recur(N, M - i));
        return dp[N][M];
    }
}