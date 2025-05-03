import java.io.*;
import java.util.*;

public class Main {
	static int[] tree, arr;
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
        StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

        size = 2;
		while(true) {
			if(size >= 65535) {
				size <<= 1;
				break;
			}
			size <<= 1;
		}

        arr = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());

		tree = new int[size];
		long res = 0;
		for(int i = 0; i < N; i++) {
			if(tree[1] != K) {
				update(arr[i], 1);
				if(tree[1] == K)
					res += query(1, (K + 1) / 2);
			} else {
				update(arr[i], 1);
				update(arr[i - K], -1);
				res += query(1, (K + 1) / 2);
			}
		}
		System.out.println(res);
	}
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i] += val;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
	
	static int query(int node, int num) {
		if(node >= size / 2)
			return node - size / 2;
		if(tree[node * 2] < num)
			return query(node * 2 + 1, num - tree[node * 2]);
		return query(node * 2, num);
	}
}