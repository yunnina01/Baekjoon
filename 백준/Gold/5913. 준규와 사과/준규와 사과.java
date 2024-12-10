import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static boolean[][] map, visit;
	static int tree = 25, res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int K = Integer.parseInt(br.readLine());

        map = new boolean[5][5];
		for(int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}

        visit = new boolean[5][5];
		tree -= K;
		DFS(0, 0, 1);
		System.out.println(res);
	}

	static void DFS(int y, int x, int cnt) {
		if(y == 4 && x == 4) {
			if(cnt == tree)
                res++;
		}

		visit[y][x] = true;
		for(int[] dir : DIRECTIONS) {
			int ny = y + dir[0];
			int nx = x + dir[1];
			if(ny >= 0 && ny < 5 && nx >= 0 && nx < 5 && !visit[ny][nx] && !map[ny][nx])
				DFS(ny, nx, cnt + 1);
		}
		visit[y][x] = false;
	}
}