import java.io.*;
import java.util.*;

public class Main {
	static final long INF = Long.MAX_VALUE;
	static Point base;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		
		int m = Integer.parseInt(br.readLine());

		while(m-- > 0){
            int n = Integer.parseInt(br.readLine());

            List<Point> points = new ArrayList<>();
            base = new Point(INF, INF);
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                long x = Long.parseLong(st.nextToken());
                long y = Long.parseLong(st.nextToken());

                Point point = new Point(x, y);
                points.add(point);
                if(point.y < base.y || (point.y == base.y && point.x < base.x))
                    base = point;
            }

            List<Point> hull = convexHull(points);
            double length = 0;
            hull.add(hull.get(0));
            for(int i = 1; i < hull.size(); i++)
                length += Math.sqrt(dist(hull.get(i - 1), hull.get(i)));
			sb.append(String.format("%.2f\n", length));
		}
		bw.write(sb.toString());
        bw.flush();
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
    
    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}