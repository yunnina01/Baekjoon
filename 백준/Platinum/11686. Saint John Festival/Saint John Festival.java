import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int L = Integer.parseInt(br.readLine());

        Point[] points = new Point[L];
        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            points[i] = new Point(x, y);
        }

        Arrays.sort(points, (o1, o2) -> o1.y != o2.y ? Long.compare(o1.y, o2.y) : Long.compare(o1.x, o2.x));
        Arrays.sort(points, 1, L, (o1, o2) -> {
            long product = outProduct(points[0], o1, o2);
            if(product == 0)
                return Long.compare(Math.abs(o1.x + o1.y - points[0].x - points[0].y), Math.abs(o2.x + o2.y - points[0].x - points[0].y));
            return Long.compare(0, product);
        });

        ArrayDeque<Point> stack = new ArrayDeque<>();
        stack.offerLast(points[0]);
        stack.offerLast(points[1]);
        int online = L - 2;
        for(int i = 2; i < L; i++) {
            Point next = points[i];
            while(stack.size() >= 2) {
                Point second = stack.pollLast();
                Point first = stack.peekLast();
                long outproduct = outProduct(first, second, next);
                if(outproduct > 0) {
                    stack.offerLast(second);
                    break;
                } else if(outproduct == 0)
                    online--;
            }
            stack.offerLast(next);
        }

        while(stack.size() >= 2) {
            Point second = stack.pollLast();
            Point first = stack.peekLast();
            if(outProduct(first, second, points[0]) > 0) {
                stack.offerLast(second);
                break;
            }
        }
        if(online == 0)
            stack.offerLast(points[L - 1]);

        int S = Integer.parseInt(br.readLine());
        List<Point> polygon = new ArrayList<>(stack);
        int res = 0;
        for(int i = 0; i < S; i++){
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y =  Long.parseLong(st.nextToken());
            Point target = new Point(x, y);
            if(inner(polygon, target))
                res++;
        }
        System.out.println(res);
    }

    static int outProduct(Point p1, Point p2, Point p3){
        double product = (p3.x - p1.x) * (p1.y - p2.y) - (p1.x - p2.x) * (p3.y - p1.y);
        return Double.compare(product, 0.0);
    }

    static boolean inner(List<Point> hull, Point target) {
        if(outProduct(hull.get(0), hull.get(1), target) < 0)
            return false;
        if(outProduct(hull.get(0), hull.get(hull.size() - 1), target) > 0)
            return false;
        int left = 1, right = hull.size() - 1;
        while(left + 1 < right) {
            int mid = (left + right) >> 1;
            if(outProduct(hull.get(0), hull.get(mid), target) > 0)
                left = mid;
            else
                right = mid;
        }
        return outProduct(hull.get(left), target, hull.get(right)) <= 0;
    }
}

class Point {
    long x, y;
    
    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}