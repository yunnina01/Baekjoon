import java.io.*;
import java.util.*;

public class Main {
	static final int MOD = 1_000_003;
	static int N, M;
	static long[][] roads;
	static HashMap<Integer, long[][]> visit = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = N * 5;
		int S = (Integer.parseInt(st.nextToken()) - 1) * 5;
		int E = (Integer.parseInt(st.nextToken()) - 1) * 5;
		int T = Integer.parseInt(st.nextToken());

		roads = new long[M][M];
		for(int i = 0; i < N; i++) {
			for(int j = 1; j < 5; j++) {
				int idx = i * 5 + j;
				roads[idx][idx - 1] = 1;
			}
		}

		for(int i = 0; i < N; i++) {
			String roadInfo = br.readLine();
			for(int j = 0; j < N; j++) {
				int data = roadInfo.charAt(j) - '0';
				int row = i * 5, col = j * 5;
				if(data <= 1)
					roads[row][col] = data;
				else if(data > 1)
					roads[row][col + data - 1] = 1;
			}
		}

		roads = matrixPower(T, roads);
		bw.write(roads[S][E] + "\n");
		br.close();
		bw.flush();
		bw.close();
	}

	static long[][] matrixPower(int p, long[][] arr) {
		if(p == 1)
			return arr;
		if(visit.containsKey(p))
			return visit.get(p);

		int half = p / 2;
		long[][] left = matrixPower(half, arr);
		if(p % 2 == 0) {
			visit.put(half, left);
			return matrixProduct(left, left);
		} else {
			long[][] right = matrixPower(half + 1, arr);
			visit.put(half, left);
			visit.put(half + 1, right);
			return matrixProduct(left, right);
		}
	}

	static long[][] matrixProduct(long[][] a, long[][] b) {
		long[][] c = new long[M][M];
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < M; j++) {
				for(int k = 0; k < M; k++) {
					c[i][j] += (a[i][k] * b[k][j]) % MOD;
					c[i][j] %= MOD;
				}
			}
		}
		return c;
	}
}