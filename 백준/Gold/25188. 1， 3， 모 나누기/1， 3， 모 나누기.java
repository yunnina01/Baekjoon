import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long[][] dp = new long[N][6];
        dp[0][0] = a[0];
        dp[0][1] = 0;
        for(int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + a[i];
            for(int j = 1; j < 6; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                if(j % 2 == 0)
                    dp[i][j] += a[i];
            }
        }
        System.out.println(Arrays.stream(dp[N - 1]).max().getAsLong());
    }
}