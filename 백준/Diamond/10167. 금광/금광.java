import java.io.*;
import java.util.*;

public class Main {
	static final long MIN = (long)-1e15;
	static Node[] tree;
	static int[] xArr;
	static int size = 2, xcnt, ycnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		List<Point> list = new ArrayList<Point>();
		xArr = new int[N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
			xArr[i] = x;
			list.add(new Point(x, y, w));
		}
		Arrays.sort(xArr);

        HashMap<Integer, Integer> xidx = new HashMap<>();
        int last = Integer.MIN_VALUE;
		xcnt = 0;
		for(int i = 0; i < xArr.length; i++) {
			if(xArr[i] != last)
				xidx.put(xArr[i],  ++xcnt);
		}
		Collections.sort(list);

		last = Integer.MIN_VALUE;
		ycnt = 0;
		for(int i = 0; i < N; i++) {
			Point tmp = list.get(i);
			if(tmp.y == last)
				tmp.y = ycnt;
			else {
				last = tmp.y;
				tmp.y = ++ycnt;
			}
			tmp.x = xidx.get(tmp.x);
		}
		
		while(true) {
			if(size >= xcnt) {
				size <<= 1;
				break;
			}
			size <<= 1;
		}

        long res = 0;
		for(int i = 1; i <= ycnt; i++) {
			clear();
			construct();
			int lastY = i;
			for(int j = 0; j < list.size(); j++) {
				Point tmp = list.get(j);
				if(tmp.y < i)
                    continue;
				if(tmp.y == lastY)
					update(tmp.x - 1, tmp.w);
				else {
					res = Math.max(res, getMax());
					lastY = tmp.y;
					j --;
				}
			}
			res = Math.max(res, getMax());
		}
		System.out.println(res);
	}
	
	static void clear() {
		tree = new Node[size];
		for(int i = 0; i < size / 2; i++) {
			if(i < xcnt)
				tree[size / 2 + i] = new Node(0, 0, 0, 0);
			else
                tree[size / 2 + i] = new Node(MIN, MIN, MIN, 0);
		}
	}
	
	static void construct() {
		for(int i = size / 2 - 1; i > 0; i--) {
			long Lv, Rv, v, a;
			Lv = Math.max(tree[i * 2].all + tree[i * 2 + 1].Lval, tree[i * 2].Lval);
			Rv = Math.max(tree[i * 2 + 1].all + tree[i * 2].Rval, tree[i * 2 + 1].Rval);
			v = Math.max(tree[i * 2].val, tree[i * 2 + 1].val);
			v = Math.max(tree[i * 2].Rval + tree[i * 2 + 1].Lval, v);
			a = tree[i * 2].all + tree[i * 2 + 1].all;
			tree[i] = new Node(Lv, Rv, v, a);
		}
	}
	
	static void update(int i, int val) {
		i += size / 2;
		tree[i].Lval += val;
		tree[i].Rval += val;
		tree[i].all += val;
		tree[i].val += val;
		while(i > 1) {
			i /= 2;
			long Rv, Lv, v, a;
			Rv = Math.max(tree[i * 2 + 1].all + tree[i * 2].Rval, tree[i * 2 + 1].Rval);
			Lv = Math.max(tree[i * 2].all + tree[i * 2 + 1].Lval, tree[i * 2].Lval);
			v = Math.max(tree[i * 2].val, tree[i * 2 + 1].val);
			v = Math.max(tree[i * 2].Rval + tree[i * 2 + 1].Lval, v);
			a = tree[i * 2].all + tree[i * 2 + 1].all;
			tree[i].Lval = Lv;
			tree[i].Rval = Rv;
			tree[i].val = v;
			tree[i].all = a;
		}
	}
	
	static long getMax() {
		long max = 0;
		max = Math.max(tree[1].Lval, tree[1].Rval);
		max = Math.max(tree[1].val, max);
		max = Math.max(tree[1].all, max);
		return max;
	}
}

class Point implements Comparable<Point>{
	int x, y, w;

    Point(int x, int y, int w) {
		this.x = x;
		this.y = y;
		this.w = w;
	}

	@Override
	public int compareTo(Point o) {
		return this.y - o.y;
	}
}

class Node{
	long Lval, Rval, val, all;

    Node(long Lval, long Rval, long val, long all) {
		this.Lval = Lval;
		this.Rval = Rval;
		this.val = val;
		this.all = all;
	}
}