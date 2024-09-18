import java.io.*;
import java.util.*;

public class Main {
    static final long MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long a = Long.parseLong(st.nextToken());

        long k = (pow(N, a) - pow(N, a - 1) + MOD) % MOD;
        long res = k + pow(N - 1, a);
        long u = (1 - pow(N, MOD - 2) + MOD) % MOD;
        res += k * (u * (pow(u, a - 1) - 1 + MOD) % MOD) % MOD * pow(u - 1, MOD - 2) % MOD;
        System.out.println(N % MOD * res % MOD);
    }

    static long pow(long base, long exp) {
        long ret = 1;
        for(int e = (int)exp; e > 0; e /= 2) {
            if(e % 2 != 0) {
                ret *= base;
                ret %= MOD;
            }
            base *= base;
            base %= MOD;
        }
        return ret;
    }
}