import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};
	static HashMap<String, Integer> hmap;
	static char[][] map;
	static char[] curArr;
    static int N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String input = br.readLine();
			for(int j = 0; j < M; j++)
				map[i][j] = input.charAt(j);
		}

		hmap = new HashMap<>();
        curArr = new char[5];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				for(int k = 0; k < 5; k++)
					DFS(i, j, 0, k);
			}	
		}
		
		for(int i = 0; i < K; i++) {
			String str = br.readLine();
			sb.append(hmap.getOrDefault(str, 0) + "\n");
		}
		System.out.print(sb);
	}

	static void DFS(int y, int x, int cnt, int end) {
		curArr[cnt] = map[y][x];
		if(cnt == end) {
			String tmp = "";
			for(int i = 0; i <= end; i++)
				tmp += curArr[i];
			hmap.put(tmp, hmap.getOrDefault(tmp, 0) + 1);
			return;
		}
		for(int[] dir : DIRECTIONS) {
			int nx = (x + dir[0] + M) % M;
			int ny = (y + dir[1] + N) % N;
			DFS(ny, nx, cnt + 1, end);
		}
	}
}