import java.io.*;
import java.util.*;

public class Main {
	static final long INF = Integer.MAX_VALUE;
	static final Point BASE = new Point(INF, INF);
	static Point base;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		long[][] A = new long[N][2];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i][0] = Long.parseLong(st.nextToken());
			A[i][1] = Long.parseLong(st.nextToken());
		}

        List<Point> points = new ArrayList<>();
		base = BASE;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			for(long[] xy : A) {
				Point point = new Point(x + xy[0], y + xy[1]);
				points.add(point);
				if(point.x < base.x || (point.x == base.x && point.y < base.y))
					base = point;
			}
		}

		List<Point> hull = convexHull(points);
		sb.append(hull.size() + "\n");
		for(Point hullPoint : hull)
			sb.append(hullPoint.x + " " + hullPoint.y + "\n");
        bw.write(sb.toString());
        bw.flush();
	}
	
	private static final List<Point> convexHull(List<Point> points) {
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
		return (p2.x - p1.x) * (p3.y - p2.y) - (p2.y - p1.y) * (p3.x - p2.x);
	}
	
	static final long dist(Point p1, Point p2) {
        long dx = p2.x - p1.x;
        long dy = p2.y - p1.y;
        return dx * dx + dy * dy;
	}
}
	
class Point {
    long x, y;
    
    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}