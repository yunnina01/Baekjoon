import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 998244353;
    static final int BASE = 3;
	static final char[] RSP = {'R','S','P'};

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		br.readLine();
		char[] rpsMachine = br.readLine().toCharArray();
        char[] myMachie = br.readLine().toCharArray();

		int llen = Math.max(rpsMachine.length, myMachie.length);
        int flen = 1;
		for(; flen < 2 * llen; flen <<= 1);

		long[] out = new long[flen];
		for(int c = 0; c < 3; c++) {
			long[] rpsArr = new long[flen];
			long[] myArr = new long[flen];
			long[] conv = new long[flen];
			for(int i = 0; i < rpsMachine.length; i++)
                rpsArr[rpsMachine.length - 1 - i] = (rpsMachine[i] == RSP[c]) ? 1 : 0;
			for(int i = 0; i < myMachie.length; i++)
                myArr[i] = (myMachie[i] == RSP[(c + 2) % 3]) ? 1 : 0;

            rpsArr = FFT(rpsArr, false);
			myArr = FFT(myArr, false);

			for(int i = 0; i < flen; i++)
                conv[i] = (rpsArr[i] * myArr[i]) % MOD;
			conv = FFT(conv, true);
			for(int i = 0; i < flen; i++)
                out[i] += conv[i];
		}

		long res = 0;
		for(int i = 0; i < rpsMachine.length; i++)
            res = Math.max(res, out[i]);
		System.out.println(res);
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

	static long pow(long base, int exp, int mod){
		if(exp == 1)
            return base;
		if(exp == 0)
            return 1;
		long ret = pow(base, exp >> 1, mod);
		ret = (ret * ret) % mod;
		if(exp % 2 == 1)
            ret = (ret * base) % mod;
		return ret;
	}
}