import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	static Queue<Point> queue = new LinkedList<>();
	static int[][] room;
    static int R, C, T;
	static int row = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		room = new int[R][C];
		for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j] == -1 && row == -1)
                    row = i;
				if(room[i][j] >= 5)
                    queue.offer(new Point(i, j));
			}
		}

		while(T-- > 0) {
			int[][] tmp = new int[R][C];
			while(!queue.isEmpty()) {
				Point now = queue.poll();
				int spread = room[now.r][now.c] / 5;
				int count = 0;
				
				for(int[] DIRECTION: DIRECTIONS) {
					int nr = now.r + DIRECTION[0], nc = now.c + DIRECTION[1];
					if(nr < 0 || nr >= R || nc < 0 || nc >= C || room[nr][nc] == -1)
                        continue;
					tmp[nr][nc] += spread;
					count++;
				}
				room[now.r][now.c] -= (spread * count);
			}
			
    		for(int i = 0; i < R; i++) {
	    		for(int j = 0; j < C; j++) {
		    		if(room[i][j] == -1)
                        continue;
		    		room[i][j] += tmp[i][j];
	    		}
    		}
			
			pushTop();
			pushBottom();
			
			initQ();
		}

        int res = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(room[i][j] > 0)
                    res += room[i][j];
			}
		}
		bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
	}
	
	static void pushTop() {
		int start = row;
		for(int r = start - 1; r > 0; r--)
			room[r][0] = room[r - 1][0];
		for(int c = 0; c < C - 1; c++)
			room[0][c] = room[0][c + 1];
		for(int r = 1; r < start + 1; r++)
			room[r - 1][C - 1] = room[r][C - 1];

		for(int c = C - 1; c > 0; c--) {
			if(room[start][c - 1] == -1) {
				room[start][c] = 0;
				continue;
			}
			room[start][c] = room[start][c - 1];
		}
	}
	
	static void pushBottom() {
		int start = row + 1;
		for(int r = start + 1; r < R - 1; r++)
			room[r][0] = room[r + 1][0];
		for(int c = 0; c < C - 1; c++)
			room[R - 1][c] = room[R - 1][c + 1];
		for(int r = R - 1; r > start; r--)
			room[r][C - 1] = room[r - 1][C - 1];
		
		for(int c = C - 1; c > 0; c--) {
			if(room[start][c - 1] == -1) {
				room[start][c] = 0;
				continue;
			}
			room[start][c] = room[start][c - 1];
		}
	}
	
	static void initQ() {
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(room[i][j] >= 5)
                    queue.offer(new Point(i, j));
			}
		}
	}
}

class Point{
    int r, c;
    
    Point(int r ,int c) {
        this.r = r;
        this.c = c;
    }
}