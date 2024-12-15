import java.io.*;

public class Main {
	static final long MOD = 998244353;
	static final long w = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int size = 2;
		while(size < N << 1)
			size <<= 1;

		long[] arr = new long[size];
		int[] check = new int[size];
		for(int i = 1; i < N; i++) {
			int idx = (int)((long)i * i % N);
			arr[idx]++;
			check[idx]++;
		}

		mul(arr);

		for(int i = 0; i < size; i++) {
			if(arr[i] != 0) {
				long sum = 0;
				if(i % 2 == 0 && check[i / 2] != 0) {
					int num = check[i / 2];
					arr[i] -= (long)num * num;
					sum += num + (((long)num * (num - 1)) / 2);
				}
				arr[i] /= 2;
				arr[i] += sum;
			}
		}

		for(int i = N; i < size; i++) {
			arr[i % N] += arr[i];
			arr[i] = 0;
		}
		long res = 0;
		for(int i = 0; i < N; i++) {
			if(arr[i] != 0 && check[i] != 0)
				res += arr[i] * check[i];
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