import java.io.*;
import java.util.*;

public class Main {
	static boolean[][] ch;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

        ch = new boolean[52][52];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int P = getIdx(st.nextToken().charAt(0));
			st.nextToken();
			int Q = getIdx(st.nextToken().charAt(0));
			ch[P][Q] = true;
		}
		floydWarshall();

		int res = 0;
		for(int i = 0; i < 52; i++) {
			for(int j = 0; j < 52; j++) {
				if(i != j && ch[i][j]) {
					res++;
					sb.append(getCh(i) + " => " + getCh(j) + "\n");
				}
			}
		}
		System.out.print(res + "\n" + sb);
	}
	
	static int getIdx(char x) {
		if(x - 'a' >= 0)
			return x - 'a' + 26;
		return x - 'A';
	}
	
	static void floydWarshall() { 
		for(int i = 0; i < 52; i++) {
			for(int j = 0; j < 52; j++) {
				for(int k = 0; k < 52; k++) {
					if(ch[j][i] && ch[i][k])
                        ch[j][k] = true;
				}
			}
		}
	}
	
	static char getCh(int idx) {
		if(idx >= 26)
			return (char)(idx - 26 + 'a');
        return (char)(idx + 'A');
	}
}	