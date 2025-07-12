import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj;
	static int[] tape;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		boolean[][] hole = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			char[] str = br.readLine().toCharArray();
			for(int j = 0; j < M; j++) {
				if(str[j] == '*')
					hole[i][j] = true;
			}
		}

		adj = new ArrayList<>();
		int[][] row = new int[N][M];
		int cnt = -1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(hole[i][j]) {
					if(j == 0 || !hole[i][j - 1])
						cnt++;
					row[i][j] = cnt;
					adj.add(new ArrayList<>());
				}
			}
		}

		int num = cnt;
		cnt = -1;
		for(int j = 0; j < M; j++) {
			for(int i = 0; i < N; i++) {
				if(hole[i][j]) {
					if(i == 0 || !hole[i - 1][j])
						cnt++;
					adj.get(row[i][j]).add(cnt);
				}
			}
		}

		int res = 0;
		tape = new int[cnt + 1];
		visit = new boolean[num + 1];
		Arrays.fill(tape, -1);
		for(int i = 0; i <= num; i++) {
			Arrays.fill(visit, false);
			if(match(i))
				res++;
		}
		System.out.print(res);
	}
	
	static boolean match(int x) {
		if(visit[x])
			return false;
		visit[x] = true;
		for(int next : adj.get(x)) {
			if(tape[next] == -1 || match(tape[next])) {
				tape[next] = x;
				return true;
			}
		}
		return false;
	}
}