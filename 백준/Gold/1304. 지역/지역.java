import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[] isConnected = new boolean[N + 1];
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			if(S < E)
				continue;
			
			for(int i = E; i < S; i++)
				isConnected[i] = true;
		}

		for(int i = 1; i <= N; i++) {
			if(N % i != 0)
				continue;
			
			boolean flag = true;
			for(int E = i; E <= N; E += i) {
				if(i == N)
					break;
				if(isConnected[E]) {
					flag = false;
					break;
				}
			}
			if(flag) {
				System.out.println(N / i);
				break;
			}
		}
	}
}