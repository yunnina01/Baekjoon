import java.io.*;
import java.util.*;

public class Main {
	static Deque<Integer> stack;
	static int N, res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				res = Math.max(res, map[i][j]);
			}
		}

		stack = new ArrayDeque<>();
		DFS(map, 1);
		System.out.print(res);
	}
	
	static void DFS(int[][] prev, int depth) {
		loop:
		for(int dir = 0; dir < 4; dir++) {
			int[][] map = getClone(prev);
			int max = 0;
			switch (dir) {
                case 0:
                    max = up(map);
                    break;
                case 1:
                    max = down(map);
                    break;
                case 2:
                    max = left(map);
                    break;
                case 3:
                    max = right(map);
			}

			res = Math.max(res, max);
			if(depth == 10)
				continue;
			if(max * (int)Math.pow(2, 10 - depth) <= res)
				continue;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] != prev[i][j]) {
						DFS(getClone(map), depth + 1);
						continue loop;
					}
				}
			}
		}
	}
	
	static int[][] getClone(int[][] map) {
		int[][] clone = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++)
				clone[i][j] = map[i][j];
		}
		return clone;
	}
	
	static int up(int[][] map) {
		int max = 0;
		for(int y = 0; y < N; y++) {
			for(int x = N - 1; x >= 0; x--) {
				if(map[x][y] != 0) {
					stack.add(map[x][y]);
					map[x][y] = 0;
				}
			}
			for(int x = 0; !stack.isEmpty(); x++) {
				map[x][y] = stack.poll();
				if(!stack.isEmpty() && stack.peek() == map[x][y])
					map[x][y] += stack.poll();
				max = Math.max(max, map[x][y]);
			}
		}
		return max;
	}
	
	static int down(int[][] map) {
		int max = 0;
		for(int y = 0; y < N; y++) {
			for(int x = 0; x < N; x++) {
				if(map[x][y] != 0) {
					stack.add(map[x][y]);
					map[x][y] = 0;
				}
			}
			for(int x = N - 1; !stack.isEmpty(); x--) {
				map[x][y] = stack.poll();
				if(!stack.isEmpty() && stack.peek() == map[x][y])
					map[x][y] += stack.poll();
				max = Math.max(max, map[x][y]);
			}
		}
		return max;
	}
	
	static int left(int[][] map) {
		int max = 0;
		for(int x = 0; x < N; x++) {
			for(int y = N - 1; y >= 0; y--) {
				if(map[x][y] != 0) {
					stack.add(map[x][y]);
					map[x][y] = 0;
				}
			}
			for(int y = 0; !stack.isEmpty(); y++) {
				map[x][y] = stack.poll();
				if(!stack.isEmpty() && stack.peek() == map[x][y])
					map[x][y] += stack.poll();
				max = Math.max(max, map[x][y]);
			}
		}
		return max;
	}
	
	static int right(int[][] map) {
		int max = 0;
		for(int x = 0; x < N; x++) {
			for(int y = 0; y < N; y++) {
				if (map[x][y] != 0) {
					stack.add(map[x][y]);
					map[x][y] = 0;
				}
			}
			for(int y = N - 1; !stack.isEmpty(); y--) {
				map[x][y] = stack.poll();
				if(!stack.isEmpty() && stack.peek() == map[x][y])
					map[x][y] += stack.poll();
				max = Math.max(max, map[x][y]);
			}
		}
		return max;
	}
}