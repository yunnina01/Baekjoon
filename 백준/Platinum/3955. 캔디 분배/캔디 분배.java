import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
			st = new StringTokenizer(br.readLine());
			long K = Integer.parseInt(st.nextToken());
			long C = Integer.parseInt(st.nextToken());
			
			long[] rst = exGcd(K, C);
			if(rst[2] != 1) {
				sb.append("IMPOSSIBLE\n");
				continue;
			}
			
			long res = rst[1];	
			res = (res % K + K) % K;
			res = Math.max(res, (K + C) / C);
			
			sb.append((res > 1e9 ? "IMPOSSIBLE" : res) + "\n");
		}
		bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
	}

	static long[] exGcd(long a, long b) {
		long r0 = a, r1 = b;
		long s0 = 1, s1 = 0;
		long t0 = 0, t1 = 1;
		
		while(r1 != 0) {
			long q = r0 / r1;
			long tmp = r0 - q * r1;
			r0 = r1;
			r1 = tmp;
			
			tmp = s0 - q * s1;
			s0 = s1;
			s1 = tmp;
			
			tmp = t0 - q * t1;
			t0 = t1;
			t1 = tmp;
		}
		return new long[] {s0, t0, r0};
	}
}