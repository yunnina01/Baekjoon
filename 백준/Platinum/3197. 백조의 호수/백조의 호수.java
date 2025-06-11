import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTION = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		Queue<int[]> water = new ArrayDeque<>();
		char[][] lake = new char[R][C];
        int[][] pos = new int[2][2];
		pos[0][0] = -1;

		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				lake[i][j] = str.charAt(j);
				if(lake[i][j] == 'L') {
					if(pos[0][0] == -1) {
						pos[0][0] = i;
						pos[0][1] = j;
					} else {
						pos[1][0] = i;
						pos[1][1] = j;
					}
					lake[i][j] = '.';
				}
				
				if(lake[i][j] == '.')
					water.offer(new int[] {i, j});
			}
		}

		boolean[][] visit = new boolean[R][C];
		visit[pos[0][0]][pos[0][1]] = true;
		
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {pos[0][0], pos[0][1]});
		
		for(int res = 0; ; res++) {
			Queue<int[]> ice = new ArrayDeque<>();
			while(!queue.isEmpty()) {
				int[] now = queue.poll();
				for(int[] dir : DIRECTION) {
					int nx = now[0] + dir[0];
					int ny = now[1] + dir[1];
					if(nx < 0 || nx >= R || ny < 0 || ny >= C)
                        continue;

					if(nx == pos[1][0] && ny == pos[1][1]) {
						System.out.println(res);
						return;
					}

					if(visit[nx][ny])
                        continue;
					
                    if(lake[nx][ny] == 'X') {
						visit[nx][ny] = true;
						ice.offer(new int[] {nx, ny});
						continue;
					}
					visit[nx][ny] = true;
					queue.offer(new int[] {nx, ny});
				}
			}
			queue = ice;
			
			int size = water.size();
			while(size-- > 0) {
				int[] now = water.poll();
				for(int[] dir : DIRECTION) {
					int nx = now[0] + dir[0];
					int ny = now[1] + dir[1];
					if(nx < 0 || nx >= R || ny < 0 || ny >= C)
                        continue;

					if(lake[nx][ny] == 'X') {
						lake[nx][ny] = '.';
						water.offer(new int[] {nx, ny});
					}
				}
			}
		}
	}
}