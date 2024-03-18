import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		String str = br.readLine();
		for(int i = 0; i < str.length(); i++) {
			sb.append('|');
			sb.append(str.charAt(i));
		}
		sb.append('|');

		char[] text = sb.toString().toCharArray();
		int[] palindromes = manacher(text);

		long res = 0L;
		for(int p : palindromes) {
			res += (long)(p / 2);
			if(p % 2 != 0)
				res++;
		}
		bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
	}

	static int[] manacher(char[] text) {
		int len = text.length;
        int[] ret = new int[len];
        int p = 0;
		int r = 0;
        for (int i = 0; i < len; i++) {
			if(r < i)
				ret[i] = 0;
			else
				ret[i] = Math.min(ret[2 * p - i], r - i);

            while(0 <= i - ret[i] - 1 && i + ret[i] + 1 < len && text[i - ret[i] - 1] == text[i + ret[i] + 1])
				ret[i]++;
            if(r < i + ret[i]) {
				r = i + ret[i];
				p = i;
			}
		}
		return ret;
	}
}