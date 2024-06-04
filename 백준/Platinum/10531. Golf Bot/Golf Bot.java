import java.io.*;

public class Main {
	static final long MOD = 998244353;
	static final int LEN = 200_000;
	static final long w = 3;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

        int size = 2;
		while(size < LEN << 1)
			size <<= 1;

		long[] arr = new long[size];
		arr[0] = 1;
		while(N-- > 0) {
			int k = Integer.parseInt(br.readLine());
			arr[k]++;
		}

		mul(arr);

		int M = Integer.parseInt(br.readLine());

        int res = 0;
		while(M-- > 0) {
			int d = Integer.parseInt(br.readLine());
			if(arr[d] != 0)
				res++;
		}
		System.out.println(res);
	}

	static void mul(long[] arr) {
		NTT(arr, false);

		int len = arr.length;
		for(int i = 0; i < len; i++)
			arr[i] = (arr[i] * arr[i]) % MOD;

		NTT(arr, true);
	}

	static void NTT(long[] arr, boolean inv) {
		int len = arr.length;
		long x = pow(w, (MOD - 1) / len);
		if(inv)
			x = pow(x, MOD - 2);

		int[] root = new int[len];
		root[0] = 1;
		for(int i = 1; i < len; i++)
			root[i] = (int)((root[i - 1] * x) % MOD);

		for(int i = 0; i < len; i++) {
			int rev = 0;
			for(int j = 1, tg = i; j < len; j <<= 1, tg >>= 1)
				rev = (rev << 1) + (tg & 1);

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