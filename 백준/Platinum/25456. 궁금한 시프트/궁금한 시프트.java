import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 998244353;
    static final int BASE = 3;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] S = br.readLine().toCharArray();
        char[] T = br.readLine().toCharArray();

		int llen = Math.max(S.length, T.length);
        int flen = 1;
		for(; flen < 2 * llen; flen <<= 1);

		long[] arr1 = new long[flen];
		for(int i = 0; i < S.length; i++)
            arr1[S.length - 1 - i] = S[i] - '0';
        long[] arr2 = new long[flen];
		for(int i = 0; i < T.length; i++)
            arr2[T.length - 1 - i] = T[i] - '0';
		arr1 = fft(arr1, false);
		arr2 = fft(arr2, false);

        long[] conv = new long[flen];
		for(int i = 0; i < flen; i++)
            conv[i] = (arr1[i] * arr2[i]) % MOD;
		conv = fft(conv,true);

		long res = 0;
		for(int i = 0; i < flen; i++)
            res = Math.max(res, conv[i]);
		System.out.println(res);
	}

	static long[] fft(long[] data, boolean inv) {
		long[] arr = Arrays.copyOf(data, data.length);
		for(int i = 1, j = 0; i < arr.length; i++) {
			int bit = arr.length / 2;
			while(true) {
				j ^= bit;
				if((j & bit) > 0)
                    break;
				bit >>>= 1;
			}
			if(i < j) {
				long tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
			}
		}

		for(int flen = 2; flen <= arr.length; flen <<= 1) {
			int halflen = flen / 2;
			long unit = pow(BASE, (MOD - 1) / flen, MOD);
			if(inv)
                unit = pow(unit, MOD - 2, MOD);

			for(int p = 0; p < arr.length; p += flen) {
				long w = 1;
				for(int n = 0; n < halflen; n++) {
					long t1 = (arr[p + n] + arr[p + n + halflen] * w) % MOD;
					long t2 = (arr[p + n] - arr[p + n + halflen] * w) % MOD;
					arr[p + n] = (t1 + MOD) % MOD;
					arr[p + n + halflen] = (t2 + MOD) % MOD;
					w = (w * unit) % MOD;
				}
			}
		}
		if(inv) {
			long invlen = pow(arr.length, MOD - 2, MOD);
			for(int i = 0; i < arr.length; i++)
				arr[i] = arr[i] * invlen % MOD;
		}
		return arr;
	}

	static long pow(long base, int exp, int mod) {
		if(exp == 1)
            return base;
		if(exp == 0)
            return 1;
		long ret = pow(base, exp / 2, mod);
		ret = (ret * ret) % mod;
		if(exp % 2 == 1)
            ret = (ret * base) % mod;
		return (int)ret;
	}
}