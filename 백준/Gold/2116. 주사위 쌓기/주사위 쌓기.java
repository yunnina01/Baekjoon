import java.io.*;
import java.util.*;

public class Main {
	static final int[] rev = {5, 3, 4, 1, 2, 0};
	static int[][] dice;
	static int N;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		dice = new int[6][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 6; j++)
				dice[j][i] = Integer.parseInt(st.nextToken());
		}

		int res = Integer.MIN_VALUE;
		for(int i = 0; i < 6; i++)
			res = Math.max(res, recur(0, i + 1, 0));
		System.out.println(res);
	}
	
	static int recur(int cnt, int last, int sum) {
		if(cnt == N)
			return sum;

		int reverse = -1;
		for(int i = 0; i < 6; i++) {
			if(dice[i][cnt] == last)
                reverse = dice[rev[i]][cnt];
		}

		if(Math.max(last, reverse) != 6)
            return recur(cnt + 1, reverse, sum + 6);
		else if (Math.min(last, reverse) != 5)
            return recur(cnt + 1, reverse, sum + 5);
		return recur(cnt + 1, reverse, sum + 4);		
	}
}