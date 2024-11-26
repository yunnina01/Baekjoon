import java.io.*;
import java.util.*;

public class Main {
	static final double INF = Double.MAX_VALUE;
    static List<Point> points;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		
        points = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			points.add(new Point(x, y));
		}

		System.out.println(rotatingCalipers(getConvexHull()));
	}

	static List<Point> getConvexHull() {
		Collections.sort(points);

		Stack<Point> lower = new Stack<>();
		for(int i = 0; i < points.size(); i++) {
			while(lower.size() > 1	&& (ccw(lower.get(lower.size() - 2), lower.get(lower.size() - 1), points.get(i)) >= 0))
				lower.pop();
			lower.add(points.get(i));
        }
		lower.pop();

		Stack<Point> upper = new Stack<>();
		for(int i = points.size() - 1; i >= 0; i--) {
			while(upper.size() > 1 && (ccw(upper.get(upper.size() - 2), upper.get(upper.size() - 1), points.get(i)) >= 0))
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

	static double rotatingCalipers(List<Point> polygon) {
		int size = polygon.size();
		int idx2 = 1;
		double maxDist = -INF;

		for(int idx1 = 0; idx1 < size; idx1++) {
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

				if(ccw(A, B, C) > 0)
					idx2 = (idx2 + 1) % size;
                else {
					double dist = polygon.get(idx1).getDistance(polygon.get(idx2));
					maxDist = Math.max(maxDist, dist);
					break;
				}
			}
		}
		return maxDist;
	}
}

class Point implements Comparable<Point> {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point p) {
        if(this.x == p.x)
            return Double.compare(this.y, p.y);
        return Double.compare(this.x, p.x);
    }

    public double getDistance(Point p) {
        double dx = this.x - p.x;
        double dy = this.y - p.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}