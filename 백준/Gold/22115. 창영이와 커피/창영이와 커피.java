import java.io.*;
import java.util.*;

public class Main {
    static final int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] caffeine = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] dp = new int[N + 1][K + 1];
        Arrays.fill(dp[0], INF);
        dp[0][0] = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= K; j++) {
                if (j - caffeine[i - 1] >= 0)
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - caffeine[i - 1]] + 1);
                else
                    dp[i][j] = dp[i - 1][j];
            }
        }
        System.out.println(dp[N][K] == INF ? -1 : dp[N][K]);
    }
}