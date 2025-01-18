import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static List<Point> mannequins;
	static int[][] map;
	static boolean[][] visit;
	static Point start;
	static int N, M, K;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
        
        mannequins = new ArrayList<>();
		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 4) {
					start = new Point(i, j);
					map[i][j] = 0;
				} else if(map[i][j] == 3)
					mannequins.add(new Point(i, j));
			}
		}
        
		visit = new boolean[N][M];
		BFS(2);
		System.out.println(BFS(1));
	}
	
	static int BFS(int state) {
		Queue<Point> queue = new LinkedList<>();
		if(state == 1) {
			queue.offer(start);
			visit[start.y][start.x] = true;
		} else {
			for(Point x : mannequins) {
				queue.offer(x);
				visit[x.y][x.x] = true;
			}
			if(K == 0)
                return -1;
		}

		int cnt = 0;
		while(!queue.isEmpty()) {
			int len = queue.size();
			for(int i = 0; i < len; i++) {
				Point now = queue.poll();
				if(state == 1 && map[now.y][now.x] == 2)
                    return cnt;
				for(int[] dir : DIRECTIONS) {
					int ny = now.y + dir[0];
					int nx = now.x + dir[1];
					if(ny >= 0 && ny < N && nx >= 0 && nx < M && !visit[ny][nx]) {
						if(state == 1 && map[ny][nx] == 1)
                            continue;
                        queue.offer(new Point(ny, nx));
						visit[ny][nx] = true;
					}
				}
			}
			cnt++;
			if(state == 2 && cnt == K)
                return -1;
		}
		return -1;
	}
}

class Point {
	int y, x;

	Point(int y, int x) {
		this.y = y;
		this.x = x;
	}
}