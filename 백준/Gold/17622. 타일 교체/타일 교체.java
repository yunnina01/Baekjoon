import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static final int INF = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visit;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		if(K == 0) {
		    visit = new boolean[N][N];
			System.out.println(BFS());
		}
		else {
			int res = INF;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					for(int k = 0; k <= 5; k++) {
						if(map[i][j] == k)
                            continue;
						int now = map[i][j];
						map[i][j] = k;
						visit = new boolean[N][N];
						int cnt = BFS();
						if(cnt != -1)
							res = Math.min(res, cnt);
						map[i][j] = now;
					}
				}
			}
            System.out.println(res == INF ? -1 : res);
		}
	}
	
	static int BFS() {
		if(map[0][0] % 2 == 0)
            return -1;
		if(map[N - 1][N - 1] == 1 || map[N - 1][N - 1] == 3 || map[N - 1][N - 1] == 4)
            return -1;

		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(0, 0, 1));
		visit[0][0] = true;
		int cnt = 1;
		while(!queue.isEmpty()) {
			int len = queue.size();
			for(int i = 0; i < len; i++) {
				Point now = queue.poll();
				if(now.y == N - 1 && now.x == N - 1) { 
					if(getNextDir(now.dir, map[now.y][now.x]) != -1)
                        return cnt;
					else
                        continue;
				}

				int nextdir = getNextDir(now.dir, map[now.y][now.x]);
				if(nextdir == -1)
                    continue;

				int ny = now.y + DIRECTIONS[nextdir][0];
				int nx = now.x + DIRECTIONS[nextdir][1];
				if(ny >= 0 && ny < N && nx >= 0 && nx < N && !visit[ny][nx]) {
					queue.offer(new Point(ny, nx, nextdir));
					visit[ny][nx] = true;
				}
			}
			cnt++;
		}
		return -1;
	}
	
	static int getNextDir(int dir, int next) {
		if(dir == 0) {
			if(next == 2 || next == 3 || next == 5)
                return -1;
			if(next == 0)
                return 1;
			if(next == 1)
                return 3;
			if(next == 4)
                return 0;
		} else if(dir == 1) {
			if(next == 0 || next == 2 || next == 4)
                return -1;
			if(next == 1)
                return 2;
			if(next == 3)
                return 0;
			if(next == 5)
                return 1;
		} else if(dir == 2) {
			if(next == 0 || next == 1 || next == 5)
                return -1;
			if(next == 2)
                return 1;
			if(next == 3)
                return 3;
			if(next == 4)
                return 2;
		} else {
			if(next == 1 || next == 3 || next == 4)
                return -1;
			if(next == 0)
                return 2;
			if(next == 2)
                return 0;
			if(next == 5)
                return 3;
		}
		return -1;
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