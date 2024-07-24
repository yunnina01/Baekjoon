import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int N, M, R;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		int res = 0;
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken()) - 1;
            int Y = Integer.parseInt(st.nextToken()) - 1;
            char D = st.nextToken().charAt(0);

			res += domino(X, Y, D);

			st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken()) - 1;
			Y = Integer.parseInt(st.nextToken()) - 1;

			if(map[X][Y] < 0)
                map[X][Y] *= -1;
		}

		sb.append(res + "\n");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] < 0)
					sb.append("F ");
				else
					sb.append("S ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	static int domino(int x, int y, char dir) {
		int cnt = 0;
		if(map[x][y] > 0) {
			int dirN;
			if(dir == 'N')
                dirN = 0;
			else if(dir == 'E')
                dirN = 1;
			else if(dir == 'S')
                dirN = 2;
			else
                dirN = 3;

			if(dirN == 0) {
				int len = x;
				for(int i = x; i >= len; i --) {
					if(i >= 0 && map[i][y] > 0) {
						len = Math.min(len, i - (map[i][y] - 1));
						map[i][y] *= -1;
						cnt++;
					}
				}
			} else if(dirN == 1) {
				int len = y;
				for(int i = y; i <= len; i++) {
					if(i < M && map[x][i] > 0) {
                        len = Math.max(len, i + (map[x][i] - 1));
                        map[x][i] *= -1;
                        cnt++;
					}
				}
			} else if(dirN == 2) {
				int len = x;
				for(int i = x; i <= len; i++) {
					if(i < N && map[i][y] > 0) {
						len = Math.max(len, i + (map[i][y] - 1));
						map[i][y] *= -1;
						cnt++;
					}
				}
			} else {
				int len = y;
				for(int i = y; i >= len; i --) {
					if(i > 0 && map[x][i] > 0) {
                        len = Math.min(len, i - (map[x][i] - 1));
                        map[x][i] *= -1;
                        cnt++;
					}
				}
			}
		}
		return cnt;
	}
}