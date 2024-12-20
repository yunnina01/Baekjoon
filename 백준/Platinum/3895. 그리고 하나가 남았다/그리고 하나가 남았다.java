import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
	static int[] tree;
	static int size;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken()) - 1;

			if(N == 0 && K == 0 && M == -1)
				break;

			size = 2;
			while(size < N << 1)
				size <<= 1;

			tree = new int[size];
			for(int i = 0; i < N; i++)
				tree[size / 2 + i] = 1;

			construct();
			update(M + size / 2, 0);
			query(K, M);
		}
		System.out.print(sb);
	}

	static void construct() {
		for(int i = size / 2 - 1; i > 0; i--)
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
	}

	static void update(int i, int val) {
		tree[i] = val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}

	static void query(int K, int M) {
		update(M + size / 2, 0);
		int node = M;
		while(true) {
			if(tree[1] == 0)
				break;

			node += K;
			if(node % tree[1] == 0)
				node = tree[1];
			else if (node > tree[1])
				node %= tree[1];

			trace(node, 1);
			node--;
		}
	}

	static void trace(int des, int node) {
		if(node >= size / 2) {
			if(tree[1] == 1)
				sb.append((node - size / 2 + 1) + "\n");
			update(node, 0);
			return;
		}

		if(tree[node * 2] >= des)
			trace(des, node * 2);
		else
			trace(des - tree[node * 2], node * 2 + 1);
	}
}