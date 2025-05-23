import java.io.*;
import java.util.*;

public class Main {
	static final int[] dx = {0, 1, 0, -1};
	static final int[] dy = {-1, 0, 1, 0};
	static Queue<Point> queue = new LinkedList<>();
	static char[][] map;
	static int[][] ch ;
	static int W, H;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new char[H][W];
		ch = new int[H][W];
		int startX = 0, startY = 0;
		boolean flag = false;
		for(int i = 0; i < H; i++) {
			String line = br.readLine();
			for(int j = 0; j < W; j++) {
				ch[i][j] = Integer.MAX_VALUE;
				char point = line.charAt(j);
				if(point == 'C' && !flag) {
					startX = j;
					startY = i;
					map[i][j] = 'S';
					flag = true;
				}
				map[i][j] = point;
			}
		}

        int res = Integer.MAX_VALUE;
		for(int i = 0; i < 4; i++) {
			int nx = startX + dx[i];
			int ny = startY + dy[i];
			if(nx >= 0 && ny >= 0 && nx < W && ny < H  && map[ny][nx] != '*') {
				queue.offer(new Point(nx, ny, 0, i));
				ch[ny][nx] = 0;
			}
		}
		while(!queue.isEmpty()) {
			int length = queue.size();
			for(int i = 0; i < length; i++) {
				Point now = queue.poll();
				if(map[now.y][now.x] == 'C')
                    res = Math.min(now.num, res);

				int nx = now.x + dx[now.d];
				int ny = now.y + dy[now.d];
				if(nx >= 0 && ny >= 0 && nx < W && ny < H && ch[ny][nx] > now.num && map[ny][nx] != '*') {
					queue.offer(new Point(nx, ny, now.num, now.d));
					ch[ny][nx] = now.num;
				}
				reflect(now);
			}
		}
		System.out.println(res);
	}

	static void reflect(Point x) {
		switch (x.d) {
            case 0: case 2: {
                int nx = x.x + dx[1];
                int ny = x.y + dy[1];
                if(nx >= 0 && ny >= 0 && nx < W && ny < H && ch[ny][nx] >= x.num + 1 && map[ny][nx] != '*') {
                    queue.offer(new Point(nx, ny, x.num + 1, 1));
                    ch[ny][nx] = x.num + 1;
                }

                nx = x.x + dx[3];
                ny = x.y + dy[3];
                if(nx >= 0 && ny >= 0 && nx < W && ny < H && ch[ny][nx] >= x.num + 1 && map[ny][nx] != '*' ) {
                    queue.offer(new Point(nx, ny, x.num + 1, 3));
                    ch[ny][nx] = x.num + 1;
                }
                break;
            }
            case 1: case 3: {
                int nx = x.x + dx[0];
                int ny = x.y + dy[0];
                if(nx >= 0 && ny >= 0 && nx < W && ny < H && ch[ny][nx] >= x.num + 1 && map[ny][nx] != '*') {
                    queue.offer(new Point(nx, ny, x.num + 1, 0));
                    ch[ny][nx] = x.num + 1;
                }

                nx = x.x + dx[2];
                ny = x.y + dy[2];
                if(nx >= 0 && ny >= 0 && nx < W && ny < H && ch[ny][nx] >= x.num + 1 && map[ny][nx] != '*') {
                    queue.offer(new Point(nx, ny, x.num + 1, 2));
                    ch[ny][nx] = x.num + 1;
                }
            }
		}
	}
}

class Point {
	int x, y, num, d;

	Point(int x, int y, int num, int d) {
		this.x = x;
		this.y = y;
		this.num = num;
		this.d = d;
	}
}