import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	static char[][] map;
	static int[][] alt;
	static boolean[][] visit;
	static int N, left, right, found;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new char[N][N];
		int sr = 0, sc = 0;
        int k = 0;
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j = 0; j < N; j++) {
				char ch = map[i][j];
				if(ch == 'K')
                    k++;
				else if(ch=='P') {
					sr = i;
                    sc = j;
				}
			}
		}

		HashSet<Integer> altSet = new HashSet<>();
        alt = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
                altSet.add(alt[i][j] = Integer.parseInt(st.nextToken()));
		}

		List<Integer> altList = new ArrayList<>(altSet);
		Collections.sort(altList);

		int lidx = 0, ridx;
		int salt = alt[sr][sc];
		for(ridx = 0; altList.get(ridx) < salt; ridx++);

        int res = 1_000_000;
		while(lidx <= ridx && ridx < altList.size()) {
			left = altList.get(lidx);
			if(salt < left)
                break;
			visit = new boolean[N][N];
			right = altList.get(ridx);
			found = 0;
			visit[sr][sc] = true;
			DFS(sr,sc);

			if(found == k) {
				res = Math.min(res, right - left);
				lidx++;
			} else
                ridx++;
		}
		System.out.println(res);
	}

	static void DFS(int r, int c) {
		if(map[r][c] == 'K')
            found++;
		for(int[] dir : DIRECTIONS) {
			int nr = r + dir[0];
            int nc = c + dir[1];
			if(isIn(nr, nc) && !visit[nr][nc]) {
				visit[nr][nc] = true;
				int nalt = alt[nr][nc];
				if(left <= nalt && nalt <= right)
                    DFS(nr, nc);
			}
		}
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}