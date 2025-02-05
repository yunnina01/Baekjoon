import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] S = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			S[i] = Integer.parseInt(st.nextToken());

		int left = 0, right = -1;
		int cnt = 0;
		int res = Integer.MIN_VALUE;
		while(right < N) {
			if(cnt <= K) {
				if(right == N - 1)
                    break;
				if(S[++right] % 2 == 1)
					cnt++;
			} else if(S[left++] % 2 == 1)
				cnt --;

			if(cnt <= K)
				res = Math.max(res, right - left + 1 - cnt);
		}
		System.out.println(res);
	}
}