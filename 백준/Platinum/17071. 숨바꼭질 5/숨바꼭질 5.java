import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		if(N == K) {
			System.out.println(0);
			return;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[][] visit = new boolean[500001][2];
		int time = 0;
		queue.offer(N);
		visit[N][0] = true;
		
		while(!queue.isEmpty()) {
			if(visit[K][time % 2]) {
				System.out.println(time);
				return;
			}
			time++;
			K += time;
			if(K > 500000)
                break;

            int size = queue.size();
			while(size-- > 0) {
				int now = queue.poll();
				for(int dir = 0; dir < 3; dir++) {
					int nx = next(now, dir);
					if (nx < 0 || nx > 500000 || visit[nx][time % 2])
                        continue;
					queue.offer(nx);
					visit[nx][time%2] = true;
				}
			}
		}
		System.out.println(-1);
	}

    static int next(int now, int dir) {
		if(dir == 0)
            return now - 1;
		else if(dir == 1)
            return now + 1;
		return now * 2;
	}
}