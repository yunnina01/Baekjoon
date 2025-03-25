import java.io.*;
import java.util.*;

public class Main {
	static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[] parent;
	static int N, K;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

        Deque<int[]> dq = new ArrayDeque<>();
		int[][] map = new int[N][N];
		parent = new int[K + 1];
		boolean[][] visit = new boolean[N][N];
		int cnt = 0;
		for(int i = 1; i <= K; i++)
            parent[i] = i;

		for(int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

			map[x][y] = i;
			for(int[] dir : DIRECTIONS){
				int nx = x + dir[0];
                int ny = y + dir[1];
 			 	if(isIn(nx, ny) && map[nx][ny] != 0) {
					int ap = find(map[x][y]);
                    int bp = find(map[nx][ny]);
					if(ap != bp) {
						parent[Math.max(ap, bp)] = Math.min(ap, bp);
						cnt++;
					}
				}
			}
			dq.offer(new int[]{x, y, map[x][y]});
			visit[x][y] = true;
		}
        
		int res = 0;
		while(cnt < K - 1) {
			for(int size = dq.size(); size > 0; size--) {
				int[] now = dq.pollFirst();
				for(int[] dir : DIRECTIONS) {
					int nx = now[0] + dir[0];
                    int ny = now[1] + dir[1];
 			 		if(isIn(nx, ny)) {
						if(map[nx][ny] != 0) {
							int ap = find(now[2]);
                            int bp = find(map[nx][ny]);
							if(ap != bp) {
								parent[Math.max(ap, bp)] = Math.min(ap, bp);
								cnt++;
							}
						} else {
                            int nnx = nx + dir[0];
                            int nny = ny + dir[1];
							map[nx][ny] = now[2];
							if(isIn(nnx, nny) && map[nnx][nny] != 0) {
 			 			 		int ap = find(now[2]);
                                int bp = find(map[nnx][nny]);
	 			 			 	if (ap != bp) {
									parent[Math.max(ap, bp)] = Math.min(ap, bp);
									cnt++;
								}
							}
						}

						if(!visit[nx][ny]) {
							visit[nx][ny] = true;
							dq.offerLast(new int[]{nx, ny, now[2]});
						}
					}
				}
			}
			res++;
		}
        System.out.println(res);
	}

	static int find(int a){
		return parent[a] = parent[a] == a ? a : find(parent[a]);
	}

	static boolean isIn(int r, int c) {
	    return 0 <= r && r < N && 0 <= c && c < N;
	}
}