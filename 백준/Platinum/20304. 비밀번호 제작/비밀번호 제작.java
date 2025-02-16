import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		Queue<Integer> queue = new ArrayDeque<>();
		int[] safeDis = new int[N + 1];
		Arrays.fill(safeDis, -1);
		
		st = new StringTokenizer(br.readLine());
		int[] p = new int[M + 1];
		for(int i = 1; i <= M; i++) {
			p[i] = Integer.parseInt(st.nextToken());
			safeDis[p[i]] = 0;
			queue.offer(p[i]);
		}

		int res = 0;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			if(res < safeDis[now])
                res = safeDis[now];

			for(int i = 1; i <= N; i <<= 1) {
				int nx = now ^ i;
				if(nx > N || safeDis[nx] >= 0)
                    continue;
				safeDis[nx] = safeDis[now] + 1;
				queue.offer(nx);
			}
		}
		System.out.println(res);
	}
}