import java.io.*;
import java.util.*;

public class Main {
	static final double POS_INF = Double.POSITIVE_INFINITY;
	static final long INF = Integer.MAX_VALUE;
	static Point base;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		String str;
		
		while((str = br.readLine()) != null) {
            int N = Integer.parseInt(str);

            List<Point> points = new ArrayList<>(N);
            base = new Point(INF, INF);
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                double X = Double.parseDouble(st.nextToken());
                double Y = Double.parseDouble(st.nextToken());

                Point point = new Point(X, Y);
                points.add(point);
                if(point.x < base.x || (point.x == base.x && point.y < base.y))
                    base = point;
            }
            
            List<Point> hull = convexHull(points);
            if(hull.size() == 2)
                sb.append(2 * Math.sqrt(dist(hull.get(0), hull.get(1))) + "\n");
            else
                sb.append(rotatingCalipers(hull) + "\n");
		}
		bw.write(sb.toString());
        bw.flush();
	}
	
	static List<Point> convexHull(List<Point> points) {
		points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                double val = ccw(base, p1, p2);
                if(val == 0)
                    return Double.compare(dist(base, p1), dist(base, p2));
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
	
	static double ccw(Point p1, Point p2, Point p3) {
		return (p2.x - p1.x) * (p3.y - p2.y) - (p2.y - p1.y) * (p3.x - p2.x);
	}
	
	static double dist(Point p1, Point p2) {
		return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
	}
	
	static double rotatingCalipers(List<Point> hull) {
        boolean reached = true;
		double min = POS_INF;
		for(int i = 0, j = 1, l = 1, r = 1; i < hull.size(); i++) {
			int ni = (i + 1) % hull.size();
			for(;;) {
				int nj = (j + 1) % hull.size();
				if(ccw(hull.get(i), hull.get(ni), hull.get(j), hull.get(nj)) > 0) {
					j = nj;
					if(j == l)
						reached = true;
				} else
					break;
			}
			Point foot = getFoot(hull.get(i), hull.get(ni), hull.get(j));
			if(reached) {
				l = j;
				reached = false;
			}
			for(;;) {
				int nl = (l + 1) % hull.size();
				if(ccw(foot, hull.get(j), hull.get(l), hull.get(nl)) > 0)
					l = nl;
				else
					break;
			}
			for(;;) {
				int nr = (r + 1) % hull.size();
				if(ccw(foot, hull.get(j), hull.get(r), hull.get(nr)) < 0)
					r = nr;
				else
					break;
			}
			min = Math.min(min, 2 * (dist(hull.get(i), hull.get(ni), hull.get(j)) + dist(foot, hull.get(j), hull.get(l)) + dist(foot, hull.get(j), hull.get(r))));
		}
		return min;
	}
	
	static double ccw(Point p1, Point p2, Point p3, Point p4) {
		return (p2.x - p1.x) * (p4.y - p3.y) - (p2.y - p1.y) * (p4.x - p3.x);
	}
	
	static double dist(Point p1, Point p2, Point p3) {
		double a = p2.y - p1.y;
		double b = p1.x - p2.x;
		double c = p2.x * p1.y - p1.x * p2.y;
		return Math.abs(a * p3.x + b * p3.y + c) / Math.sqrt(a * a + b * b);
	}
	
	static Point getFoot(Point p1, Point p2, Point p3) {
		double a = p2.y - p1.y;
		double b = p1.x - p2.x;
		double c = p2.x * p1.y - p1.x * p2.y;
		double x = (b * b * p3.x - a * b * p3.y - a * c) / (a * a + b * b);
		double y = (a * a * p3.y - a * b * p3.x - b * c) / (a * a + b * b);
		return new Point(x, y);
	}
}
	
class Point{
    double x, y;
    
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}