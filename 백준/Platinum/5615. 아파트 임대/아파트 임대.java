import java.io.*;
import java.math.*;

public class Main {
	static final int[] list = {2, 3, 5, 7, 11};
	static BigInteger ONE = new BigInteger("1");

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

		long res = 0;
		while(N-- > 0) {
			long area = Long.parseLong(br.readLine());
			if(isPrime(area * 2 + 1))
                res++;
		}
		System.out.println(res);
	}

	static boolean isPrime(long area) {
		if(area == 2 || area == 3)
            return true;
		if(area <= 1 || area % 2 == 0)
            return false;
		for(int i = 0; i < list.length; i++) {
			long p = list[i];
			if(area == p)
                return true;
			if(millerRabin(area, p))
                return false;
		}
		return true;
	}

	static boolean millerRabin(long p, long a) {
		long rem = 0;
		long div = p - 1;
		while(div % 2 == 0) {
			rem++;
			div >>= 1;
		}

		BigInteger bigP = new BigInteger(p + "");
		BigInteger bigPMinus = new BigInteger(p + "").subtract(ONE);
		BigInteger x = pow(new BigInteger(a + ""), div, bigP);
		if(x.equals(ONE) || x.equals(bigPMinus))
            return false;

		long loop = rem - 1;
		while(loop-- > 0) {
			x = pow(x, 2, bigP);
			if(x.equals(bigPMinus))
                return false;
		}
		return true;
	}

	static BigInteger pow(BigInteger x, long div, BigInteger p) {
		BigInteger ret = ONE;
		x = x.mod(p);
		while(div > 0) {
			if(div % 2 == 1)
                ret = ret.multiply(x).mod(p);
			div /= 2;
			x = x.multiply(x).mod(p);
		}
		return ret;
	}
}