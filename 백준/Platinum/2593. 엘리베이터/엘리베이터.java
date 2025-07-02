import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> destination, elevator;
	static Stack<Integer> stack;
	static int[] visit;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		destination = new ArrayList<>();
		for(int i = 0; i <= N; i++)
			destination.add(new ArrayList<>());
		elevator = new ArrayList<>();
		for(int i = 0; i <= M; i++)
			elevator.add(new ArrayList<>());

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());

            while(X <= N) {
				destination.get(X).add(i);
				elevator.get(i).add(X);
				X += Y;
			}
		}

		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

        stack = new Stack<>();
		visit = new int[M];
		int ret = BFS(A, B);
		if(ret != -1) {
			recur(ret);
			sb.append(stack.size() + "\n");
			while(!stack.isEmpty())
				sb.append((stack.pop() + 1) + "\n");
			System.out.print(sb);
		} else
			System.out.println(-1);
	}

	static int BFS(int start, int end) {
		Queue<Integer> queue = new LinkedList<>();
		HashSet<Integer> set = new HashSet<>();
		Arrays.fill(visit, -1);
		for(int s : destination.get(start)) {
			queue.offer(s);
			visit[s] = Integer.MAX_VALUE;
		}
		for(int e : destination.get(end))
			set.add(e);

		while(!queue.isEmpty()) {
			int len = queue.size();
			for(int i = 0; i < len; i++) {
				int now = queue.poll();
				if(set.contains(now))
					return now;

				for(int j = 0; j < elevator.get(now).size(); j++) {
					int floor = elevator.get(now).get(j);
					for(int k = 0; k < destination.get(floor).size(); k++) {
						int next = destination.get(floor).get(k);
						if(visit[next] == -1) {
							visit[next] = now;
							queue.offer(next);
						}
					}
				}
			}
		}
		return -1;
	}

	static void recur(int now) {
		stack.push(now);
		if(visit[now] == Integer.MAX_VALUE)
			return;
		recur(visit[now]);
	}
}