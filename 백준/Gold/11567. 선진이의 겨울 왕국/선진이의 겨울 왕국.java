import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
	static char[][] board;
	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new char[n][m];
		for(int i = 0; i < n; i++)
			board[i] = br.readLine().toCharArray();
		
		st = new StringTokenizer(br.readLine());
		int r1 = Integer.parseInt(st.nextToken());
        int c1 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int r2 = Integer.parseInt(st.nextToken());
        int c2 = Integer.parseInt(st.nextToken());
		
		System.out.print(BFS(r1 - 1, c1 - 1, r2 - 1, c2 - 1));
	}
	
	static String BFS(int x, int y, int x1, int y1) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {x, y});
		
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			
			for(int[] dir : DIRECTIONS) {
				int nx = now[0] + dir[0];
				int ny = now[1] + dir[1];

                if(nx >= 0 && nx < n && ny >= 0 && ny < m) {
					if(nx == x1 && ny == y1) {
						if(board[nx][ny] == '.') {
							board[nx][ny] = 'X';
							queue.offer(new int[] {nx, ny});
						}
						else
							return "YES";
					}
					else if(board[nx][ny] != 'X') {
						queue.offer(new int[] {nx, ny});
						board[nx][ny] = 'X';
					}
				}
			}
		}
		return "NO";
	}
}