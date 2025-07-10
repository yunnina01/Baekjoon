import java.io.*;
import java.util.*;

public class Main {
	static int[][] d;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		d = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(i == j)
                    d[i][j] = 0;
				else
                    d[i][j] = 10000000;
			}
		}

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;

			d[a][b] = 1;
			d[b][a] = -1;
		}
		System.out.println(floyeWarshall(N));
	}
	
	static int floyeWarshall(int N) {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(d[j][k] != 10000000)
                        continue;
					else {
						if(d[j][i] == 1 && d[i][k] == 1) { 
							d[j][k] = 1;
							d[k][j] = -1;
						}
					}
				}
			}
		}
        
		int ret = 0;
		for(int i = 0; i < N; i++) {
			boolean flag = false;
			for(int j = 0; j < N; j++) {
				if(d[i][j] == 10000000) {
					flag = true;
					break;
				}
			}
			if(!flag)
                ret++;
		}
		return ret;
	}
}