import java.io.*;
import java.util.*;

public class Main {
	static List<List<Integer>> adj;
	static int[] laptops;
	static boolean[] visit;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		adj = new ArrayList<>();
		for(int i = 0; i < N; i++)
			adj.add(new ArrayList<>());

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			adj.get(a).add(b);
		}
		
		laptops = new int[N];
		Arrays.fill(laptops, -1);
		int res = 0;
		for(int i = 0; i < N; i++) {
			visit = new boolean[N];
			if(match(i))
				res++;
		}
        System.out.println(res);
	}
	
	static boolean match(int student) {
		for(int next : adj.get(student)) {
			if(visit[next])
				continue;

			visit[next] = true;
			if(laptops[next] == -1 || match(laptops[next])) {
				laptops[next] = student;
				return true;
			}
		}
		return false;
	}
}