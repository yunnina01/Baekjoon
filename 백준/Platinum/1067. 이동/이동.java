import java.io.*;
import java.util.*;

public class Main {
	static final long MOD = 998244353;
	static final long w = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		int size = 2;
		while(size < N << 1)
			size <<= 1;

		long[] X = new long[size];
		st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
			X[i] = Integer.parseInt(st.nextToken());

		long[] Y = new long[size];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			Y[i] = Integer.parseInt(st.nextToken());


		for(int i = 0; i < N / 2; i++) {
			long tmp = X[N - 1 - i];
			X[N - 1 - i] = X[i];
			X[i] = tmp;
		}

		long[] ret = multiply(X, Y, N);

		long res = Long.MIN_VALUE;
		for(int i = N - 1; i < 2 * N - 1; i++)
			res = Math.max(res, ret[i]);
		System.out.println(res);
	}

	static long[] multiply(long[] a, long[] b, int N) {
		NTT(a, false);
		NTT(b, false);

		int size = a.length;
		long[] c = new long[size];
		for(int i = 0; i < size; i++)
			c[i] = a[i] * b[i] % MOD;

		NTT(c, true);

		for(int i = 0; i < N - 1; i++)
			c[N + i] += c[i];
		return c;
	}

	static void NTT(long[] arr, boolean inv) {
		int N = arr.length;
		long x = pow(w, (MOD - 1) / N);
		if(inv)
			x = pow(x, MOD - 2);

		long[] root = new long[N];
		root[0] = 1;
		for(int i = 1; i < N; i++)
			root[i] = (root[i - 1] * x) % MOD;

		for(int i = 0; i < N; i++) {
			int rev = 0;
			for(int j = 1, target = i; j < N; j <<= 1, target >>= 1)
				rev = (rev << 1) + (target & 1);

			if(i < rev) {
				long tmp = arr[i];
				arr[i] = arr[rev];
				arr[rev] = tmp;
			}
		}

		for(int i = 2; i <= N; i <<= 1) {
			int step = N / i;
			for(int j = 0; j < N; j += i) {
				for(int k = 0; k < (i >> 1); k++) {
					long u = arr[j | k];
					long v = arr[j | k | i >> 1] * root[step * k] % MOD;
					arr[j | k] = (u + v) % MOD;
					arr[j | k | i >> 1] = (u - v) % MOD;
					if(arr[j | k | i >> 1] < 0)
						arr[j | k | i >> 1] += MOD;
				}
			}
		}

		if(inv) {
			long t = pow(N, MOD - 2);
			for(int i = 0; i < N; i++)
				arr[i] = arr[i] * t % MOD;
		}
	}
    
	static long pow(long a, long b) {
		long ret = 1;
		while(b > 0) {
			if((b & 1) > 0)
				ret = ret * a % MOD;
			b >>= 1;
			a = a * a % MOD;
		}
		return ret;
	}
}