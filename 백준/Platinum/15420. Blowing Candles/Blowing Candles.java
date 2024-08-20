import java.io.*;
import java.util.*;

public class Main {
	static final double INF = Double.MAX_VALUE;
	static List<Point> points;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());

		points = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			points.add(new Point(x, y));
		}
		System.out.println(rotatingCalipers(getConvexHull()));
	}

	static double rotatingCalipers(List<Point> polygon) {
		int size = polygon.size();
		int idx2 = 1;
		double ret = INF;

		for(int idx1 = 0; idx1 < size; idx1++) {
			double maxDist = 0;
			while(true) {
				int nextIdx1 = (idx1 + 1) % size;
				int nextIdx2 = (idx2 + 1) % size;

				double y1 = polygon.get(nextIdx1).y - polygon.get(idx1).y;
				double x1 = polygon.get(nextIdx1).x - polygon.get(idx1).x;
				double y2 = polygon.get(nextIdx2).y - polygon.get(idx2).y;
				double x2 = polygon.get(nextIdx2).x - polygon.get(idx2).x;

				Point A = new Point(0, 0);
				Point B = new Point(y1, x1);
				Point C = new Point(y2, x2);

				if (ccw(A, B, C) > 0)
					idx2 = (idx2 + 1) % size;
				else {
					Point p1 = polygon.get(idx1);
					Point p2 = polygon.get(nextIdx1);
					Point p3 = polygon.get(idx2);

					if(p1.x == p2.x) {
						double dist = Math.abs(p3.x - p1.x);
						maxDist = Math.max(maxDist, dist);
					} else {
						double m = (p2.y - p1.y) / (p2.x - p1.x);
						double b = (-m * p1.x) + p1.y;

						double numer = Math.abs(m * p3.x - p3.y + b);
						double denom = Math.sqrt(m * m + 1 * 1);
						double dist = numer / denom;
						maxDist = Math.max(maxDist, dist);
					}
					break;
				}
			}
			ret = Math.min(ret, maxDist);
		}
		return ret;
	}

	static List<Point> getConvexHull() {
		points.sort(null);

		Stack<Point> lower = new Stack<>();
		for(int i = 0; i < points.size(); i++) {
			while(lower.size() > 1	&& (ccw(lower.get(lower.size() - 2), lower.get(lower.size() - 1), points.get(i)) >= 0))
				lower.pop();
			lower.add(points.get(i));
		}
		lower.pop();

		Stack<Point> upper = new Stack<>();
		for(int i = points.size() - 1; i >= 0; i--) {
			while(upper.size() > 1	&& (ccw(upper.get(upper.size() - 2), upper.get(upper.size() - 1), points.get(i)) >= 0))
				upper.pop();
			upper.add(points.get(i));
		}
		upper.pop();

		List<Point> ret = new ArrayList<>();
		ret.addAll(lower);
		ret.addAll(upper);
		return ret;
	}
	
	static double ccw(Point a, Point b, Point c) {
		double cp = a.x * b.y + b.x * c.y + c.x * a.y;
		cp -= a.y * b.x + b.y * c.x + c.y * a.x;
		return cp;
	}
}

class Point implements Comparable<Point> {
	double x, y;

	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Point o) {
		if(this.y == o.y)
			return Double.compare(this.x, o.x);
		return -Double.compare(this.y, o.y);
	}
}