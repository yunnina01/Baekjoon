import java.io.*;
import java.util.*;

public class Main {
	static int[] tree;
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());

        size = 2;
		while(true) {
			if(size >= 100001) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];

		int[] height = new int[N];
		for(int i = 0; i < N; i++)
			height[i] = Integer.parseInt(br.readLine());
		Arrays.sort(height);

        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> comp = new HashMap<>();
		int idx = 0;
		for(int i = 0; i < N; i++) {
			if(!map.containsKey(height[i])) {
				map.put(height[i], idx++);
				comp.put(idx - 1, height[i]);
			}
			tree[map.get(height[i]) + size / 2]++;
		}

		construct();

		int[] S = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			S[i] = Integer.parseInt(st.nextToken());

        int[] res = new int[N];
		for(int i = N - 1; i >= 0; i--) {
			idx = findNum(S[i] + 1, 1);
			res[i] = comp.get(idx);
			update(idx);
		}

		for(int i = 0; i < N; i++)
			sb.append(res[i] + "\n");
		System.out.print(sb);
	}
	
	static void construct() {
		for(int i = size / 2 - 1; i > 0; i--)
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
	}
	
	static int findNum(int num, int node) {
		if(node >= size / 2)
			return node - size / 2;
		if(num > tree[node * 2])
			return findNum(num - tree[node * 2], node * 2 + 1);
		return findNum(num, node * 2);
	}
	
	static void update(int i) {
		i += size / 2;
		tree[i]--;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
	}
}