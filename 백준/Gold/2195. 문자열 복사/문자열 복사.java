import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String S = br.readLine();
		String P = br.readLine();

		int idx = 0;
		int cnt	= 0;
		while(true) {
			cnt++;
			boolean flag = false;
			for(int i = idx + 1; i <= P.length(); i++) {
				if(!S.contains(P.substring(idx, i))) {
					idx = i - 1;
					flag = true;
					break;
				}
			}
			if(!flag) {
				System.out.print(cnt);
				return;
			}
		}
	}
}