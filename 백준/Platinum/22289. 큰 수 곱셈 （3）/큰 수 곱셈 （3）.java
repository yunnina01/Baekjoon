import java.io.*;
import java.util.*;

public class Main {
	static final long MOD = 998244353;
	static final long w = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		String strA = st.nextToken();
		String strB = st.nextToken();

		int size = 2;
		while(size < strA.length() + strB.length())
			size <<= 1;

		long[] A = new long[size];
		for(int i = 0; i < strA.length(); i++)
			A[i] = strA.charAt(i) - '0';

		long[] B = new long[size];
		for(int i = 0; i < strB.length(); i++)
			B[i] = strB.charAt(i) - '0';

		long[] C = multiply(A, B, size);

		int res[] = new int[strA.length() + strB.length() + 1];
		for(int i = strA.length() + strB.length() - 2; i >= 0; i--) {
			int idx = res.length - 1 - (strA.length() + strB.length() - 2 - i);
			res[idx] += C[i];
			res[idx - 1] += res[idx] / 10;
			res[idx] %= 10;
		}

		int idx = 0;
		while(res[idx] == 0) {
			idx++;
			if(idx == res.length) {
				System.out.println(0);
				return;
			}
		}
		for(; idx < res.length; idx++)
			sb.append(res[idx]);
		System.out.println(sb);
	}

	static long[] multiply(long[] A, long[] B, int size) {
		DFT(A, false);
		DFT(B, false);

		long[] C = new long[size];
		for (int i = 0; i < size; i++)
			C[i] = A[i] * B[i] % MOD;

        DFT(C, true);
		return C;
	}

	static void DFT(long[] arr, boolean inv) {
		int len = arr.length;
		long x = pow(w, (MOD - 1) / len);
		if(inv)
			x = pow(x, MOD - 2);

		long[] root = new long[len];
		root[0] = 1;
        for(int i = 1; i < len; i++)
			root[i] = (int)((root[i - 1] * x) % MOD);

		for(int i = 0; i < len; i++) {
			int reverse = 0;
			for(int j = 1, target = i; j < len; j <<= 1, target >>= 1)
				reverse = (reverse << 1) + (target & 1);
			if(i < reverse) {
				long tmp = arr[i];
				arr[i] = arr[reverse];
				arr[reverse] = tmp;
			}
		}

		for(int i = 2; i <= len; i <<= 1) {
			int step = len / i;
			for(int j = 0; j < len; j += i) {
				for(int k = 0; k < (i >> 1); k++) {
					long u = arr[j | k];
					long v = arr[j | k | i >> 1] * root[step * k] % MOD;
					arr[j | k] = ((u + v) % MOD);
					arr[j | k | i >> 1] = ((u - v) % MOD) < 0 ? ((u - v) % MOD + MOD) : ((u - v) % MOD);
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