import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static final int INF = 2_100_000_000;
	static double[][] times;
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		double V = Double.parseDouble(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		times = new double[R][C];
		for(int i = 0; i < R; i++) {
			Arrays.fill(times[i], INF);
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		dijkstra(V, R, C);
		System.out.printf("%f\n", times[R - 1][C - 1]);
	}

	static void dijkstra(double meterPerSec, int N, int M) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, meterPerSec, times[0][0]));
		times[0][0] = 0;
		while(!pq.isEmpty()) {
			Point now = pq.poll();
			for(int[] dir : DIRECTIONS) {
				int nr = now.row + dir[0];
				int nc = now.col + dir[1];
				if(nr >= 0 && nr < N && nc >= 0 && nc < M) {
					int dist = map[now.row][now.col] - map[nr][nc];
					double time = 1 / now.vel;
					double vel = Math.pow(2, dist) * now.vel;

					if(times[nr][nc] > times[now.row][now.col] + time) {
						times[nr][nc] = times[now.row][now.col] + time;
						pq.offer(new Point(nr, nc, vel, times[nr][nc]));
					}
				}
			}
		}
	}
}

class Point implements Comparable<Point> {
	int row, col;
	double vel, sec;

	Point(int row, int col, double vel, double sec) {
		this.row = row;
		this.col = col;
		this.vel = vel;
		this.sec = sec;
	}

	@Override
	public int compareTo(Point p) {
		return this.sec < p.sec ? -1 : 1;
	}
}