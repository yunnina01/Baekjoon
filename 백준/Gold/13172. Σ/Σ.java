import java.io.*;
import java.util.*;

public class Main {
    static final int p = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        
        int M = Integer.parseInt(br.readLine());
        long res = 0;
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            long N = Integer.parseInt(st.nextToken());
            long S = Integer.parseInt(st.nextToken());
            if(S % N == 0)
                res += S / N;
            else {
                long gcd = GCD(Math.max(S, N), Math.min(S, N));
                res += (long)(S / gcd) * pow(N / gcd, p - 2);
                res %= p;
            }
        }
        bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static long GCD(long a, long b) {
        if(a % b == 0)
            return b;
        return GCD(b, a % b);
    }
    
    static long pow(long n, long k) {
        if(k == 1)
            return n;
        long sq = pow(n, k / 2);
        sq = sq * sq % p;
        return k % 2 == 0 ? sq : sq * n % p;
    }
}