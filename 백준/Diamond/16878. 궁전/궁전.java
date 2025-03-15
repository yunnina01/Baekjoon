import java.io.*;

public class Main {
    static final long MOD = 1_000_000_007;
    static final int MAX = 10_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        long[] dp = new long[MAX];
        dp[0] = dp[1] = 1;
        dp[4] = 2;

        for(int i = 5; i < MAX; i++)
            dp[i] = ((i + 1) * dp[i - 1] % MOD - (i - 2) * dp[i - 2] % MOD - (i - 5) * dp[i - 3] % MOD + (i - 3) * dp[i - 4] % MOD + 4 * MOD) % MOD;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(dp[N] + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}