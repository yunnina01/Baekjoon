import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	static char[][] map;
	static boolean[][] visit;
	static int R, C;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		visit = new boolean[R][C];
		Point start = new Point(0,0);
		for(int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = line.charAt(j);
				if(map[i][j] == 'S') {
					start.y = i;
					start.x = j;
					map[i][j] = '.';
				}
			}
		}

		int res = BFS(start);
		System.out.println(res == -1 ? "KAKTUS" : res);
	}
	
	static int BFS(Point S) {
		Queue<Point> queue = new LinkedList<>();
		visit[S.y][S.x] = true;
		queue.offer(S);
		int cnt = 0;
		while(!queue.isEmpty()) {
			int length = queue.size();
			for(int i = 0; i < length; i++) {
				Point now = queue.poll();
				if(map[now.y][now.x] == '*')
                    continue;
				if(map[now.y][now.x] == 'D')
                    return cnt;

				for(int[] dir : DIRECTIONS) {
					int nx = now.x + dir[0];
					int ny = now.y + dir[1];
					if(ny >= 0 && nx >= 0 && ny < R && nx < C && (map[ny][nx] == '.' || map[ny][nx] == 'D') && !visit[ny][nx]) {
						queue.offer(new Point(ny, nx));
						visit[ny][nx] = true;
					}
				}
			}
			flood();
			cnt++;
		}
		return -1;
	}
	
	static void flood() {
		boolean[][] changed = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == '*' && !changed[i][j]) {
					for(int[] dir : DIRECTIONS) {
						int ny = i + dir[0];
						int nx = j + dir[1];
						if(ny >= 0 && nx >= 0 && ny < R && nx < C && map[ny][nx] == '.') {
							map[ny][nx] = '*';
							changed[ny][nx] = true;
						}
					}
				}
			}
		}
	}
}

class Point {
	int y, x;

	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}