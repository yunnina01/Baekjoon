import java.io.*;
import java.util.*;

public class Main {
	static final long INF = Long.MAX_VALUE;
	static List<Point> points;
	static List<Point> res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());

		points = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			char c = st.nextToken().charAt(0);
			if(c == 'Y')
				points.add(new Point(x, y));
		}

		getConvexHull();

		sb.append(res.size() + "\n");
		for (Point p : res)
			sb.append(p.x + " " + p.y + "\n");
		bw.write(sb.toString());
		bw.flush();
	}

	static void getConvexHull() {
		points.sort((o1, o2) -> o1.x == o2.x ? Long.compare(o1.y, o2.y) : Long.compare(o1.x, o2.x));
		
		Stack<Point> lower = new Stack<>();
		for(int i = 0; i < points.size(); i++) {
			while(lower.size() > 1	&& (ccw(lower.get(lower.size() - 2), lower.get(lower.size() - 1), points.get(i)) < 0))
				lower.pop();
			lower.add(points.get(i));
		}
		lower.pop();

		Stack<Point> upper = new Stack<>();
		for(int i = points.size() - 1; i >= 0; i--) {
			while(upper.size() > 1	&& (ccw(upper.get(upper.size() - 2), upper.get(upper.size() - 1), points.get(i)) < 0))
				upper.pop();
			upper.add(points.get(i));
		}
		upper.pop();

		res = new ArrayList<>();
		res.addAll(lower);
		res.addAll(upper);
	}

	static long ccw(Point a, Point b, Point c) {
		long cp = a.x * b.y + b.x * c.y + c.x * a.y;
		cp -= a.y * b.x + b.y * c.x + c.y * a.x;
		return cp;
	}
}

class Point {
	long x, y;

	Point(long x, long y) {
		this.x = x;
		this.y = y;
	}
}