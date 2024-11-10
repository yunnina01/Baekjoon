import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
        long B = 3, C = 2;
        long cntC1 = 0, cntC2 = 0;
        long res = 0;
		for(int i = 0; i < N; i++) {
			long A = Long.parseLong(st.nextToken());
			long cntB = A - Math.min(A, cntC1);
			res += cntB * B + Math.min(A, cntC1) * C;
			cntC2 = Math.min(cntC2, A);
			cntC1 = cntC2 + cntB;
			cntC2 = cntB;
		}		
		System.out.println(res);
	}
}