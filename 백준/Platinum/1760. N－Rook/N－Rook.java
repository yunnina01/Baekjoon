import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj;
	static int[] src;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] board = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}

		adj = new ArrayList<>();
		int[][] row = new int[N][M];
		int cnt = -1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if((j == 0 || board[i][j - 1] == 2) && board[i][j] != 2) {
					adj.add(new ArrayList<>());
					cnt++;
				}
				row[i][j] = cnt;
			}
		}

		cnt = -1;
		for(int j = 0; j < M; j++) {
			for(int i = 0; i < N; i++) {
				if((i == 0 || board[i - 1][j] == 2) && board[i][j] != 2)
					cnt++;
				if(board[i][j] == 0)
					adj.get(row[i][j]).add(cnt);
			}
		}

		src = new int[cnt + 1];
		visit = new boolean[cnt + 1];
		Arrays.fill(src, -1);
		int res = 0;
		for(int i = 0; i < adj.size(); i++) {
			Arrays.fill(visit, false);
			if(match(i))
				res++;
		}
		System.out.println(res);
	}
	
	static boolean match(int x) {
		for(int next : adj.get(x)) {
			if(visit[next])
				continue;
			visit[next] = true;
			if(src[next] == -1 || match(src[next])) {
				src[next] = x;
				return true;
			}
		}
		return false;
	}
}