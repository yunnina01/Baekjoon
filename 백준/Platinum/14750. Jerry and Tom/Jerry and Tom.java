import java.io.*;
import java.util.*;

public class Main {
    static List<Point> wall;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        wall = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            wall.add(new Point(x, y));
        }
        wall.add(new Point(wall.get(0).x, wall.get(0).y));

        List<Point> hole = new ArrayList<>();
        for(int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            hole.add(new Point(x, y));
        }

        List<Point> mouse = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            mouse.add(new Point(x, y));
        }

        int source = 0;
        int sink = h + m + 1;

        List<List<Integer>> edges = new ArrayList<>();
        for(int i = 0; i <= sink; i++)
            edges.add(new ArrayList<>());
        int[][] capacity = new int[sink + 1][sink + 1];
        int[][] flow = new int[sink + 1][sink + 1];

        for(int i = 1; i <= m; i++) {
            edges.get(source).add(i);
            edges.get(i).add(source);
            capacity[source][i] = 1;
        }
        for(int i = m + 1; i < sink; i++) {
            edges.get(i).add(sink);
            edges.get(sink).add(i);
            capacity[i][sink] = k;
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < h; j++) {
                if(!isCrossed(mouse.get(i), hole.get(j))) {
                    edges.get(i + 1).add(j + m + 1);
                    edges.get(j + m + 1).add(i + 1);
                    capacity[i + 1][j + m + 1] = 1;
                }
            }
        }

        int maxFlow = 0;
        while(true) {
            Queue<Integer> queue = new LinkedList<>();
            int[] parent = new int[sink + 1];
            Arrays.fill(parent, -1);
            parent[source] = source;
            queue.offer(source);

            while(!queue.isEmpty() && parent[sink] == -1) {
                int now = queue.poll();
                for(int next : edges.get(now)) {
                    if(parent[next] == -1 && capacity[now][next] - flow[now][next] > 0) {
                        queue.offer(next);
                        parent[next] = now;
                    }
                }
            }
            if(parent[sink] == -1)
                break;
            
            int minFlow = Integer.MAX_VALUE;
            for(int i = sink; i != source; i = parent[i])
                minFlow = Math.min(minFlow, capacity[parent[i]][i] - flow[parent[i]][i]);
            for(int i = sink; i != source; i = parent[i]) {
                flow[parent[i]][i] += minFlow;
                flow[i][parent[i]] -= minFlow;
            }
            maxFlow += minFlow;
        }
        System.out.println(maxFlow == m ? "Possible" : "Impossible");
    }

    static long ccw(Point a, Point b, Point c) {
        long cp = (long)(b.x - a.x) * (c.y - a.y) - (long)(b.y - a.y) * (c.x - a.x);
        return cp == 0 ? 0 : (cp > 0 ? 1 : -1);
    }

    static boolean isLine(Point p1, Point p2, Point p3, Point p4) {
        int a, b, c, d;
        if(p1.x == p2.x) {
            a = Math.min(p1.y, p2.y);
            b = Math.max(p1.y, p2.y);
            c = Math.min(p3.y, p4.y);
            d = Math.max(p3.y, p4.y);
        } else {
            a = Math.min(p1.x, p2.x);
            b = Math.max(p1.x, p2.x);
            c = Math.min(p3.x, p4.x);
            d = Math.max(p3.x, p4.x);
        }
        return a <= d && c <= b;
    }

    static boolean isCrossed(Point m, Point h) {
        int cnt = 0;
        for(int i = 0; i < wall.size() - 1; i++) {
            Point p1 = wall.get(i);
            Point p2 = wall.get(i + 1);
            if(h.x == p1.x && h.y == p1.y)
                continue;
            
            long cp1 = ccw(m, h, p1);
            long cp2 = ccw(m, h, p2);
            long cp3 = ccw(p1, p2, m);
            long cp4 = ccw(p1, p2, h);
            if(cp1 * cp2 <= 0 && cp3 * cp4 < 0 || cp1 * cp2 < 0 && cp3 * cp4 <= 0)
                cnt++;
            else if(cp1 * cp2 == 0 && cp3 * cp4 == 0 && isLine(p1, p2, m, h))
                cnt++;
            
            if(cnt > 1)
                return true;
        }
        return false;
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}