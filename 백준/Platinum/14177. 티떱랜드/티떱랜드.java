import java.io.*;
import java.util.*;

public class Main {
    static long[][][] dp;
    static int[][] c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
    
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        dp = new long[3][N + 1][N + 1];
        for(int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 1; c <= N; c++)
                dp[1][r][c] = dp[1][r][c - 1] + Integer.parseInt(st.nextToken());
        }

        for(int r = 1; r <= N; r++) {
            for(int c = r + 1; c <= N; c++)
                dp[2][r][c] = dp[2][r][c - 1] + dp[1][c][c] - dp[1][c][r - 1];
            dp[0][1][r] = dp[2][1][r];
        }
        
        c = new int[N + 1][N + 1];
        for(int i = 2; i <= K; i++)
            recur(i, i, N, i - 1, N - 1);
        System.out.println(dp[0][K][N]);
    }

    static void recur(int idx, int start, int end, int left, int right) {
        if(start > end)
            return;
        int mid = (start + end) >> 1;
        dp[0][idx][mid] = c[idx][mid] = -1;
        for(int i = left; i <= Math.min(mid - 1, right); i++) {
            if(dp[0][idx][mid] != -1 && dp[0][idx][mid] <= dp[0][idx - 1][i] + dp[2][i + 1][mid])
                continue;
            dp[0][idx][mid] = dp[0][idx - 1][i] + dp[2][i + 1][mid];
            c[idx][mid] = i;
        }
        recur(idx, start, mid - 1, left, c[idx][mid]);
        recur(idx, mid + 1, end, c[idx][mid], right);
    }
}