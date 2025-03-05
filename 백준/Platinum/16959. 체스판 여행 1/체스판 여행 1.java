import java.io.*;
import java.util.*;

public class Main {
	static int[][][] moves;
	static int[][] map;
	static int N;

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		makeMoves();

		Queue<Node> queue = new LinkedList<>();
		map = new int[N][N];
		boolean[][][][] visit = new boolean[3][N][N][N * N + 1];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				if((map[i][j] = Integer.parseInt(st.nextToken())) == 1) {
					queue.offer(new Node(i, j, 1, 0, 0));
					queue.offer(new Node(i, j, 1, 1, 0));
					queue.offer(new Node(i, j, 1, 2, 0));
					visit[0][i][j][1] = true;
					visit[1][i][j][1] = true;
					visit[2][i][j][1] = true;
				}
			}
		}

		int res = N * N * 3;
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			if(map[now.r][now.c] == now.found + 1)
                now.found++;
			if(now.found == N * N) {
				res = now.t;
				break;
			}

			for(int i = 0; i < moves[now.piece].length; i++) {
				int nr = now.r + moves[now.piece][i][0];
                int nc = now.c + moves[now.piece][i][1];
				if(isIn(nr, nc) && !visit[now.piece][nr][nc][now.found]) {
					visit[now.piece][nr][nc][now.found] = true;
					queue.offer(new Node(nr, nc, now.found, now.piece, now.t + 1));
				}
			}

			for(int i = 1; i <= 2; i++) {
				if(!visit[(now.piece + i) % 3][now.r][now.c][now.found]){
					visit[(now.piece + i) % 3][now.r][now.c][now.found] = true;
					queue.offer(new Node(now.r, now.c, now.found, (now.piece + i) % 3, now.t + 1));
				}
			}
		}
		System.out.println(res);
	}

	static void makeMoves() {
		moves = new int[3][][];
		moves[0] = new int[4 * N - 4][2];
        moves[1] = new int[4 * N - 4][2];
		for(int i = 1; i < N; i++) {
			moves[0][0 * (N - 1) + i - 1][0] =  i;
            moves[0][0 * (N - 1) + i - 1][1] =  i;
			moves[0][1 * (N - 1) + i - 1][0] =  i;
            moves[0][1 * (N - 1) + i - 1][1] = -i;
			moves[0][2 * (N - 1) + i - 1][0] = -i;
            moves[0][2 * (N - 1) + i - 1][1] =  i;
			moves[0][3 * (N - 1) + i - 1][0] = -i;
            moves[0][3 * (N - 1) + i - 1][1] = -i;
			moves[1][0 * (N - 1) + i - 1][0] =  i;
            moves[1][0 * (N - 1) + i - 1][1] =  0;
			moves[1][1 * (N - 1) + i - 1][0] = -i;
            moves[1][1 * (N - 1) + i - 1][1] =  0;
			moves[1][2 * (N - 1) + i - 1][0] =  0;
            moves[1][2 * (N - 1) + i - 1][1] =  i;
			moves[1][3 * (N - 1) + i - 1][0] =  0;
            moves[1][3 * (N - 1) + i - 1][1] = -i;
		}
		moves[2] = new int[][]{{-1, 2}, {1, 2}, {2, -1}, {2, 1}, {-1, -2}, {1, -2}, {-2, -1}, {-2, 1}};
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}
}

class Node {
    int r, c, found, piece, t;

    Node(int r, int c, int found, int piece, int t) {
        this.r = r;
        this.c = c;
        this.found = found;
        this.piece = piece;
        this.t = t;
    }
}