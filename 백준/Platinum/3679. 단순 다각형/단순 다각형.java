import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        while(c-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            Point[] points = new Point[n];
            for(int i = 0; i < n; i++) {
                Long x = Long.parseLong(st.nextToken());
                Long y = Long.parseLong(st.nextToken());
                points[i] = new Point(x, y, i);
            }
            
            Arrays.sort(points, (o1, o2) -> o1.y == o2.y ? Long.compare(o1.x, o2.x) : Long.compare(o1.y, o2.y));
            Arrays.sort(points, 1, n, (p1, p2) -> {
                long product = outProduct(points[0], p1, p2);
                return product == 0 ? Long.compare(Math.abs(p1.x + p1.y - points[0].x - points[0].y), 
                    Math.abs(p2.x + p2.y - points[0].x - points[0].y)) : Long.compare(0, product);
            });

            int last = n - 1;
            for(int i = n - 1; i > 0; i--, last--) {
                if(outProduct(points[0], points[i], points[i - 1]) != 0)
                    break;
            }
            for(int i = 0; i < last; i++)
                bw.write(Long.toString(points[i].order) + " ");
            for(int i = last; i < n; i++)
                bw.write(Long.toString(points[n - 1 + last - i].order) + " ");
            bw.write("\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    static long outProduct(Point p1, Point p2, Point p3) {
        return (p3.x - p1.x) * (p1.y - p2.y) - (p1.x - p2.x) * (p3.y - p1.y);
    }
}

class Point {
    long x, y, order;

    Point(long x, long y, long order) {
        this.x = x;
        this.y = y;
        this.order = order;
    }
}