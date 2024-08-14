import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 1_000_001;
	static Point[] points;
	static Point origin;
	static boolean[] visit;
	static int[] res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		points = new Point[n];
		visit = new boolean[n];

		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			points[i] = new Point(i, x, y);
		}
		
		res = new int[n];
		convexHull(n);
		
		for(int i = 0; i < n; i++)
			sb.append(res[i] + " ");
		System.out.println(sb.toString());
	}

	static void convexHull(int n) {
		int cnt = 1;
		while(true) {
			List<Point> pIdx = new ArrayList<>();
			ArrayDeque<Point> stack = new ArrayDeque<>();
			origin = new Point(-1, INF, INF);
			
			for(int i = 0; i < n; i++) {
				if(visit[points[i].idx])
					continue;
				
				if(points[i].y < origin.y || (points[i].y == origin.y && points[i].x < origin.x))
					origin = new Point(points[i].idx, points[i].x, points[i].y);
				pIdx.add(points[i]);
			}
			if(pIdx.size() <= 2)
				break;
			
			pIdx.sort(new Comparator<Point>() {
				@Override
				public int compare(Point p1, Point p2) {
					long c = ccw(origin, p1, p2);
					if(c > 0)
						return -1;
					else if(c < 0)
						return 1;
					else {
						long a = getDist(origin, p1);
						long b = getDist(origin, p2);
						return Long.compare(a, b);
					}
				}
			});
			
			stack.push(pIdx.get(0));
			stack.push(pIdx.get(1));
			
			for(int i = 2; i < pIdx.size(); i++) {
				Point next = pIdx.get(i);
				while(stack.size() >= 2) {
					Point second = stack.pop();
					Point first = stack.peek();
					if(ccw(first, second, next) > 0) {
						stack.push(second);
						break;
					}
				}
				stack.push(next);
			}
			if(stack.size() <= 2)
				break;
			
			while(!stack.isEmpty()) {
				Point now = stack.pop();
				res[now.idx] = cnt; 
				visit[now.idx] = true;
			}
			cnt++;
		}
	}
	
	static long ccw(Point p1, Point p2, Point p3) {
		return (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p3.x * p2.y + p2.x * p1.y + p1.x * p3.y);
	}

	static long getDist(Point a, Point b) {
		long dx = b.x - a.x;
		long dy = b.y - a.y;
		return dx * dx + dy * dy;
	}
}

class Point {
	int idx;
	long x, y;

	Point(int idx, long x, long y) {
		this.idx = idx;
		this.x = x;
		this.y = y;
	}
}