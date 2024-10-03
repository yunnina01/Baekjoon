import java.io.*;

public class Main {
	static final int DIRECTIONS[][][] = {{{-1, 0}, {1, -1}, {1, 1}}, {{1, 0}, {-1, 1}, {-1, -1}}};
	static boolean visit[][];
	static int N, res;

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        N = Integer.parseInt(br.readLine());

		visit = new boolean[51][51];
		visit[26][25] = true;
		backtracking(25, 25, 0, 1, 0);
		System.out.print(res);
	}

	static void backtracking(int y, int x, int prev, int flag, int depth) {
		if(visit[y][x] || depth == N) {
			if(depth == N && visit[y][x])
                res++;
			return;
		}
		
		visit[y][x] = true;
		int nextFlag = (flag + 1) % 2;
		for(int i = 0; i < 3; i++) {
			if(i == prev) 
				continue;
			
			int ny = y + DIRECTIONS[flag][i][0];
			int nx = x + DIRECTIONS[flag][i][1];
			backtracking(ny, nx, i, nextFlag, depth + 1);		
		}
		visit[y][x] = false;
	}
}