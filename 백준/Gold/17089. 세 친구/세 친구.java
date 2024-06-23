import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 12000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		boolean[][] isFriend = new boolean[N + 1][N + 1];
		int[] cnt = new int[N + 1];
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			isFriend[A][B] = isFriend[B][A] = true;
			cnt[A]++;
			cnt[B]++;
		}

		int res = INF;
		for(int a = 1; a <= N; a++) {
			for(int b = a + 1; b <= N; b++) {
				if(!isFriend[a][b])
					continue;

				for(int c = b + 1; c <= N; c++) {
					if(!isFriend[b][c] || !isFriend[a][c])
						continue;

					int fc = cnt[a] + cnt[b] + cnt[c] - 6;
					if(fc < res)
						res = fc;
				}
			}
		}
		System.out.println(res == INF ? -1 : res);
	}
}