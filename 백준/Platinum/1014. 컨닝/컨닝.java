import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
	static List<List<Integer>> connected;
	static List<Integer> target;
	static int[] matchA, matchB;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int C = Integer.parseInt(br.readLine());
		
		while(C-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			char[][] map = new char[N][M];
			for(int i = 0; i < N; i++)
				map[i] = br.readLine().toCharArray();

			int size = N * M;
			connected = new ArrayList<>();
			for(int i = 0; i < size; i++)
				connected.add(new ArrayList<>());
			target = new ArrayList<>();
			int blank = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(map[i][j] != '.') {
						blank++;
						continue;
					}
					
					int now = i * M + j;
					if(j % 2 == 0)
						target.add(now);

					for(int[] dir : DIRECTIONS) {
						int ni = i + dir[0], nj = j + dir[1];	
						if(ni < 0 || nj < 0 || ni >= N || nj >= M || map[ni][nj] != '.')
							continue;
						int next = ni * M + nj;
						connected.get(now).add(next);
					}
				}
			}

			matchA = new int[size];
			matchB = new int[size];
			visit = new boolean[size];
			int res = 0;
			Arrays.fill(matchB, -1);
			
			for(int t : target) {
				Arrays.fill(visit, false);
				if(DFS(t))
					res++;
			}
			sb.append(size - res - blank + "\n");
		}
		bw.write(sb.toString());
		br.close();
		bw.flush();
		bw.close();
	}
	
	static boolean DFS(int x) {
		for(int next : connected.get(x)) {
			if(visit[next])
				continue;
			visit[next] = true;
	        if(matchB[next] == -1 || DFS(matchB[next])) {
	            matchA[x] = next;
	            matchB[next] = x;
	            return true;
	        }
	    }
		return false;
	}
}