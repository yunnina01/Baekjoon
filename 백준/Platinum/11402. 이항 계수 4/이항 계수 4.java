import java.io.*;
import java.util.*;
import java.util.function.BiFunction;

public class Main {
    static final int SIZE = 2001;
    static long[] factorial;
    static long mod;
    static final BiFunction<Long, Long, Long> MODULATION_MUL = (x, y) -> ((x % mod) * (y % mod)) % mod;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());
        long M = Long.parseLong(st.nextToken());

        mod = M;

        init();
        System.out.println(process(N, K, M));
    }

    static void init() {
        factorial = new long[SIZE];
        Arrays.fill(factorial, 1L);
        for(int i = 2; i < SIZE; i++)
            factorial[i] = MODULATION_MUL.apply(factorial[i - 1], (long)i);
    }

    static long process(long n, long k, long m) {
        if(k == 0)
            return 1 % m;
        long ret = 1L;
        while(n > 0 || k > 0) {
            long modN = n % m;
            long modK = k % m;
            if(modN < modK) {
                ret = 0;
                break;
            }

            ret = MODULATION_MUL.apply(ret, MODULATION_MUL.apply(factorial[(int) modN]
                , MODULATION_MUL.apply(pow(factorial[(int)(modN - modK)], m - 2)
                , pow(factorial[(int) modK], m - 2))));
            n /= m;
            k /= m;
        }
        return ret;
    }

    static long pow(long x, long y) {
        if(y == 1)
            return x;
        long powShift = pow(x, y >> 1L);
        if(y % 2 == 0)
            return MODULATION_MUL.apply(powShift, powShift);
        return MODULATION_MUL.apply(x, MODULATION_MUL.apply(powShift, powShift));
    }
}