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
			g ^= (P - 1) / 2 + P % 2;
		}
		System.out.println(g == 0 ? "cubelover" : "koosaga");
	}
}