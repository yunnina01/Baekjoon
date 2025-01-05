import java.io.*;
import java.util.*;

public class Main {
    private static List<List<Integer>> tunnelList;
    private static int[] tunnelIdx;
    private static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        tunnelList = new ArrayList<>();
        Point[] mice = new Point[N];
        Point[] tunnel = new Point[M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            mice[i] = new Point(x, y);
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            tunnel[i] = new Point(x, y);
        }

        int spend = V * S;
        spend *= spend;
        for(int i = 0; i < N; i++) {
            tunnelList.add(new ArrayList<>());
            for(int j = 0; j < M; j++) {
                double distance = getDistance(mice[i], tunnel[j]);
                if(distance > spend)
                    continue;
                tunnelList.get(i).add(j);
            }
        }

        tunnelIdx = new int[101];
        visit = new boolean[101];
        Arrays.fill(tunnelIdx, -1);
        int res = 0;

        for(int start = 0; start < N; start++) {
            Arrays.fill(visit, false);
            if(DFS(start))
                res++;
        }
        System.out.println(N - res);
    }

    static double getDistance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return dx * dx + dy * dy;
    }

    static boolean DFS(int x) {
        for(int next: tunnelList.get(x)) {
            if(visit[next])
                continue;
            visit[next] = true;
            if(tunnelIdx[next] == -1 || DFS(tunnelIdx[next])) {
                tunnelIdx[next] = x;
                return true;
            }
        }
        return false;
    }
}

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}