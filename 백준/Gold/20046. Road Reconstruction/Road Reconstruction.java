import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static final int INF = Integer.MAX_VALUE;
	static int[][] map;
	
	static int BFS(int N, int M) {
		if(map[0][0] == -1)
            return INF;

        PriorityQueue<Point> pq = new PriorityQueue<>();
		int[][] move = new int[N][M];
		for(int i = 0; i < N; i++)
			Arrays.fill(move[i], INF);
		pq.offer(new Point(0, 0, map[0][0]));
		move[0][0] = map[0][0];

		while(!pq.isEmpty()) {
			Point now = pq.poll();
			if(move[now.y][now.x] < now.cnt)
                continue;
			for(int[] dir : DIRECTIONS) {
				int nx = now.x + dir[0];
				int ny = now.y + dir[1];
				if(nx >= 0 && nx < M && ny >= 0 && ny < N) {
					if(map[ny][nx] == -1)
                        continue;
					if(now.cnt + map[ny][nx] < move[ny][nx]) {
						move[ny][nx] = now.cnt + map[ny][nx];
						pq.offer(new Point(ny, nx, move[ny][nx]));
					}
				}
			}
		}
		return move[N - 1][M - 1];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		int res = BFS(N, M);
        System.out.println(res == INF ? -1 : res);
	}
}


class Point implements Comparable<Point> {
	int y, x, cnt;

	Point(int y, int x, int cnt) {
		this.y = y;
		this.x = x;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Point o) {
		return this.cnt - o.cnt;
	}
}