import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

		long[][] fenwick = new long[N + 1][N + 1];
        long[][] arr = new long[N + 1][N + 1];
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				arr[i][j] = Long.parseLong(st.nextToken());
				for(int r = i; r <= N; r += r & -r) {
					for(int c = j; c <= N; c += c & -c)
						fenwick[r][c] += arr[i][j];
				}
			}
		}

		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
			if(w == 0) {
				int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				for(int i = x; i <= N; i += i & -i) {
					for(int j = y; j <= N; j += j & -j)
						fenwick[i][j] += c-arr[x][y];
				}
				arr[x][y] = c;
			} else {
				int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
				long sum = 0;
				for(int i = x2; i > 0; i -= i & -i) {
					for(int j = y2; j > 0; j -= j & -j)
						sum += fenwick[i][j];
				}
				for(int i = x2; i > 0; i -= i & -i) {
					for(int j = y1 - 1; j > 0; j -= j & -j)
						sum -= fenwick[i][j];
				}
				for(int i = x1 - 1; i > 0; i -= i & - i) {
					for(int j = y2; j > 0; j -= j & -j)
						sum -= fenwick[i][j];
				}
				for(int i = x1 - 1; i > 0; i -= i & - i) {
					for(int j = y1 - 1; j > 0; j -= j & -j)
						sum += fenwick[i][j];
				}
				sb.append(sum + "\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}
}