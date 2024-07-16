import java.io.*;
import java.util.*;

public class Main {
	static final int[] dx = {0, -1, 0, 1}, dy = {-1, 0, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		List<int[]> room = new ArrayList<>();
		List<int[]> heater = new ArrayList<>();
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				int n = Integer.parseInt(st.nextToken());
				if(n == 0)
                    continue;
				if(n == 5)
					room.add(new int[] {i, j});
				else {
					if(n == 1)
						heater.add(new int[] {i, j, 2});
					else if(n == 2) 
						heater.add(new int[] {i, j, 0});
					else if(n == 3) 
						heater.add(new int[] {i, j, 1});
					else
						heater.add(new int[] {i, j, 3});
				}
			}
		}

		int W = Integer.parseInt(br.readLine());

		boolean[][][] wall = new boolean[R][C][4];
		for(int i = 0; i < W; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken());

			if(t == 0) {
				wall[x][y][1] = true;
				wall[x - 1][y][3] = true;
			} else {
				wall[x][y][2] = true;
				wall[x][y + 1][0] = true;
			}
		}
		
		int[][] heat = new int[R][C];
		for(int i = 0; i < heater.size(); i++) {
			Queue<int[]> queue = new ArrayDeque<>();
			boolean[][] visit = new boolean[R][C];
			int[] h = heater.get(i);
			int dir = h[2];
			heat[h[0] + dx[dir]][h[1] + dy[dir]] += 5;
			queue.offer(new int[] {h[0] + dx[dir], h[1] + dy[dir], 5});
			
			while(!queue.isEmpty()) {
				int[] now = queue.poll();
				if(now[2] == 1)
                    break;

				if(!wall[now[0]][now[1]][(dir + 3) % 4]
					&& now[0] + dx[(dir + 3) % 4] >= 0 && now[0] + dx[(dir + 3) % 4] < R
					&& now[1] + dy[(dir + 3) % 4] >= 0 && now[1] + dy[(dir + 3) % 4] < C
					&& !wall[now[0] + dx[(dir + 3) % 4]][now[1] + dy[(dir + 3) % 4]][dir]) {
					int nx = now[0] + dx[(dir + 3) % 4] + dx[dir];
					int ny = now[1] + dy[(dir + 3) % 4] + dy[dir];
					if(nx >= 0 && nx < R && ny >= 0 && ny < C && !visit[nx][ny]) {
				    	heat[nx][ny] += now[2] - 1;
					    queue.offer(new int[] {nx, ny, now[2] - 1});
					    visit[nx][ny] = true;
                    }
				}
				
				if(!wall[now[0]][now[1]][dir]) {
					int nx = now[0] + dx[dir];
					int ny = now[1] + dy[dir];
					if(nx >= 0 && nx < R && ny >= 0 && ny < C && !visit[nx][ny]) {
                        heat[nx][ny] += now[2] - 1;
                        queue.offer(new int[] {nx, ny, now[2] - 1});
                        visit[nx][ny] = true;
                    }
				}
				
				if(!wall[now[0]][now[1]][(dir + 1) % 4]
					&& now[0] + dx[(dir + 1) % 4] >= 0 && now[0] + dx[(dir + 1) % 4] < R
					&& now[1] + dy[(dir + 1) % 4] >= 0 && now[1] + dy[(dir + 1) % 4] < C
					&& !wall[now[0] + dx[(dir + 1) % 4]][now[1] + dy[(dir + 1) % 4]][dir]) {
					int nx = now[0] + dx[(dir + 1) % 4] + dx[dir];
					int ny = now[1] + dy[(dir + 1) % 4] + dy[dir];
					if(nx >= 0 && nx < R && ny >= 0 && ny < C && !visit[nx][ny]) {
                        heat[nx][ny] += now[2] - 1;
                        queue.offer(new int[] {nx, ny, now[2] - 1});
                        visit[nx][ny] = true;
                    }
				}
			}
		}
		
		int[][] map = new int[R][C];
		for(int t = 1; t <= 100; t++) {
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++)
					map[i][j] += heat[i][j];
			}
			
			int[][] adjust = new int[R][C];
			for(int i = 0; i < R - 1; i++) {
				for(int j = 0; j < C; j++) {
					if(!wall[i][j][3]) {
						int tmp = (map[i][j] - map[i + 1][j]) / 4;
						adjust[i][j] -= tmp;
						adjust[i + 1][j] += tmp;
					}
				}
			}
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C - 1; j++) {
					if(!wall[i][j][2]) {
						int tmp = (map[i][j] - map[i][j + 1]) / 4;
						adjust[i][j] -= tmp;
						adjust[i][j + 1] += tmp;
					}
				}
			}
			for(int i = 0; i < R; i++) {
				for(int j = 0; j < C; j++)
					map[i][j] += adjust[i][j];
			}
			
			for(int i = 0; i < R; i++) {
				if(map[i][0] > 0)
					map[i][0]--;
				if(map[i][C - 1] > 0)
					map[i][C - 1]--;
			}
			for(int i = 1; i < C - 1; i++) {
				if(map[0][i] > 0)
					map[0][i]--;
				if(map[R - 1][i] > 0)
					map[R - 1][i]--;
			}
			
			boolean terminated = true;
			for(int i = 0; i < room.size(); i++) {
				if(map[room.get(i)[0]][room.get(i)[1]] < K)
					terminated = false;
			}
			if(terminated) {
				System.out.println(t);
				return;
			}	
		}
		System.out.println(101);
	}
}