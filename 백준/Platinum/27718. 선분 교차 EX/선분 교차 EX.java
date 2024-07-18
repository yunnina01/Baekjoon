import java.io.*;
import java.util.*;

public class Main {
    static int[][] graph;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        Line[] lines = new Line[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long x1 = Long.parseLong(st.nextToken());
            long y1 = Long.parseLong(st.nextToken());
            long x2 = Long.parseLong(st.nextToken());
            long y2 = Long.parseLong(st.nextToken());
            lines[i] = new Line(x1, y1, x2, y2);
        }
        
        graph = new int[N][N];
        for(int i = 0; i < N; i++) {
            graph[i][i] = 3;
            for(int j = i + 1; j < N; j++)
                graph[i][j] = graph[j][i] = isCrossing(lines[i].p1, lines[i].p2, lines[j].p1, lines[j].p2);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++)
                sb.append(graph[i][j]);
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static int outProduct(Point p1, Point p2, Point p3) {
        long op = (p3.x - p1.x) * (p1.y - p2.y) - (p1.x - p2.x) * (p3.y - p1.y);
        return op > 0 ? 1 : (op < 0 ? -1 : 0);
    }

    static int isCrossing(Point p1, Point p2, Point p3, Point p4) {
        int op1 = outProduct(p1, p2, p3) * outProduct(p1, p2, p4);
        int op2 = outProduct(p3, p4, p1) * outProduct(p3, p4, p2);
        boolean crossing;
        if(op1 == 0 && op2 == 0)
            crossing = p1.compareTo(p4) <= 0 && p2.compareTo(p3) >= 0;
        else
            crossing = op1 <= 0 && op2 <= 0;
        if(!crossing)
            return 0;

        if((p1.x - p2.x) * (p3.y - p4.y) - (p1.y - p2.y) * (p3.x - p4.x) != 0 || (p1.compareTo(p4) == 0 && p3.compareTo(p1) <= 0) || (p2.compareTo(p3) == 0 && p1.compareTo(p3) <= 0))
            return op1 == 0 || op2 == 0 ? 1 : 2;
        return 3;
    }
}

class Point implements Comparable<Point> {
    long x, y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        return x == o.x ? Long.compare(y, o.y) : Long.compare(x, o.x);
    }
}

class Line {
    Point p1, p2;

    Line(long x1, long y1, long x2, long y2) {
        p1 = new Point(x1,y1);
        p2 = new Point(x2,y2);
        if(p1.compareTo(p2) > 0) {
            Point tmp = p1;
            p1 = p2;
            p2 = tmp;
        }
    }
}