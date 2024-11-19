import java.io.*;
import java.util.*;

public class Main {
	static final long MOD = 1_000_000_007L;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

		long[][] arr = new long[k + 2][k+2];
		for(int i = 0; i <= k + 1; i++) {
            for(int j = 0; j <= i; j++)
                arr[i][j] = 1;
        }
		System.out.println(matPow(arr, n)[k + 1][0]);
	}

	static long[][] matPow(long[][] a, long num) {
		if(num == 1)
            return a;
		else if(num % 2 == 1)
            return matMul(matPow(a, num - 1), a);
		else
            return matPow(matMul(a, a), num / 2);
	}

	static long[][] matMul(long[][] a, long[][] b) {
		long[][] c = new long[b[0].length][a.length];
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[0].length; j++) {
				for(int k = 0; k < b[0].length; k++)
					c[i][k] = (c[i][k] + (a[i][j] * b[j][k]) % MOD) % MOD;
			}
		}
		return c;
	}
}