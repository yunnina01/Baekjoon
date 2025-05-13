import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
    
		while(T-- > 0) {
			int m = Integer.parseInt(br.readLine());

			double res = 0.0;
			for(int i = 1; i <= m; i++)
				res += Math.log10(i);
			sb.append((int)res + 1 + "\n");
		}
		System.out.print(sb);
    }
}
