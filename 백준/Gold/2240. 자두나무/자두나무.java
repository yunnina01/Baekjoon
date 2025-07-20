import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] tree = new int[T + 1];
        for(int i = 1; i <= T; i++)
            tree[i] = Integer.parseInt(br.readLine()) - 1;
        
        int[][] dp = new int[T + 1][W + 1];
        for(int i = 1; i <= T; i++) {
            dp[i][0] = dp[i - 1][0];
            if(tree[i] == 0)
                dp[i][0] += 1;
        }

        for(int i = 1; i <= T; i++) {
            for(int j = 1; j <= Math.min(W, i); j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                dp[i][j] += (j % 2 == tree[i] ? 1 : 0);
            }
        }

        int res = 0;
        for(int i = 0; i <= W; i++)
            res = Math.max(res, dp[T][i]);
        System.out.println(res);
    }
}