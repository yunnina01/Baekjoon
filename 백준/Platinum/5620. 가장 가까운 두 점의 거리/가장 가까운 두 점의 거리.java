import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        List<Point> points = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points.add(new Point(x, y));
        }
        points.sort((o1, o2) -> o1.x - o2.x);

        TreeSet<Point> set = new TreeSet<>((o1, o2) -> o1.y == o2.y ? o1.x - o2.x : o1.y - o2.y);
        set.add(points.get(0));
        set.add(points.get(1));
        int start = 0;
        int res = getDist(points.get(0), points.get(1));

        for(int i = 2; i < n; i++) {
            Point now = points.get(i);
            while(start < i) {
                Point p = points.get(start);
                int dx = now.x - p.x;
                if(dx * dx > res) {
                    set.remove(p);
                    start++;
                } else
                    break;
            }

            int dist = (int)Math.sqrt(res) + 1;
            Point from = new Point(-100000, now.y - dist);
            Point to = new Point(100000, now.y + dist);

            for(Point p : set.subSet(from, to))
                res = Math.min(res, getDist(now, p));
            set.add(now);
        }
        System.out.println(res);
    }

    static int getDist(Point a, Point b) {
        int dx = b.x - a.x;
        int dy = b.y - a.y;
        return dx * dx + dy * dy;
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}