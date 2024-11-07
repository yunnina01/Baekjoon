import java.io.*;
import java.util.*;

public class Main {
	static Option[] options;
	static int[][] dp;
	static int n, B, P;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int K = Integer.parseInt(br.readLine());

		for(int t = 1; t <= K; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			P = Integer.parseInt(st.nextToken());

			options = new Option[n];
			dp = new int[B + 1][P + 1];
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int s = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				options[i] = new Option(s, c, p);
			}

			if(t != 1)
				sb.append("\n");
			sb.append("Data Set " + t + ":\n" + getMax() + "\n");
		}
		System.out.print(sb.toString());
	}

	static int getMax() {
		for(Option option : options) {
			for(int i = B; i >= option.c; i--) {
				for(int j = P; j >= option.p; j--)
					dp[i][j] = Math.max(dp[i][j], dp[i - option.c][j - option.p] + option.s);
			}
		}
		return dp[B][P];
	}
}

class Option {
    int s, c, p;

    Option(int s, int c, int p) {
        this.s = s;
        this.c = c;
        this.p = p;
    }
}