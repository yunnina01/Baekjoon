import java.io.*;
import java.util.*;

public class Main {
    static PriorityQueue<Node> pq;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int w = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            Circle[] circles = new Circle[n];
            pq = new PriorityQueue<>();
            for(int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                circles[i] = new Circle(x, y, r);

                double d1 = circles[i].x - circles[i].r;
                double d2 = w - (circles[i].x + circles[i].r);
                pq.offer(new Node(i, n, d1 < 0 ? 0 : d1));
                pq.offer(new Node(i, n + 1, d2 < 0 ? 0 : d2));

                for(int j = 0; j < i; j++) {
                    double d = getDist(circles[i], circles[j]);
                    pq.offer(new Node(i, j, d));
                }
            }
            pq.offer(new Node(n, n + 1, w));

            parent = new int[n + 2];
            Arrays.fill(parent, -1);
            sb.append(String.format("%.6f\n", kruskal(n)));
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static double getDist(Circle c1, Circle c2) {
        double dx = c1.x - c2.x;
        double dy = c1.y - c2.y;
        double dist = Math.sqrt(dx * dx + dy * dy) - c1.r - c2.r;
        return dist < 0 ? 0 : dist;
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y)
            return true;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        } else {
            parent[y] += parent[x];
            parent[x] = y;
        }
        return false;
    }

    static int find(int x){
        if(parent[x] < 0)
            return x;
        return parent[x] = find(parent[x]);
    }

    static double kruskal(int n) {
        double res = 0;
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(union(now.u, now.v) || find(n) != find(n + 1))
                continue;

            if(now.w == 0)
                res = 0;
            else
                res = now.w / 2;
            break;
        }
        return res;
    }
}

class Node implements Comparable<Node> {
    int u, v;
    double w;

    Node(int u, int v, double w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(this.w, o.w);
    }
}

class Circle {
    int x, y, r;

    Circle(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }
}