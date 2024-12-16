import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> graph;
    static int[] homeIdx;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String input;
        while((input = br.readLine()) != null) {
            st = new StringTokenizer(input);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            Point[] gopher = new Point[n];
            Point[] shelter = new Point[m];
            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                double x = Double.parseDouble(st.nextToken());
                double y = Double.parseDouble(st.nextToken());
                gopher[i] = new Point(x, y);
            }
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                double x = Double.parseDouble(st.nextToken());
                double y = Double.parseDouble(st.nextToken());
                shelter[i] = new Point(x, y);
            }

            int spend = s * v;
            spend *= spend;
            for(int i = 0; i < n; i++) {
                graph.add(new ArrayList<>());
                for(int j = 0; j < m; j++) {
                    double dist = getDist(gopher[i], shelter[j]);
                    if(dist > spend)
                        continue;
                    graph.get(i).add(j);
                }
            }

            homeIdx = new int[101];
            visit = new boolean[101];
            Arrays.fill(homeIdx, -1);
            int res = 0;
            for(int i = 0; i < n; i++) {
                Arrays.fill(visit, false);
                if(DFS(i))
                    res++;
            }
            sb.append(n - res + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static double getDist(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        return dx * dx + dy * dy;
    }

    static boolean DFS(int x) {
        for(int next : graph.get(x)) {
            if(visit[next])
                continue;
            visit[next] = true;
            if(homeIdx[next] == -1 || DFS(homeIdx[next])) {
                homeIdx[next] = x;
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