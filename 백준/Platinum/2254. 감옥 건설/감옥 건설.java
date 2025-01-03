import java.io.*;
import java.util.*;

public class Main {
	static final long INF = Long.MAX_VALUE;
	static final Point BASE = new Point(INF, INF);
	static Point base;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
				
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());

		Point prison = new Point(x, y);
		base = BASE;

		List<Point> points = new ArrayList<>(N + 1);
		points.add(prison);
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			Point point = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
			points.add(point);
			if(point.y < base.y || (point.y == base.y && point.x < base.x))
				base = point;
		}

		for(int i = 0;; i++) {
			List<Point> hull = convexHull(points);
			for(Point hullPoint : hull) {
				if(hullPoint == prison) {
					System.out.print(i);
					return;
				}
				hullPoint.deleted = true;
			}

			List<Point> temp = new ArrayList<>();
			base = BASE;
			for(Point notHull : points) {
				if(notHull.deleted)
					continue;

				temp.add(notHull);
				if(notHull.y < base.y || (notHull.y == base.y && notHull.x < base.x))
					base = notHull;
			}
			points = temp;
		}
	}
	
	static List<Point> convexHull(List<Point> points) {
		points.sort(new Comparator<Point>() {
			@Override
			public int compare(Point p1, Point p2) {
				long val = ccw(base, p1, p2);
				if(val == 0)
					return Long.compare(dist(base, p1), dist(base, p2));
				return val > 0 ? -1 : 1;
			}
		});
		if(points.size() < 3)
			return new ArrayList<>(points);

		Stack<Point> stack = new Stack<>();
		for(int i = 0; i < points.size(); i++) {
			while(stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0)
				stack.pop();
			stack.add(points.get(i));
		}
		return new ArrayList<>(stack);
	}
	
	static long ccw(Point p1, Point p2, Point p3) {
		return (p2.x - p1.x) * (p3.y - p2.y) - (p3.x - p2.x) * (p2.y - p1.y);
	}
	
	static long dist(Point p1, Point p2) {
		long dx = p2.x - p1.x;
		long dy = p2.y - p1.y;
		return dx * dx + dy * dy;
	}
}
	
class Point {
	long x, y;
	boolean deleted;
	
	Point(long x, long y) {
		this.x = x;
		this.y = y;
	}
}