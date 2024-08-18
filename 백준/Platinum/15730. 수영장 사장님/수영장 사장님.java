import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		PriorityQueue<Part> pq = new PriorityQueue<>();
		boolean[][] visit = new boolean[N][M];

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(i == 0 || i == N - 1 || j == 0 || j == M - 1) {
					pq.offer(new Part(i, j, map[i][j]));
					visit[i][j] = true;
				}
			}
		}

		int res = 0;
		while(!pq.isEmpty()) {
			Part now = pq.poll();
			for(int[] dir : DIRECTIONS) {
				int nx = now.x + dir[0];
				int ny = now.y + dir[1];
                if(nx >= 0 && nx < N && ny >= 0 && ny < M && !visit[nx][ny]) {
                    visit[nx][ny] = true;
                    int diff = now.h - map[nx][ny];
                    res += diff < 0 ? 0 : diff;
                    pq.offer(new Part(nx, ny, Math.max(now.h, map[nx][ny])));
                }
			}
		}
		System.out.println(res);
	}
}

class Part implements Comparable<Part> {
    int x, y, h;

    Part(int x, int y, int h) {
        this.x = x;
        this.y = y;
        this.h = h;
    }

    @Override
    public int compareTo(Part o) {
        return this.h - o.h;
    }
}