import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] scoville = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        dp = new long[N + 1];
        dp[0] = 1;
        long res = 0;
        for(int i = 0; i < N; i++) {
            res += scoville[i] * pow(i);
            res -= scoville[i] * pow(N - i - 1);
            res %= MOD;
        }
        bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static long pow(int x) {
        if(dp[x] != 0)
            return dp[x];
        long half = pow(x / 2);
        if(x % 2 == 0)
            return dp[x] = half * half % MOD;
        return dp[x] = half * half * 2 % MOD;
    }
}