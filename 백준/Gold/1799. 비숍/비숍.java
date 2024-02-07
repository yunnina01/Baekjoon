import java.io.*;
import java.util.*;

public class Main {
	static int N, odd, even;
	static boolean[][] check;
	static int[][] restrict;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		check = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				if(st.nextToken().equals("1"))
					check[i][j] = true;
			}
		}
		
		restrict = new int[N][N];
		backTracking(0, 0);
		backTracking(1, 0);
		
		bw.write(odd + even + "\n");
        br.close();
        bw.flush();
        bw.close();
	}
	
	static void backTracking(int k, int cnt){
		if(k == N * 2 - 1) {
			odd = Math.max(odd, cnt);
			return;
		} else if(k == N * 2) {
			even = Math.max(even, cnt);
			return;
		}
		
		for(int x = 0; x <= k; x++) {
			if(x >= 0 && k - x >= 0 && x < N && k - x < N) {
				if(check[x][k - x] && restrict[x][k - x] == 0) {
					for(int i = 0; i < N; i++) {
						if(i >= 0 && i - 2 * x + k >= 0 && i < N && i - 2 * x + k < N)
							restrict[i][i - 2 * x + k]++;
					}
					
					backTracking(k + 2, cnt + 1);
					for(int i = 0; i < N; i++) {
						if(i >= 0 && i - 2 * x + k >= 0 && i < N && i - 2 * x + k < N)
							restrict[i][i - 2 * x + k]--;
					}
				}
			}
		}
		backTracking(k + 2, cnt);
	}
}