import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()) + 4;
        int M = Integer.parseInt(st.nextToken());

        Point[] points = new Point[N];
        for(int i = 0; i < N - 4; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            points[i] = new Point(x, y);
        }
        points[N - 4] = new Point(0, M);
        points[N - 3] = new Point(0, -M);
        points[N - 2] = new Point(M, 0);
        points[N - 1] = new Point(-M, 0);

        Arrays.sort(points, (o1, o2) -> o1.y == o2.y ? Long.compare(o1.x, o2.x) : Long.compare(o1.y, o2.y));
        Arrays.sort(points, 1, N, (p1, p2) -> {
            long product = outProduct(points[0], p1, p2);
            if(product == 0)
                return Long.compare(Math.abs(p1.x + p1.y - points[0].x - points[0].y), Math.abs(p2.x + p2.y - points[0].x - points[0].y));
            else
                return Long.compare(0, product);
        });

        Deque<Point> shell = new ArrayDeque<>();
        int online = N - 2;
        shell.offerLast(points[0]);
        shell.offerLast(points[1]);
        for(int i = 2; i < N; i++) {
            while(shell.size() >= 2) {
                Point secnond = shell.pollLast();
                Point first = shell.peekLast();
                long out = outProduct(first, secnond, points[i]);
                if(out > 0) {
                    shell.offerLast(secnond);
                    break;
                } else if(out == 0)
                    online--;
            }
            shell.offerLast(points[i]);
        }

        while(shell.size() >= 2) {
            Point second = shell.pollLast();
            Point first = shell.peekLast();
            if(outProduct(first, second, points[0]) > 0) {
                shell.offerLast(second);
                break;
            }
        }

        if(online == 0)
            shell.offerLast(points[N - 1]);

        List<Point> cal = new ArrayList<>(shell);
        double res = 0;
        for(int i = 0; i < shell.size() - 1; i++) {
            for(int j = i + 2; j < shell.size(); j++) {
                long lmax = 0, rmax = 0, tmp;
                for(int k = i + 1; k < j; k++) {
                    tmp = Math.abs(outProduct(cal.get(i), cal.get(k), cal.get(j)));
                    if(tmp < lmax)
                        break;
                    lmax = tmp;
                }
                for(int k = j + 1; k < i + cal.size(); k++) {
                    tmp = Math.abs(outProduct(cal.get(i), cal.get(k % cal.size()), cal.get(j)));
                    if(tmp < rmax)
                        break;
                    rmax = tmp;
                }
                res = Math.max(res, ((lmax + rmax) / 4.0) * ((lmax + rmax) / 4.0));
            }
        }
        System.out.println(res);
    }
    
    static long outProduct(Point p1, Point p2, Point p3) {
        return (p3.x - p1.x) * (p1.y - p2.y) - (p1.x - p2.x) * (p3.y - p1.y);
    }
}

class Point {
    long x, y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}