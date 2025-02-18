import java.io.*;
import java.util.*;

public class Main {
	static Node[] adNode;
	static int dp[];
	static boolean[] visit;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader 	br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		adNode = new Node[N + 1];
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			adNode[a] = new Node(b, adNode[a]);
			adNode[b] = new Node(a, adNode[b]);
		}

		dp = new int[N + 1];
		visit = new boolean[N + 1];
		DFS(1);

		long cnt = nC2(N);
		long res = 0;
		for(int i = 2; i <= N; i++)
			res += cnt - nC2(N - dp[i]);
		System.out.println(res);
	}

    static int DFS(int node) {
		visit[node] = true;
		for(Node now = adNode[node]; now != null; now = now.next)
			if(!visit[now.node])
				dp[node] += DFS(now.node);
		return dp[node] += 1;
	}

	static long nC2(long n) {
		return n * (n - 1) / 2;
	}
}

class Node {
	int node;
	Node next;
	Node(int node, Node next) {
        this.node = node;
        this.next = next;
    }
}