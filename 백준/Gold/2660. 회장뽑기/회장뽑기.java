import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(map[i], (int)1e9);
			map[i][i] = 0;
		}

		while(true) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;

			if(A == -2 && B == -2)
                break;
			map[A][B] = map[B][A] = 1;
		}
		floyd();

		List<Integer> candidate = new ArrayList<Integer>();
		int res = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			int min = Integer.MIN_VALUE;
			for(int j = 0; j < N; j++) {
				if(map[i][j] != (int)1e9)
					min = Math.max(min, map[i][j]);
			}

			if(min < res) {
				candidate = new ArrayList<Integer>();
				candidate.add(i + 1);
				res = min;
			} else if(min == res)
                candidate.add(i + 1);
		}

		sb.append(res + " " + candidate.size() + "\n");
		for(int i = 0; i < candidate.size(); i++)
			sb.append(candidate.get(i) + " ");
		System.out.println(sb);
	}
	
	static void floyd() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(map[j][i] + map[i][k] < map[j][k])
						map[j][k] = map[j][i] + map[i][k];
				}
			}
		}
	}
}