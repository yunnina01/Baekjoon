import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            pointList.add(new Point(x, y));
        }
        pointList.sort((o1, o2) -> o1.y != o2.y ? o2.y - o1.y : o1.x - o2.x);

        Point start = pointList.get(0);
        for(int i = 1; i < pointList.size(); i++) {
            Point p = pointList.get(i);
            p.dx = p.x - start.x;
            p.dy = p.y - start.y;
        }
        pointList.sort((o1, o2) -> {
            if(o1.dx * o2.dy != o1.dy * o2.dx)
                return o1.dy * o2.dx - o1.dx * o2.dy;
            return getDist(pointList.get(0), o1) - getDist(pointList.get(0), o2);
        });
        
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(1);
        int next = 2;
        while(next < n) {
            while(stack.size() >= 2) {
                int second = stack.pop();
                int first = stack.peek();
                if(outProduct(pointList.get(first), pointList.get(second), pointList.get(next)) > 0) {
                    stack.push(second);
                    break;
                }
            }
            stack.push(next++);
        }

        List<Point> cows = new ArrayList<>();
        while(!stack.isEmpty())
            cows.add(pointList.get(stack.pop()));

        int res = 0;
        start = cows.get(0);
        for(int i = 1; i < cows.size() - 1; i++) {
            Point p1 = cows.get(i);
            Point p2 = cows.get(i + 1);
            res += Math.abs((p1.x - start.x) * (p2.y - start.y) - (p2.x - start.x) * (p1.y - start.y));
        }
        System.out.println(res / 100);
    }

    static int getDist(Point a, Point b) {
        int dx = b.x - a.x;
        int dy = b.y - a.y;
        return dx * dx + dy * dy;
    }

    static int outProduct(Point a, Point b, Point c) {
        int product = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        return Integer.signum(product);
    }
}

class Point {
    int x, y, dx, dy;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}