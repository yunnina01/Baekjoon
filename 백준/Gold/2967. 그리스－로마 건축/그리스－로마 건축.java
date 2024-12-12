import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	static Queue<int[]> edges;
	static long[][] map;
	static int[] aRes, bRes;
	static int R, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new long[R][2];
        for(int i = 0; i < R; i++) {
			String line = br.readLine();
			for(int j = 0; j < C; j++) {
				if(line.charAt(j) == 'x') {
					if(j <= 50)
						map[i][0] |= (1L << j);
					else
						map[i][1] |= (1L << (j - 50));
				}
			}
		}
        
		edges = new LinkedList<>();
		getEdges();

		int size = edges.size();
		while(size-- > 0)
			getLen(edges.poll());
		getTopLeftEdge();

		aRes = new int[] {-1, -1, 0};
		bRes = new int[] {-1, -1, 0};
		getResults();
		if(bRes[0] == -1)
			bRes = aRes.clone();
    
		System.out.println((aRes[0] + 1) + " " + (aRes[1] + 1) + " " + aRes[2]);
		System.out.println((bRes[0] + 1) + " " + (bRes[1] + 1) + " " + bRes[2]);
	}

	static void getEdges() {
		for(int i = 0; i < R; i++) {
			boolean prev = false;
			boolean now = false;
			for(int j = 0; j < C; j++) {
				if(j <= 50) {
					if((map[i][0] & 1L << j) != 0)
						now = true;
					else
						now = false;
				} else {
					if((map[i][1] & 1L << (j - 50)) != 0)
						now = true;
					else
						now = false;
				}

				if((now && (j == 0 || !prev)) || (!now && prev) || (now && j == C - 1)) {
					int tmpi = i;
					int tmpj = j;
					if(!now)
						tmpj--;

                    int cnt = 0;
					for(int[] dir : DELTAS) {
						int nx = tmpi + dir[0];
						int ny = tmpj + dir[1];
						if(ny <= 50) {
							if(!isIn(nx, ny) || (map[nx][0] & 1L << ny) == 0)
								cnt++;
						} else {
							if(!isIn(nx, ny) || (map[nx][1] & 1L << (ny - 50)) == 0)
								cnt++;
						}
					}
					if(cnt > 1)
						edges.add(new int[] {tmpi, tmpj});
				}
				prev = now;
			}
		}
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < R && y < C;
	}

	static void getLen(int[] edge) {
		int min = Integer.MAX_VALUE;
		int cnt = 0;
		for(int[] dir : DELTAS) {
			int x = edge[0];
			int y = edge[1];
			int len = 0;

			while(true) {
				if(isIn(x, y)) {
					if(y <= 50) {
						if((map[x][0] & 1L << y) != 0) {
							x += dir[0];
							y += dir[1];
						} else
							break;
					} else {
						if((map[x][1] & 1L << (y - 50)) != 0) {
							x += dir[0];
							y += dir[1];
						} else
							break;
					}
				}
				else
					break;
				len++;
			}
			if(len == 1) {
				cnt++;
				continue;
			}
			min = Math.min(min, len);
		}
		if(cnt > 2)
			min = 1;
		edges.add(new int[] {edge[0], edge[1], min});
	}

	static void getTopLeftEdge() {
		int size = edges.size();
		while(size-- > 0) {
			boolean[] visit = new boolean[4];
			int[] now = edges.poll();
			int len = now[2] - 1;

			for(int dir = 0; dir < 4; dir++) {
				int nx = now[0] + DELTAS[dir][0] * len;
				int ny = now[1] + DELTAS[dir][1] * len;
				if(isIn(nx, ny)) {
					if(ny <= 50) {
						if((map[nx][0] & 1L << ny) != 0)
							visit[dir] = true;
					} else {
						if((map[nx][1] & 1L << (ny - 50)) != 0)
							visit[dir] = true;
					}
				}
				if(visit[0] && visit[3])
					edges.add(new int[] {now[0] - len, now[1] - len, len + 1});
				else if(visit[1] && visit[2])
					edges.add(new int[] {now[0], now[1], len + 1});
			}
		}
	}

	static void getResults() {
		while(!edges.isEmpty()) {
			int[] now = edges.poll();
			if(aRes[0] == -1)
				aRes = now.clone();
			else {
				if(aRes[0] != now[0] || aRes[1] != now[1]) {
					bRes = now.clone();
					return;
				}
			}
		}
	}
}