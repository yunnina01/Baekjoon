import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 998244353;
    static final int BASE = 3;
    static final int SIZE = 2097152;
    static final int END = 1048576;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    StringBuilder sb = new StringBuilder();
	
		boolean[] prime = new boolean[END];
		Arrays.fill(prime, true);
		for(int i = 2; i < 1025; i++) {
			if(!prime[i])
                continue;
			for(int j = i * i; j < END; j += i)
				prime[j] = false;
		}

		long[] arr = new long[SIZE];
		for(int i = 2; i < END; i++)
			if(prime[i]) arr[i] = 1;

		arr = FFT(arr,false);

		for(int i = 0; i < SIZE; i++)
            arr[i] = arr[i] * arr[i] % MOD;
		arr = FFT(arr,true);

        int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			sb.append((prime[N / 2] ? arr[N] / 2 + 1 : arr[N] / 2) + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	static long[] FFT(long[] data, boolean inv){
		long[] arr = Arrays.copyOf(data, data.length);
		for(int i = 1, j = 0; i < arr.length; i++) {
			int bit = arr.length >> 1;
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
			int halflen = flen >> 1;
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
		return ret;
	}
}