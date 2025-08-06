import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj;
	static int row[];
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		boolean[][] board = new boolean[R][C];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
		}

		adj = new ArrayList<>();
		for(int i = 0; i < R; i++)
			adj.add(new ArrayList<>());

		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(!board[i][j])
					adj.get(i).add(j);
			}
		}

		row = new int[C];
		visit = new boolean[C];
		Arrays.fill(row, -1);
		int res = 0;
		for(int i = 0; i < R; i++) {
			Arrays.fill(visit, false);
			if(match(i))
				res++;
		}
		System.out.println(res);
	}

    static boolean match(int node) {
		for(int col : adj.get(node)) {
			if(visit[col])
				continue;
			visit[col] = true;
			if(row[col] == -1 || match(row[col])) {
				row[col] = node;
				return true;
			}
		}
		return false;
	}
}