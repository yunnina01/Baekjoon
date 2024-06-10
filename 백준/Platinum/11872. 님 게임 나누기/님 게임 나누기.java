import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
        
        int g = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int P = Integer.parseInt(st.nextToken());
			g ^= (P - 1) % 4 > 1 ? P - 1 + P % 2 * 2 : P;
		}
		System.out.println(g == 0 ? "cubelover" : "koosaga");
	}
}