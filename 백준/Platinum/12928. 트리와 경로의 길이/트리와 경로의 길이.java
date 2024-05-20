import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
        
	    int[] combi = new int[45];
		combi[1] = 1;
		for(int i = 1; i < 45; i++)
			combi[i] = (i + 1) * i / 2;
		
	    boolean[][] mem = new boolean[N + 1][S + 1];
		mem[2][0] = true;
		for(int i = 2; i <= N; i++) {
			for(int j = 0; j <= S; j++) {
				if(mem[i][j]) {
					for(int k = 1; k < 45; k++) {
						if(i + k <= N && j + combi[k] <= S)
							mem[i + k][j + combi[k]] = true;
					}
				}
			}
		}
		System.out.println(mem[N][S] ? 1 : 0);
	}
}