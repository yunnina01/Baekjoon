import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());

		List<Point> points = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
            long X = Long.parseLong(st.nextToken());
            long Y = Long.parseLong(st.nextToken());
			points.add(new Point(X, Y));
		}
		System.out.print(rotatingCalipers(points));
	}
	
	static long rotatingCalipers(List<Point> hull) {
		long cnt = 0;
		for(int i = 0, j = 1; i < hull.size(); i++) {
			int ni = (i + 1) % hull.size();
			for(;;) {
				int nj = (j + 1) % hull.size();
				if(ccw(hull.get(i), hull.get(ni), hull.get(j), hull.get(nj)) >= 0)
					j = nj;
				else
					break;
			}

			int pj = (j + hull.size() - 1) % hull.size();
            long dist;
			if(pj >= ni)
				dist = pj - ni;
			else
				dist = hull.size() - (ni - pj);
			cnt += (dist + 1) * dist >> 1;
		}
		return (long)hull.size() * (hull.size() - 1) * (hull.size() - 2) / 6 - cnt;
	}
	
	static final long ccw(Point p1, Point p2, Point p3, Point p4) {
		return (p2.x - p1.x) * (p4.y - p3.y) - (p2.y - p1.y) * (p4.x - p3.x);
	}
}

class Point {
    long x, y;
    
    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}