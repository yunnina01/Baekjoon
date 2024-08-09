import java.io.*;
import java.util.*;

public class Main {
	static final int[] dx = {0, 1, 0, -1}, dy = {-1, 0, 1, 0};
	static List<Point> aircons;
	static int[][] map, cmap;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

        aircons = new ArrayList<>();
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9)
                    aircons.add(new Point(i, j, -1));
			}
		}
		System.out.println(BFS());
	}

	static int BFS() {
		Queue<Point> queue = new LinkedList<>();
		cmap = new int[N][M];
		boolean[][][] visit = new boolean[N][M][4];
		int ret = 0;

		for(Point p : aircons) {
			Arrays.fill(visit[p.y][p.x], true);
			if(cmap[p.y][p.x] != 5) {
				ret++;
				cmap[p.y][p.x] = 5;
			}
			for(int i = 0; i < 4; i++)
				queue.offer(new Point(p.y, p.x, i));
		}

		while(!queue.isEmpty()) {
			Point now = queue.poll();
			int nx = now.x + dx[now.dir];
			int ny = now.y + dy[now.dir];
			if(nx >= 0 && ny >= 0 && ny < N && nx < M && !visit[ny][nx][now.dir] && map[ny][nx] != 9) {
				visit[ny][nx][now.dir] = true;
				if(cmap[ny][nx] != 5) {
					ret++;
					cmap[ny][nx] = 5;
				}
				
				if(map[ny][nx] == 1 && (now.dir == 1 || now.dir == 3))
					continue;
				else if(map[ny][nx] == 2 && (now.dir == 0 || now.dir == 2))
					continue;
				else if(map[ny][nx] == 3) {
					int ndir;
					if(now.dir >= 2)
						ndir = 2 + (now.dir + 1) % 2;
					else
						ndir = (now.dir + 1) % 2;
					queue.offer(new Point(ny, nx, ndir));
					continue;
				} else if(map[ny][nx] == 4) {
					int ndir = 3 - now.dir;
					queue.offer(new Point(ny, nx, ndir));
					continue;
				} else
					queue.offer(new Point(ny, nx, now.dir));
			}
		}
		return ret;
	}
}

class Point {
	int y, x, dir;

	Point(int y, int x, int dir) {
		this.y = y;
		this.x = x;
		this.dir = dir;
	}
}