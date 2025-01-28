import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		if(N == 1) {
			System.out.println(1 + "\n" + 1);
			return;
		}

		int[] color = new int[N + 1];
		int K = 0;
		color[1] = ++K;
		for(int i = 2; i <= N; i++) {
			if(color[i] == 0) {
				color[i] = ++K;
				for(int j = i; j <= N; j += i) {
					if(color[j] == 0)
						color[j] = K;
				}
			}
		}

		sb.append(K + "\n");
		for(int i = 1; i <= N; i++)
			sb.append(color[i] + " ");
		System.out.println(sb);
	}
}