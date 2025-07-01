import java.io.*;
import java.util.*;

public class Main {
	static int[][][] dp;
	static Deque<Character> dq;
	static String str, devil, angel;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		str = br.readLine();
		devil = br.readLine();
		angel = br.readLine();

		dp = new int[devil.length()][2][str.length()];
		for(int i = 0; i < devil.length(); i++) { 
			for(int j = 0; j < 2; j++)
				Arrays.fill(dp[i][j], -1);
		}

        dq = new LinkedList<>();
		for(int i = 0; i < str.length(); i++)
            dq.offer(str.charAt(i));

		int res = 0;
		for(int i = 0; i < devil.length(); i++) {
			if(devil.charAt(i) == dq.peek()) {
				char next = dq.poll();
				res += recur(i, 0, 0);
				dq.addFirst(next);
			}
		}

		for(int i = 0; i < angel.length(); i++) {
			if(angel.charAt(i) == dq.peek()) {
				char next = dq.poll();
				res += recur(i, 1, 0);
				dq.addFirst(next);
			}
		}
		System.out.println(res);
	}
	
	static int recur(int x, int y, int idx) {
		if(dp[x][y][idx] != -1)
            return dp[x][y][idx];
		if(dq.isEmpty())
            return dp[x][y][idx] = 1;

		int sum = 0;
		if(y == 0) {
			for(int i = x + 1; i < angel.length(); i++) {
				if(angel.charAt(i) == dq.peek()) {
					char next = dq.poll();
					sum += recur(i, 1, idx + 1);
					dq.addFirst(next);
				}
			}
		} else {
			for(int i = x + 1; i < devil.length(); i++) {
				if(devil.charAt(i) == dq.peek()) {
					char next = dq.poll();
					sum += recur(i, 0, idx + 1);
					dq.addFirst(next);
				}
			}
		}
		return dp[x][y][idx] = sum;
	}
}