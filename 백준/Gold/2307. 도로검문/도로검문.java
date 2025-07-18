import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static List<List<Edge>> edges;
    static List<Edge> shortEdges;
    static int[] seq, dist;
    static Edge checkPoint;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        for(int i = 0; i < N; i++)
            edges.add(new ArrayList<>());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            edges.get(a).add(new Edge(b, t));
            edges.get(b).add(new Edge(a, t));
        }

        dist = new int[N];
        seq = new int[N];
        Arrays.fill(seq, -1);
        dijkstra();

        int min = dist[N - 1];
        if(min == INF) {
            System.out.println(-1);
            return;
        }

        shortEdges = new ArrayList<>();
        findShortCut(N - 1);

        int res = Integer.MIN_VALUE;
        for(int i = 0; i < shortEdges.size(); i++) {
            checkPoint = shortEdges.get(i);
            cdijkstra();
            res = Math.max(res, dist[N - 1]);
        }
        System.out.println(res == INF ? -1 : res - min);
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(dist, INF);
        pq.offer(new Edge(0, 0));
        dist[0] = 0;
        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            if(now.cost > dist[now.end])
                continue;
            for(Edge next : edges.get(now.end)) {
                if(dist[next.end] > now.cost + next.cost) {
                    dist[next.end] = now.cost + next.cost;
                    seq[next.end] = now.end;
                    pq.offer(new Edge(next.end, now.cost + next.cost));
                }
            }
        }
    }

    static void findShortCut(int last) {
        shortEdges.add(new Edge(last, seq[last]));
        if(seq[last] == 0)
            return;
        findShortCut(seq[last]);
    }

    static void cdijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(dist, INF);
        pq.offer(new Edge(0, 0));
        dist[0] = 0;
        while(!pq.isEmpty()){
            Edge now = pq.poll();
            if(now.cost > dist[now.end])
                continue;
            for(Edge next : edges.get(now.end)) {
                if((next.end == checkPoint.end || next.end == checkPoint.cost) && (now.end == checkPoint.end || now.end == checkPoint.cost))
                    continue;
                if(dist[next.end] > now.cost + next.cost){
                    dist[next.end] = now.cost + next.cost;
                    seq[next.end] = now.end;
                    pq.offer(new Edge(next.end, now.cost + next.cost));
                }
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    int end, cost;

    Edge(int end, int cost) {
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}