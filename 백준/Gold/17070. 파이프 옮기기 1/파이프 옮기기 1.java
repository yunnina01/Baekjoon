import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		boolean[][] wall = new boolean[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				if(st.nextToken().equals("1"))
                    wall[i][j] = true;
			}
		}
		
		int[][][] res = new int[N + 1][N + 1][3];
		res[1][2][2] = 1;
		for(int i = 1; i <= N; i++) {
			for(int j = 2; j <= N; j++) {
				if((i == 1 && j == 2) || wall[i][j])
                    continue;

				if(!wall[i - 1][j - 1] && !wall[i - 1][j] && !wall[i][j - 1])
					res[i][j][0] = res[i - 1][j - 1][0] + res[i - 1][j - 1][1] + res[i - 1][j - 1][2];
				if(!wall[i - 1][j])
					res[i][j][1] = res[i - 1][j][0] + res[i - 1][j][1];
				if(!wall[i][j - 1])
					res[i][j][2] = res[i][j - 1][0] + res[i][j - 1][2];
			}
		}
		bw.write(res[N][N][0] + res[N][N][1] + res[N][N][2] + "\n");
        br.close();
        bw.flush();
        bw.close();
	}
}