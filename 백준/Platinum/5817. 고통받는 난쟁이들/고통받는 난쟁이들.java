import java.io.*;
import java.util.*;

public class Main {
	static Node[] tree;
	static int size = 2;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

        while(true) {
			if(size >= N) {
				size *= 2;
				break;
			}
			size *= 2;
		}

		int pos[] = new int[N];
		int idx[] = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken()) - 1;
			pos[num] = i;
			idx[i] = num;
		}

		tree = new Node[size];
		for(int i = 0; i < size / 2; i++) {
			if(i < N) {
				int tmp = pos[i];
				tree[size / 2 + i] = new Node(tmp, tmp);
			} else
                tree[size / 2 + i] = new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);
		}

		construct();

		for(int i = 0 ; i < M ; i ++) {
			st = new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken()) == 1) {
				int X = Integer.parseInt(st.nextToken()) - 1;
				int Y = Integer.parseInt(st.nextToken()) - 1;

				update(idx[X], Y);
				update(idx[Y], X);

				int tmp = idx[X];
				idx[X] = idx[Y];
				idx[Y] = tmp;
			} else {
				int A = Integer.parseInt(st.nextToken()) - 1;
				int B = Integer.parseInt(st.nextToken()) - 1;

				int dif = getMax(1, 0, size / 2 - 1, A, B) - getMin(1, 0, size / 2 - 1, A, B);
				int difidx = B - A;
 				if(dif == difidx)
 					sb.append("YES\n");
 				else
                    sb.append("NO\n");
			}
		}
		System.out.print(sb);
	}
	
	static void construct() {
		for(int i = size / 2 - 1; i > 0; i--) {
			int min = Math.min(tree[i * 2].min, tree[i * 2 + 1].min);
			int max = Math.max(tree[i * 2].max, tree[i * 2 + 1].max);
			tree[i] = new Node(min, max); 
		}
	}
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i].min = val;
		tree[i].max = val;
		while(i > 1) {
			i /= 2;
			tree[i].min = Math.min(tree[i * 2].min, tree[i * 2 + 1].min);
			tree[i].max = Math.max(tree[i * 2].max, tree[i * 2 + 1].max);
		}
	}
	
	static int getMin(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
            return Integer.MAX_VALUE;
		if(left <= start && right >= end)
            return tree[node].min;
		int mid = (start + end) / 2;
		return Math.min(getMin(node * 2, start, mid, left, right), getMin(node * 2 + 1, mid + 1, end, left, right));
	}
	
	static int getMax(int node, int start, int end, int left, int right) {
		if(left > end || right < start)
            return Integer.MIN_VALUE;
		if(left <= start && right >= end)
            return tree[node].max;
		int mid = (start + end) / 2;
		return Math.max(getMax(node * 2, start, mid, left, right), getMax(node * 2 + 1, mid + 1, end, left, right));
	}
}

class Node {
	int min, max;

	Node(int min, int max) {
		this.min = min;
		this.max = max;
	}
}