import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		List<Query> queries = new ArrayList<>();
		int[] imos = new int[100002];
		int[] startSum = new int[100002];
		int[] endSum = new int[100002];
		for(int i = 0; i < N; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			queries.add(new Query(a, b));
			imos[a] ++;
			imos[b] --;
			startSum[a] ++;
			endSum[b] ++;
		}

		for(int i = 1; i <= 100001; i ++){
			imos[i] += imos[i - 1];
			startSum[i] += startSum[i - 1];
			endSum[i] += endSum[i - 1];
		}

		int s1 = Integer.MIN_VALUE;
		int s2 = Integer.MIN_VALUE;
		for(int i = 0; i <= 100001; i ++)
			s2 = Math.max(s2, imos[i]);

		for(int i = 0; i < N; i ++) {
			Query now = queries.get(i);
			int val = N;
			val -= startSum[100001] - startSum[now.b - 1];
			val -= endSum[now.a];
			s1 = Math.max(val, s1);
		}
		System.out.println(s1 + " " + s2);
	}
}

class Query {
	int a, b;

	Query(int a, int b) {
		this.a = a;
		this.b = b;
	}
}