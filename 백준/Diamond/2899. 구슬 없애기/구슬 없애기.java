import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 1000;
    static int[][][] dp;
    static int[] marble;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N][N][K];
        marble = new int[N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++)
                Arrays.fill(dp[i][j], -1);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            marble[i] = Integer.parseInt(st.nextToken());

        System.out.println(recur(0, N - 1, 0));
    }

    static int recur(int left, int right, int cnt) {
        if(left > right || right >= N)
            return 0;
        if(left == right)
            return K - cnt - 1;
        if(dp[left][right][cnt] == -1) {
            dp[left][right][cnt] = (cnt > K - 1) ? MAX : (cnt < K - 1) ? recur(left, right, cnt + 1) + 1 : recur(left + 1, right, 0);
            for(int i = left; i < right; i++) {
                if(marble[left] != marble[i + 1])
                    continue;
                dp[left][right][cnt] = Math.min(dp[left][right][cnt], recur(left + 1, i, 0) + recur(i + 1, right, Math.min(K - 1, cnt + 1)));
            }
        }
        return dp[left][right][cnt];
    }
}