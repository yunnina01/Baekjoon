import java.io.*;
import java.util.*;

public class Main {
	static final long MOD = 998244353;
	static final int LEN = 1_000_000;
	static final long w = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int size = 2;
		while(size < LEN << 1)
			size <<= 1;

		long[] a = new long[size];
		int sqrtSize = (int)Math.sqrt(size);
		Arrays.fill(a, 1);
		a[0] = a[1] = 0;
		for(int i = 2; i <= sqrtSize; i++) {
			for(int j = i * i; j <= LEN; j += i)
				a[j] = 0;
		}
		for(int i = LEN; i < size; i++)
			a[i] = 0;

		long[] b = new long[size];
		for(int i = 0; i <= LEN >> 1; i++) {
			if(a[i] == 1)
				b[i * 2] = 1;
		}

		a[2] = 0;

		long[] ret = multiply(a, b);

		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append(ret[N] + "\n");
		}
		bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
	}

	static long[] multiply(long[] a, long[] b) {
		NTT(a, false);
		NTT(b, false);

		int size = a.length;
		long[] c = new long[size];
		for(int i = 0; i < size; i++)
			c[i] = a[i] * b[i] % MOD;

		NTT(c, true);

		return c;
	}

	static void NTT(long[] arr, boolean inv) {
		int len = arr.length;
		long x = pow(w, (MOD - 1) / len);
		if(inv)
			x = pow(x, MOD - 2);

		long[] root = new long[len];
		root[0] = 1;
		for(int i = 1; i < len; i++)
			root[i] = (root[i - 1] * x) % MOD;

		for(int i = 0; i < len; i++) {
			int rev = 0;
			for(int j = 1, target = i; j < len; j <<= 1, target >>= 1)
				rev = (rev << 1) + (target & 1);

            if(i < rev) {
				long tmp = arr[i];
				arr[i] = arr[rev];
				arr[rev] = tmp;
			}
		}

		for(int i = 2; i <= len; i <<= 1) {
			int step = len / i;
			for(int j = 0; j < len; j += i) {
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
			long t = pow(len, MOD - 2);
			for(int i = 0; i < len; i++)
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