import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while(T -- > 0) {
			List<List<Integer>> alphabet = new ArrayList<>();
			for(int i = 0; i < 26; i++)
				alphabet.add(new ArrayList<>());

			String W = br.readLine();
			int K = Integer.parseInt(br.readLine());
			for(int i = 0; i < W.length(); i++)
				alphabet.get(W.charAt(i) - 'a').add(i);

			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < 26; i++) {
				if(alphabet.get(i).size() >= K) {
					for(int j = 0; j <= alphabet.get(i).size() - K; j++) {
						int len = alphabet.get(i).get(j + K - 1) - alphabet.get(i).get(j) + 1;
						min = Math.min(min, len);
						max = Math.max(max, len);
					}
				}
			}

			if(min == Integer.MAX_VALUE)
                sb.append(-1 + "\n");
			else
                sb.append(min + " " + max + "\n");
		}
		System.out.print(sb);
	}
}