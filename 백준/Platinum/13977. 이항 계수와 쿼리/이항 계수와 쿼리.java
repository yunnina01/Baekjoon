import java.io.*;
import java.util.*;

public class Main {
	static final long p = 1_000_000_007L;
	static long[] fac = new long[4_000_001];
	static int idx = 1;

	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringTokenizer st;
		
        int M = Integer.parseInt(br.readLine());
        fac[0] = 1;
        while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

			long top = factorial(N);
            long bot = factorial(N - K) * factorial(K) % p;
			bw.write((top * square(bot, p - 2) % p) + "\n");
		}
        br.close();
		bw.flush();
        bw.close();
	}

	static long factorial(int n) {
		for(; idx <= n; idx++)
			fac[idx] = idx * fac[idx - 1] % p;
		return fac[n];
	}

	static long square(long n, long pow) {
		if(pow == 0)
            return 1;
		else if(pow == 1)
            return n;
		long tmp = square(n, pow / 2);
        tmp = tmp * tmp % p;
		return pow % 2 == 1 ? tmp * n % p : tmp;
	}
}