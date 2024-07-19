import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
        
		st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int res = 0;

        List<Point> outerPoint = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            outerPoint.add(new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));

		List<Point> innerPoint = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++)
            innerPoint.add(new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken())));
		
        Collections.sort(outerPoint);
        Collections.sort(innerPoint);

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++) {
            double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
            Point target = new Point(x, y);

            int outerPos = Collections.binarySearch(outerPoint, target);
			int innerPos = Collections.binarySearch(innerPoint, target);
            if(outerPos < 0)
				outerPos = -outerPos;
            if(innerPos < 0)
				innerPos = -innerPos;
            if(!inner(outerPoint, target, outerPos) || inner(innerPoint, target, innerPos))
				res++;
		}
        System.out.println(res == 0 ? "YES" : res);
    }

    static int outProduct(Point p1, Point p2, Point p3){
        double product = (p3.x - p1.x) * (p1.y - p2.y) - (p1.x - p2.x) * (p3.y - p1.y);
        return Double.compare(product, 0.0);
    }

    static boolean inner(List<Point> hull, Point target, int idx){
        for(int i = 0; i < 3; i++) {
            if(outProduct(hull.get((idx + hull.size() - 2 + i) % hull.size()), hull.get((idx + hull.size() - 1 + i) % hull.size()), target) <= 0)
				return false;
        }
        return true;
    }
}

class Point implements Comparable<Point>{
	double x, y, theta;
	
	Point(double x, double y) {
		this.x = x;
		this.y = y;
		this.theta = Math.atan2(y, x);
	}

	@Override
	public int compareTo(Point o) {
		return Double.compare(this.theta, o.theta);
	}
}