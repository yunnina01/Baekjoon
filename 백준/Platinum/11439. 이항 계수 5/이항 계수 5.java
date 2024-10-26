import java.io.*;
import java.util.*;

public class Main {
    static int[] cntPrime;
    static boolean[] prime;
    static int N, K, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cntPrime = new int[N + 1];
        prime = new boolean[N + 1];
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        getPrime();
        count();

        long res = 1L;
        for(int i = 1; i <= N; i++) {
            for(int j = 0; j < cntPrime[i]; j++) {
                res *= i;
                res %= M;
            }
        }
        System.out.println(res);
    }

    static void getPrime() {
        for(int i = 2; i <= Math.sqrt(N); i++) {
            if(!prime[i])
                continue;
            for(int j = i * i; j <= N; j += i)
                prime[j] = false;
        }
    }

    static void count() {
        for(int i = 2; i <= N; i++) {
            if(!prime[i])
                continue;
            for(long j = i; j <= N; j *= i)
                cntPrime[i] += (N / j) - (K / j) - ((N - K) / j);
        }
    }
}