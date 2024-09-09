import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    static final double PI = Math.PI;
    static List<Point> wall;
    static ArrayDeque<Point> stack;
    static Point origin = new Point(INF, INF);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        wall = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if(y < origin.y || (y == origin.y && x < origin.x))
                origin = new Point(x, y);
            wall.add(new Point(x, y));
        }

        wall.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                long c = ccw(origin, p1, p2);
                if(c > 0)
                   return -1;
                else if(c < 0)
                    return 1;
                else {
                    long a = getDist(origin, p1);
                    long b = getDist(origin, p2);
                    return Long.compare(a, b);
                }
            }
        });

        convexHull(N);
        System.out.println(getRound(L));
    }    

    static long ccw(Point p1, Point p2, Point p3) {
        return (p1.x * p2.y + p2.x * p3.y + p3.x * p1.y) - (p3.x * p2.y + p2.x * p1.y + p1.x * p3.y);
    }

    static long getDist(Point p1, Point p2) {
        return (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y);
    }

    static void convexHull(int N) { 
        stack = new ArrayDeque<>();
        stack.push(new Point(wall.get(0).x, wall.get(0).y));
        stack.push(new Point(wall.get(1).x, wall.get(1).y));

        for(int i = 2; i < N; i++) {
            Point next = new Point(wall.get(i).x, wall.get(i).y);
            while(stack.size() >= 2) {
                Point second = stack.pop();
                Point first = stack.peek();
                if(ccw(first, second, next) > 0) {
                    stack.push(second);
                    break;
                }
            }
            stack.push(next);
        }
    }

    static double getRound(int limit) {
        int size = stack.size();
        double round = (PI * size - (PI * (size - 2))) * limit;
        Point prev = stack.pop();
        Point start = prev;

        while(!stack.isEmpty()) {
            Point now = stack.pop();
            round += Math.sqrt(getDist(prev, now));
            prev = now;
        }
        round += Math.sqrt(getDist(start, prev));
        return round;
    }
}

class Point {
    long x, y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}