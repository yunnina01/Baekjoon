import java.io.*;
import java.util.*;

public class Main {
	static int sqrtN;

	static class Query implements Comparable<Query> {
		int x, y, idx;

		Query(int x, int y, int idx) {
			this.x = x;
			this.y = y;
			this.idx = idx;
		}

        @Override
		public int compareTo(Query o) {
			int cx = x / sqrtN;
			int cy = o.x / sqrtN;
			if(cx == cy)
				return Integer.compare(y, o.y);
			return Integer.compare(cx, cy);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
        int[] A = new int[N + 1];
		for(int i = 1; i <= N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine());

        Query[] query = new Query[M];
		for(int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            query[k] = new Query(i, j, k);
		}

		sqrtN = (int)Math.sqrt(N);
		Arrays.sort(query);

		int[] cnt = new int[1000001];
		int[] res = new int[M];
		int type = 0, x = 1, y = 0;
		for(int i = 0; i < M; i++) {
			while(query[i].x > x) {
				if(--cnt[A[x++]] == 0)
					--type;
            }
			while(query[i].x < x) {
				if(++cnt[A[--x]] == 1)
					++type;
            }
			while(y > query[i].y) {
				if(--cnt[A[y--]] == 0)
					--type;
            }
			while(y < query[i].y) {
				if(++cnt[A[++y]] == 1)
					++type;
            }
			res[query[i].idx] = type;
		}

		for(int i = 0; i < M; i++)
			bw.write(res[i] + "\n");
        br.close();
		bw.flush();
		bw.close();
	}
}