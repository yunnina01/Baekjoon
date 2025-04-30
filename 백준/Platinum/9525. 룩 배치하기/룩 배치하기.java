import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> rookList;
	static int[] rook;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		
		int N = Integer.parseInt(br.readLine());
		
        char[][] board = new char[N][N];
		for(int i = 0; i < N; i++)
            board[i] = br.readLine().toCharArray();

		rookList = new ArrayList<>();
		int[][] row = new int[N][N];
		int cnt = -1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if((j == 0 || board[i][j - 1] == 'X') && board[i][j] == '.') {
					rookList.add(new ArrayList<>());
					cnt++;
				}
				row[i][j] = cnt;
			}
		}

		cnt = -1;
		for(int j = 0; j < N; j++) {
			for(int i = 0; i < N; i++) {
				if((i == 0 || board[i - 1][j] == 'X') && board[i][j] == '.')
					cnt++;
				if(board[i][j] == '.')
					rookList.get(row[i][j]).add(cnt);
			}
		}

		rook = new int[cnt + 1];
		visited = new boolean[cnt + 1];
		Arrays.fill(rook, -1);
		int res = 0;
		for(int i = 0; i < rookList.size(); i++) {
			Arrays.fill(visited, false);
			if(match(i))
				res++;
		}
		sb.append(res);
		
		bw.write(sb.toString());
		bw.flush();
	}
	
	static boolean match(int node) {
		for(int now : rookList.get(node)) {
			if(visited[now])
				continue;
			visited[now] = true;
			if(rook[now] == -1 || match(rook[now])) {
				rook[now] = node;
				return true;
			}
		}
		return false;
	}
}