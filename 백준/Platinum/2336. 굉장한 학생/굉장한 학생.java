import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
	static int[] tree;
	static int size = 2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
        Student[] students = new Student[N];
		for(int i = 0; i < N; i++)
			students[i] = new Student();

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int a = Integer.parseInt(st.nextToken()) - 1;
			students[a].r1 = i;
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int b = Integer.parseInt(st.nextToken()) - 1;
			students[b].r2 = i;
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int c = Integer.parseInt(st.nextToken()) - 1;
			students[c].r3 = i;
		}

		Arrays.sort(students);

		while(size < N << 1)
			size <<= 1;
		tree = new int[size];
		for(int i = 1; i < size; i++)
			tree[i] = INF;
		int res = 0;

		for(int i = 0; i < N; i++) {
			Student now = students[i];
			if(query(1, 0, size / 2 - 1, 0, now.r2) > now.r3)
				res++;
			update(now.r2, now.r3);
		}
		System.out.println(res);
	}

	static int query(int node, int start, int end, int left, int right) {
		if(left > end || start > right)
			return Integer.MAX_VALUE;
		if(left <= start && right >= end)
			return tree[node];
		int mid = (start + end) >> 1;
		return Math.min(query(node << 1, start, mid, left, right), query((node << 1) + 1, mid + 1, end, left, right));
	}

	static void update(int idx, int val) {
		idx += (size >> 1);
		tree[idx] = val;
		while(idx > 1) {
			idx >>= 1;
			tree[idx] = Math.min(tree[idx << 1], tree[(idx << 1) + 1]);
		}
	}
}

class Student implements Comparable<Student> {
	int r1, r2, r3;

	@Override
	public int compareTo(Student o) {
		return this.r1 - o.r1;
	}
}