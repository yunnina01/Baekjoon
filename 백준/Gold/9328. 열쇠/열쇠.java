import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static char[][] map;
	static List<Point>[] lockedDoor;
	static boolean[] key;
	static Queue<Point> queue;
	static boolean[][] visit;
	static int res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			map = new char[h][w];
			for(int i = 0; i < h; i++)
				map[i] = br.readLine().toCharArray();
			
			queue = new LinkedList<>();
			visit = new boolean[h][w];
			lockedDoor = new ArrayList[26];
			for(int i = 0; i < 26; i++)
				lockedDoor[i] = new ArrayList<>();
			
            key = new boolean[26];
			String keys = br.readLine();
			if(!keys.equals("0")) {
				for(int i = 0; i < keys.length(); i++)
					key[keys.charAt(i) - 'a'] = true;
			}
			
			res = 0;
			for(int i = 0; i < w; i++) {
				if(map[0][i] != '*')
                    BFS(new Point(0, i));
				if(map[h - 1][i] != '*')
                    BFS(new Point(h - 1, i));
			}
			for(int i = 1; i < h - 1; i++) {
				if(map[i][0] != '*')
                    BFS(new Point(i, 0));
				if(map[i][w - 1] != '*')
                    BFS(new Point(i, w - 1));
			}
			
			while(!queue.isEmpty()) {
				Point now = queue.poll();
				for(int[] dir : DIRECTIONS) {
					int nx = now.x + dir[0];
					int ny = now.y + dir[1];
					if(nx < 0 || nx >= h || ny < 0 || ny >= w || visit[nx][ny] || map[nx][ny] == '*')
                        continue;
					BFS(new Point(nx, ny));
				}
			}
			bw.write(res + "\n");
		}
        br.close();
        bw.flush();
        bw.close();
	}
	
	static void BFS(Point p) {
		char now = map[p.x][p.y];
		visit[p.x][p.y] = true;
		
		if('a' <= now && now <= 'z') {
			key[now - 'a'] = true;
			map[p.x][p.y] = '.';
			
			for(Point door : lockedDoor[now - 'a']) {
				map[door.x][door.y] = '.';
				queue.offer(new Point(door.x, door.y));
			}
			lockedDoor[now - 'a'].clear();
		} else if('A' <= now && now <= 'Z') {
			if(key[now - 'A'])
				map[p.x][p.y] = '.';
			else {
				lockedDoor[now - 'A'].add(new Point(p.x, p.y));
				return;
			}
		} else if(now == '$') {
			map[p.x][p.y] = '.';
			res++;
		}
		queue.offer(new Point(p.x, p.y));
	}
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}