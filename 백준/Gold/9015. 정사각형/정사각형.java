import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            Point[] points = new Point[N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                points[i] = new Point(x, y);
            }

            Arrays.sort(points);
            bw.write(make(points) + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    static int make (Point[] p) {
        int max = 0;
        for(int i = 0; i < p.length; i++) {
            for(int j = i + 1; j < p.length; j++) {
                int d = getDistance(p[i], p[j]);
                if(d <= max)
                    continue;

                int x = p[j].x - p[i].x, y = p[j].y - p[i].y;
                if(!binarySearch(p, new Point(p[i].x - y, p[i].y + x)) || !binarySearch(p, new Point(p[j].x - y, p[j].y + x)))
                    continue;
                max = Math.max(max, getDistance(p[i], p[j]));
            }
        }
        return max;
    }

    static boolean binarySearch(Point[] Points, Point p) {
        int start = 0, end = Points.length - 1;
        while(start <= end) {
            int mid = (start + end) >> 1;
            if(p.x == Points[mid].x && p.y == Points[mid].y)
                return true;

            if(p.x == Points[mid].x) {
                if(Points[mid].y < p.y)
                    start = mid + 1;
                else
                    end = mid - 1;
            } else {
                if(Points[mid].x < p.x)
                    start = mid + 1;
                else
                    end = mid - 1;
            }
        }
        return false;
    }

    static int getDistance(Point p1, Point p2) {
        int x = Math.abs(p1.x - p2.x);
        int y = Math.abs(p1.y - p2.y);
        return x * x + y * y;
    }
}

class Point implements Comparable<Point>{
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        if(this.x == o.x)
            return this.y - o.y;
        else
            return this.x - o.x;
    }
}