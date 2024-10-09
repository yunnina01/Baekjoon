import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
	static int[][] map, ch;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
        
		List<Point> cheezeList = new ArrayList<>();
		map = new int[N][M];
		int cheeze = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int cell = Integer.parseInt(st.nextToken());
				if(cell == 1) {
					cheezeList.add(new Point(i, j));
					cheeze++;
				}
				map[i][j] = cell;
			}
		}
        
		int res = 0;
		int remain = 0;
		while(cheeze != 0) {
			ch = new int[N][M];
			remain = cheeze;
			DFS(0,0);

			List<Integer> delete = new ArrayList<>();
			for(int i = 0; i < cheezeList.size(); i++) {
				for(int[] dir : DIRECTIONS) {
					int ny = cheezeList.get(i).y + dir[0];
					int nx = cheezeList.get(i).x + dir[1];
					if(map[ny][nx] == -1) {
						delete.add(i);
						break;
					}
				}
			}

			for(int i = delete.size() - 1; i >= 0; i--) {
				int now = delete.get(i);
				map[cheezeList.get(now).y][cheezeList.get(now).x] = -1;
				cheeze--;
				cheezeList.remove(now);
			}
			res++;
		}
		System.out.println(res + "\n" + remain);
	}
	
	static void DFS(int y, int x) {
		map[y][x] = -1;
		for(int[] dir : DIRECTIONS) {
			int ny = y + dir[0];
			int nx = x + dir[1];
			if(ny >= 0 && ny < N && nx >= 0 && nx < M && ch[ny][nx] == 0) {
				ch[ny][nx] = 1;
				if(map[ny][nx] == 0 || map[ny][nx] == -1)
                    DFS(ny,nx);
			}
		}
	}
}

class Point{
	int x, y;

    Point(int y, int x) {
		this.x = x; 
		this.y = y;
	}
}