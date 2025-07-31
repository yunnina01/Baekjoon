import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] arr;
	static boolean[][] visit;
	static int n, left, right, res = 200;
	static boolean valid;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		HashSet<Integer> arrSet = new HashSet<>();
		arr = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++)
                arrSet.add(arr[i][j] = Integer.parseInt(st.nextToken()));
		}

		List<Integer> arrList = new ArrayList<>(arrSet);
		Collections.sort(arrList);

		int lidx = 0, ridx = 0;
        int sarr = arr[0][0];
		for(; arrList.get(ridx) < sarr; ridx++);

		while(lidx <= ridx && ridx < arrList.size()) {
			left = arrList.get(lidx);
			if(sarr < left)
                break;
			right = arrList.get(ridx);
			visit = new boolean[n][n];
			visit[0][0] = true;
			valid = false;
			DFS(0, 0);

			if(valid) {
				res = Math.min(res, right - left);
				lidx++;
			} else
                ridx++;
		}
        System.out.println(res);
	}

	static void DFS(int r, int c) {
		if(r == n - 1 && c == n - 1) {
			valid = true;
			return;
		}
		for(int[] dir : DIRECTIONS) {
			int nr = r + dir[0];
            int nc = c + dir[1];
			if(isIn(nr, nc) && !visit[nr][nc]) {
				int narr = arr[nr][nc];
				visit[nr][nc] = true;
				if(left <= narr && narr <= right)
                    DFS(nr, nc);
			}
		}
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < n && 0 <= c && c < n;
	}
}