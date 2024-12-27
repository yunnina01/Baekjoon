import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int num1 = Integer.parseInt(st.nextToken());
		int num2 = Integer.parseInt(st.nextToken());
        
		int share = num2 / num1;
		int sqrtShare = (int)Math.sqrt(share);
		while(true) {
			if(share % sqrtShare == 0 && gcd(sqrtShare, share / sqrtShare) == 1) {
				sb.append(num1 * sqrtShare + " " + num1 * share / sqrtShare);
				break;
			}
			sqrtShare--;
		}
		System.out.println(sb);
	}
    
	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}