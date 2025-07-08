import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj;
	static int[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		st.nextToken();

		int[] codes = new int[N];
		for(int i = 0; i < N; i++)
			codes[i] = Integer.valueOf(br.readLine(), 2);

		adj = new ArrayList<>();
		for(int i = 0; i < N; i++)
			adj.add(new ArrayList<>());

		for(int i = 0; i < N; i++) {
			for(int j = i + 1; j < N; j++) {
				if(isBin(codes[i] ^ codes[j])) {
					adj.get(i).add(j);
					adj.get(j).add(i);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken()) - 1;
		int B = Integer.parseInt(st.nextToken()) - 1;

		Stack<Integer> stack = new Stack<>();
		visit = new int[N];
		Arrays.fill(visit, -1);
		if(BFS(A, B) > 0) {
			int now = B;
			stack.push(B);
			while(now != A) {
				stack.push(visit[now]);
				now = visit[now];
			}
			while(!stack.isEmpty())
				sb.append((stack.pop() + 1) + " ");
			System.out.println(sb);
		} else
			System.out.println(-1);
	}

	static boolean isBin(int n) {
		double code = n;
		if(code == 1)
            return true;

		while(code >= 2)
			code /= 2;
		if(code == 1)
            return true;
		return false;
	}
	
	static int BFS(int s, int e) {
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(s);
		visit[s] = s;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			if(now == e)
                return 1;
			for(int next : adj.get(now)) {
				if(visit[next] == -1) {
					queue.offer(next);
					visit[next] = now;
				}
			}
		}
		return -1;
	}
}