import java.io.*;
import java.util.*;
import java.math.*;

public class Main {
	static List<Long> A = new ArrayList<>();
	static Random rand = new Random();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		BigInteger N = new BigInteger(br.readLine());

		pollard_rho(N);
		Collections.sort(A);

		for(long x : A)
			sb.append(x + "\n");
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
	}
	
	static void pollard_rho(BigInteger n) {
		if(n.equals(BigInteger.ONE))
			return;
		else if(n.isProbablePrime(10)) {
			A.add(n.longValue());
			return;
		}
		BigInteger d = rho(n);
		pollard_rho(d);
		pollard_rho(n.divide(d));
	}
	
	static BigInteger rho(BigInteger n) {
		if(n.mod(BigInteger.TWO).equals(BigInteger.ZERO))
			return BigInteger.TWO;

		BigInteger b = new BigInteger(n.bitLength(), rand);
		BigInteger x = new BigInteger(n.bitLength(), rand);
		BigInteger y = x;
		while(true) {
			x = f(x, b, n);
			y = f(f(y, b, n), b, n);
			BigInteger d = x.subtract(y).gcd(n);
			if(d.compareTo(BigInteger.ONE) == 1)
				return d;
		}
	}
	
	static BigInteger f(BigInteger x, BigInteger b, BigInteger n) {
		return x.multiply(x).mod(n).add(b).mod(n);
	}
}