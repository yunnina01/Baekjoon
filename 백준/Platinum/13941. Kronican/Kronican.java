import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    static int[] dp;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        if(N == K)
            System.out.println(0);
        else {
            dp = new int[1 << N];
            Arrays.fill(dp, -1);

            System.out.println(recur(0));
        }
    }

    static int recur(int selected) {
        if(Integer.bitCount(selected) == (N - K))
            return 0;
        if(dp[selected] != -1)
            return dp[selected];

        int ret = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            if((selected & (1 << i)) != 0)
                continue;
            for(int j = 0; j < N; j++) {
                if(i == j)
                    continue;
                if((selected & (1 << j)) == 0)
                    ret = Math.min(ret, recur(selected | (1 << i)) + arr[i][j]);
            }
        }
        return dp[selected] = ret;
    }
}