import java.io.*;
import java.util.*;

public class Main {
	static char[][] map;
	static int N, M, res;
	static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        Point rPoint = null, bPoint = null;
        for(int i = 0; i < N; i++) {
        	map[i] = br.readLine().toCharArray();
        	for(int j = 0; j < M; j++) {
          		if(map[i][j] == 'R')
        			rPoint = new Point(i, j);
          		else if(map[i][j] ==  'B')
        			bPoint = new Point(i, j);
        	}
        }

        res = 11;
        DFS(0, -1, rPoint, bPoint);
        if(res == 11)
            res = -1;
        bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static void DFS(int cnt, int predir, Point red, Point blue) {
    	if(cnt == 10)
    		return;
    	for(int dir = 0; dir < 4; dir++) {
    		if(dir != predir) {
                int rx = red.x, ry = red.y;
	    		int bx = blue.x, by = blue.y;
	    		boolean success = false;
	    		boolean fail = false;

	    		while((!success && map[rx + dx[dir]][ry + dy[dir]] != '#' && !(rx + dx[dir] == bx && ry + dy[dir] == by))
	    			|| (!fail && map[bx + dx[dir]][by + dy[dir]] != '#' && !(bx + dx[dir] == rx && by + dy[dir] == ry))) {
	    			if(!success && map[rx + dx[dir]][ry + dy[dir]] == 'O') {
	    				rx = -1;
	    				ry = -1;
	    				success = true;
	    			}
	    			if(!fail && map[bx + dx[dir]][by + dy[dir]] == 'O') {
	    				bx = -1;
	    				by = -1;
	    				fail = true;
	    				break;
	    			}

	    			if(!success && map[rx + dx[dir]][ry + dy[dir]] != '#' && !(rx + dx[dir] == bx && ry + dy[dir] == by)) {
	    				rx += dx[dir];
	        			ry += dy[dir];
	    			}
	    			if(!fail && map[bx + dx[dir]][by + dy[dir]] != '#' && !(bx + dx[dir] == rx && by + dy[dir] == ry)) {
	    				bx += dx[dir];
	        			by += dy[dir];
	    			}
	    		}
	    		if(fail)
                    continue;
	    		else if(success) {
	    			if(res > cnt + 1)
                        res = cnt + 1;
	    			continue;
	    		}
	    		DFS(cnt + 1, dir, new Point(rx, ry), new Point(bx, by));
    		}
    	}
    }
}

class Point {
    int x, y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}