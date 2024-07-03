import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		boolean[] isPrime = new boolean[N + 1];
		Arrays.fill(isPrime, true);

		isPrime[0] = isPrime[1] = false;
		for(int i = 2; i * i <= N; i++) {
			if(!isPrime[i])
				continue;
			for(int j = i * i; j <= N; j += i)
				isPrime[j] = false;
		}

		if(N < 8) {
			sb.append(-1).append("\n");
		} else if(N % 2 == 0) {
			for(int i = 2; i <= N; i++) {
				if(isPrime[i] && isPrime[N - 4 - i]) {
					sb.append(2 + " " + 2 + " " + i + " " + (N - 4 - i) + "\n");
					break;
				}
			}
		} else {
			for(int i = 2; i <= N; i++) {
				if(isPrime[i] && isPrime[N - 5 - i]) {
					sb.append(2 + " " + 3 + " " + i + " " + (N - 5 - i) + "\n");
					break;
				}
			}
		}
		System.out.print(sb);
	}
}