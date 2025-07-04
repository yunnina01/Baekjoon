import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj;
	static int[] src;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
        int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

        boolean[][] board = new boolean[N][N];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}

		adj = new ArrayList<>();
		int[][] row = new int[N][N];
		int cnt = -1;
		for(int k = 0; k < N; k++) {
			int i = 0;
			int j = k;
			while(i < N && j < N) {
				if((i == 0 || board[i - 1][j - 1] == true) && board[i][j] == false) {
					adj.add(new ArrayList<>());
					cnt++;
				}
				row[i][j] = cnt;
				i++;
				j++;
			}
		}
		for(int k = 1; k < N; k++) {
			int i = k;
			int j = 0;
			while(i < N && j < N) {
				if((j == 0 || board[i - 1][j - 1] == true) && board[i][j] == false) {
					adj.add(new ArrayList<>());
					cnt++;
				}
				row[i][j] = cnt;
				i++;
				j++;
			}
		}

		cnt = -1;
		for(int k = 0; k < N; k++) {
			int i = 0;
			int j = k;
			while(i < N && j >= 0) {
				if((i == 0 || board[i - 1][j + 1] == true) && board[i][j] == false)
					cnt++;
				if(board[i][j] == false)
					adj.get(row[i][j]).add(cnt);
				i++;
				j--;
			}
		}
		for(int k = 1; k < N; k++) {
			int i = k;
			int j = N - 1;
			while(i < N && j >= 0) {
				if((j == N - 1 || board[i - 1][j + 1] == true) && board[i][j] == false)
					cnt++;
				if(board[i][j] == false)
					adj.get(row[i][j]).add(cnt);
				i++;
				j--;
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
	
	static boolean match(int node) {
		for(int next : adj.get(node)) {
			if(visit[next])
				continue;
			visit[next] = true;
			if(src[next] == -1 || match(src[next])) {
				src[next] = node;
				return true;
			}
		}
		return false;
	}
}