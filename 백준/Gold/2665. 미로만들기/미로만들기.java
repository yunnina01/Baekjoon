import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	static int[][] boom, map;
	static int n;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		boom = new int[n][n];
		map  = new int[n][n];
		for(int i = 0; i < n; i++) {
			Arrays.fill(boom[i], Integer.MAX_VALUE);
			String line = br.readLine();
			for(int j = 0; j < n; j++)
				map[i][j] = (int)(line.charAt(j) - '0');
		}
		System.out.println(BFS());
	}

	static int BFS() {
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0, 0));
		while(!queue.isEmpty()) {
			Point now = queue.poll();
			if(boom[now.y][now.x] < now.cnt)
                continue;
			for(int[] dir : DIRECTIONS) {
				int nx = now.x + dir[0];
				int ny = now.y + dir[1];
				if(nx >= 0 && nx < n && ny >= 0  && ny < n) {
					if(map[ny][nx] == 0 && now.cnt + 1 < boom[ny][nx]) {
						boom[ny][nx] = now.cnt + 1;
						queue.offer(new Point(nx, ny, now.cnt + 1));
					} else if(map[ny][nx] == 1 && now.cnt < boom[ny][nx]){
						boom[ny][nx] = now.cnt;
						queue.offer(new Point(nx, ny, now.cnt));
					}
				}
			}
		}
		return boom[n - 1][n - 1];
	}
}

class Point {
	int x, y, cnt;
	
    Point(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}