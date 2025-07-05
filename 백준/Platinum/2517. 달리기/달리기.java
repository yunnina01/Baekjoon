import java.io.*;
import java.util.*;

public class Main {
	static List<Integer> comp;
	static int[] tree, seq;
	static int size;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
        size = 2;
		while(true) {
			if(size >= 500000) {
				size *= 2;
				break;
			}
			size *= 2;
		}
		tree = new int[size];
		
		int N = Integer.parseInt(br.readLine());

        comp = new ArrayList<>();
		seq = new int[N];
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			comp.add(num);
			seq[i] = num;
		}
		Collections.sort(comp);

		for(int i = 0; i < N; i++) {
			int num = Collections.binarySearch(comp, seq[i]) + 1;
			update(num - 1);
			sb.append(query(1, 0, size / 2 - 1, num, size / 2 - 1) + 1 + "\n");
		}
		System.out.print(sb);
	}
	
	static void update(int i) {
		i += size / 2;
		tree[i]++;
		while(i > 1) {
			i /= 2;
			tree[i] = tree[i * 2] + tree[i * 2 + 1];
		}
 	}

	static int query(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
            return 0;
		if(left <= start && right >= end)
            return tree[node];
		int mid = (start + end) / 2;
		return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
	}
}