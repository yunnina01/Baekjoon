import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while(true) {
			String input = br.readLine();
			if(input == null)
				break;

			long n = Long.parseLong(input);
			long x = 1;
			while(true) {
				x *= 9;
				if(x >= n) {
					sb.append("Baekjoon wins.\n");
					break;
				}

				x *= 2;
				if(x >= n) {
					sb.append("Donghyuk wins.\n");
					break;
				}
			}
		}
		System.out.print(sb);
	}
}