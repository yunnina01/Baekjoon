import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 40001;
	static List<Point> points;
	static ArrayDeque<Point> stack;
	static Point origin = new Point(INF, INF);

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		points = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if(y < origin.y || (y == origin.y && x < origin.x))
				origin = new Point(x, y);
			points.add(new Point(x, y));
		}

		points.sort(new Comparator<Point>() {	
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

		getConvexHull(N);
		System.out.println(getArea());
	}
	
	static long ccw(Point p1, Point p2, Point p3) {
		return (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p3.x * p2.y + p2.x * p1.y + p1.x * p3.y);
	}
	
	static long getDist(Point p1, Point p2) {
		long dx = p2.x - p1.x;
		long dy = p2.y - p1.y;
		return dx * dx + dy * dy;
	}
	
	static void getConvexHull(int N) {
		stack = new ArrayDeque<>();
		stack.push(new Point(points.get(0).x, points.get(0).y));
		stack.push(new Point(points.get(1).x, points.get(1).y));

		for(int i = 2; i < N; i++) {
			Point next = new Point(points.get(i).x, points.get(i).y);
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
	}
	
	static String getArea(){
		Point first = stack.pop();
		Point remain = first;
		double res = 0;
		
		while(!stack.isEmpty()) {
			Point p = stack.pop();
			res += Math.sqrt((double)getDist(first, p));
			first = p;
		}
		res += Math.sqrt((double)getDist(first, remain));
		return String.format("%.2f", res);
	}
}

class Point {
	long x, y;

	Point(long x, long y) {
		this.x = x;
		this.y = y;
	}
}