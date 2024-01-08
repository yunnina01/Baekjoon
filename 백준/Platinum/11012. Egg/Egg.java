import java.io.*;
import java.util.*;

public class Main {
    static final int RANGE = 100000;
	static Node[] parades;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer  st;

		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());

			int[][] points = new int[n + 1][2];
			for(int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine());
				points[i][0] = Integer.parseInt(st.nextToken());
				points[i][1] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(points, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[0] == o2[0])
						return Integer.compare(o1[1], o2[1]);
					return Integer.compare(o1[0], o2[0]);
				}
			});

			Map<Integer, Integer> map = new HashMap<>();
			for(int i = 1; i <= n; i++)
				map.put(points[i][0], i);

			parades = new Node[n + 1];
			parades[0] = new Node(0, null, null);
            parades[0].left = parades[0];
            parades[0].right = parades[0];
			for(int i = 1; i <= n; i++)
				parades[i] = update(parades[i - 1], 0, RANGE, points[i][1]);

			int res = 0;
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int l = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());

				int lb = lowerBound(points, l, r);
				int ub = upperBound(points, l, r);
				if(lb == -1 || ub == -1)
					continue;

				if(ub >= 1)
					res += query(parades[map.get(points[ub][0])], 0, RANGE, b, t);
				if(lb >= 2)
					res -= query(parades[map.get(points[lb - 1][0])], 0, RANGE, b, t);
			}
			bw.write(res + "\n");
		}
        br.close();
        bw.flush();
		bw.close();
	}

	static int lowerBound(int[][] points, int left, int right) {
		int low = 1;
		int high = points.length - 1;

		while(low < high) {
			int mid = (low + high) >> 1;
			if(points[mid][0] < left)
				low = mid + 1;
			else
				high = mid;
		}
		if(points[low][0] < left)
			return -1;
		return low;
	}

	static int upperBound(int[][] points, int left, int right) {
		int low = 1;
		int high = points.length - 1;

		while(low < high) {
			int mid = (low + high) >> 1;
			if(points[mid][0] <= right)
				low = mid + 1;
			else
				high = mid;
		}
		if(points[low][0] <= right)
			return low;
		if(low - 1 >= 0 && points[low - 1][0] <= right)
			return low - 1;
		return -1;
	}

	static Node update(Node p, int start, int end, int idx) {
		if(idx < start || idx > end)
			return p;
		if(start == end)
			return new Node(p.v + 1, null, null);

		int mid = (start + end) >> 1;
		return new Node(p.v + 1, update(p.left, start, mid, idx), update(p.right, mid + 1, end, idx));
	}

	static int query(Node p, int start, int end, int left, int right) {
		if(start > right || end < left)
			return 0;
		if(start >= left && end <= right)
			return p.v;

		int mid = (start + end) >> 1;
		return query(p.left, start, mid, left, right) + query(p.right, mid + 1, end, left, right);
	}
}

class Node {
	int v;
	Node left, right;

	Node(int v, Node left, Node right) {
		this.v = v;
		this.left = left;
		this.right = right;
	}
}