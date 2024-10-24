import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()) - 1;
		
		long[][] counts = new long[101][101];
		for(int i = 0; i <= 100; i++) {
			counts[i][0] = 1;
			for(int j = 1; j <= i; j++)
				counts[i][j] = (counts[i - 1][j - 1] + counts[i - 1][j]) % MOD;
		}

		int[] traces = new int[N + 1];
		int start = 0;
		int end = N - 1;
		int mid = (start + end) / 2;

		st = new StringTokenizer(br.readLine());
		traces[mid] = Integer.parseInt(st.nextToken());

		while(K-- > 0) {
			int t = Integer.parseInt(st.nextToken());
			if(t > traces[mid])
				start = mid + 1;
			else
				end = mid - 1;
			
			mid = (start + end) / 2;
			traces[mid] = t;
		}
		
		traces[N] = 101;
		long res = 1;
		for(int i = 0, cnt = 0, min = 1; i < traces.length; i++) {
			if(traces[i] == 0)
				cnt++;
			else {
				res *= counts[traces[i] - min][cnt];
				res %= MOD;
				
				min = traces[i] + 1;
				cnt = 0;
			}
		}
		System.out.println(res);
	}
}