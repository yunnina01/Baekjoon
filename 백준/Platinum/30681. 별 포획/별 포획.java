import java.io.*;
import java.util.*;

public class Main {
	static final double POS_INF = Double.POSITIVE_INFINITY;
	static final long INF = Integer.MAX_VALUE;
	static final Point BASE = new Point(INF, INF);
	static Point base;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());

		List<Point> points = new ArrayList<>();
		base = BASE;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            Point point = new Point(x, y);
			points.add(point);
			if(point.x < base.x || (point.x == base.x && point.y < base.y))
				base = point;
		}
		System.out.print(rotatingCalipers(convexHull(points)));
	}
	
	static double rotatingCalipers(List<Point> hull) {
		if(hull.size() < 3)
			return Math.sqrt(dist(hull.get(0), hull.get(hull.size() - 1)));

		double len = Math.sqrt(dist(hull.get(0), hull.get(1)));
		double min = POS_INF;
		for(int i = 0, j = 1; i < hull.size(); i++) {
			int ni = (i + 1) % hull.size();
			len -= Math.sqrt(dist(hull.get(i), hull.get(ni)));
			for(;;) {
				int nj = (j + 1) % hull.size();
				if(ccw(hull.get(i), hull.get(ni), hull.get(j), hull.get(nj)) >= 0) {
					len += Math.sqrt(dist(hull.get(j), hull.get(nj)));
					j = nj;
				} else
					break;
			}
			min = Math.min(min, len);
		}
		return min;
	}
	
	static final long dist(Point p1, Point p2) {
        long dx = p2.x - p1.x;
        long dy = p2.y - p1.y;
		return dx * dx + dy * dy;
	}
	
	static final long ccw(Point p1, Point p2, Point p3, Point p4) {
		return (p2.x - p1.x) * (p4.y - p3.y) - (p4.x - p3.x) * (p2.y - p1.y);
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
		if(ccw(points.get(points.size() - 1), points.get(0), points.get(1)) == 0) {
			stack.add(points.get(0));
			stack.add(points.get(points.size() - 1));
			return new ArrayList<>(stack);
		}
		for(int i = 0; i < points.size(); i++) {
			while(stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.get(stack.size() - 1), points.get(i)) <= 0)
				stack.pop();
			stack.add(points.get(i));
		}
		return new ArrayList<>(stack);
	}

	static final long ccw(Point p1, Point p2, Point p3) {
		return (p2.x - p1.x) * (p3.y - p2.y) - (p3.x - p2.x) * (p2.y - p1.y);
	}
}
	
class Point {
    long x, y;
    
    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}