import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb;
	static int map[][];

	public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			int cnt = 0;
            st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				cnt += map[i][j];
			}
			if(cnt % 2 != 0) {
				System.out.print(-1);
				return;
			}
		}
		
		sb = new StringBuilder();
		DFS(0);
		System.out.println(sb);
	}
	
	static void DFS(int now) {
		for(int i = 0; i < map.length; i++) {
			while(map[now][i] > 0) {
				map[now][i]--;
				map[i][now]--;
				DFS(i);
			}
		}
		sb.append((now + 1) + " ");		
	}
}