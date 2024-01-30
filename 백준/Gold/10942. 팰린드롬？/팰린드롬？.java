import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		int[] board = new int[N + 1];
		for(int i = 1; i <= N; i++)
			board[i] = Integer.parseInt(st.nextToken());
		
		boolean[][] isPalindrome = new boolean[N + 1][N + 1];
		for(int i = 1; i <= N; i++)
			isPalindrome[i][i] = true;
		for(int i = 1; i < N; i++) {
			if(board[i] == board[i + 1])
				isPalindrome[i][i + 1] = true;
		}
		
		for(int len = 2; len <= N - 1; len++) {
			for(int i = 1; i + len <= N; i++) {
				if(board[i] == board[i + len])
					isPalindrome[i][i + len] = isPalindrome[i + 1][i + len - 1];
			}
		}
		
		int M = Integer.parseInt(br.readLine());
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if(isPalindrome[s][e])
				sb.append("1\n");
			else
				sb.append("0\n");
		}
		bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
	}
}