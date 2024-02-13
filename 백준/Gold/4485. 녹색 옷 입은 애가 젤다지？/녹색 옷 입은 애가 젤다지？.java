import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static final int INF = Integer.MAX_VALUE - 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int TC = 1;
		int N;
		while((N = Integer.parseInt(br.readLine())) != 0) {
			int[][] cave = new int[N][N];
			int[][] dist = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					int k = Integer.parseInt(st.nextToken());
					cave[i][j] = k;
					dist[i][j] = INF;
				}
			}
			sb.append("Problem " + TC + ": " + dijkstra(cave, dist, N) + "\n");
			TC++;
		}
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
	}
	
	static int dijkstra(int[][] cave, int[][] dist, int N) {
		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, cave[0][0]));
		dist[0][0] = cave[0][0];

		while(!pq.isEmpty()) {
			Point now = pq.poll();
			if(now.cost > dist[now.row][now.col])
				continue;

			for(int[] dir : DIRECTIONS){
				int nr = now.row + dir[0];
				int nc = now.col + dir[1];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N) {
					int minDist = dist[now.row][now.col] + cave[nr][nc];
					if(minDist < dist[nr][nc]) {
						dist[nr][nc] = minDist;
						pq.offer(new Point(nr, nc, dist[nr][nc]));
					}
				}
			}
		}
		return dist[N - 1][N - 1];
	}
}

class Point implements Comparable<Point> {
	int row, col, cost;
	
	Point(int row, int col, int cost) {
		this.row = row;
		this.col = col;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Point p) {
		return Integer.compare(this.cost, p.cost);
	}	
}