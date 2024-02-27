import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    static List<Node>[] roads;
    static int[][] dist;
    static int res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()) - 1;
        int D = Integer.parseInt(st.nextToken()) - 1;

        roads = new ArrayList[N];
        dist = new int[N][N];
        for(int i = 0; i < N; i++) {
            roads[i] = new ArrayList<>();
            Arrays.fill(dist[i], INF);
        }
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());

            roads[a].add(new Node(b, w, 0));
            roads[b].add(new Node(a, w, 0));
        }

        dijkstra(S);

        res = INF;
        for(int d : dist[D]) {
            if(d == INF)
                continue;
            if(res > d)
                res = d;
        }
        sb.append(res + "\n");

        while(K-- > 0) {
            int p = Integer.parseInt(br.readLine());
            res = INF;

            for(int i = 0; i < N; i++) {
                int cost = i * p + dist[D][i];
                if(res > cost)
                    res = cost;
                dist[D][i] = cost;
            }
            sb.append(res + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 0));
        dist[start][0] = 0;

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(isFind(now))
                continue;
            if(now.hop >= dist.length - 1 || dist[now.v][now.hop] < now.w)
                continue;

            for(Node next : roads[now.v]) {
                if(dist[next.v][now.hop + 1] <= dist[now.v][now.hop] + next.w)
                    continue;
                dist[next.v][now.hop + 1] = dist[now.v][now.hop] + next.w;
                pq.offer(new Node(next.v, dist[next.v][now.hop + 1], now.hop + 1));
            }
        }
    }

    static boolean isFind(Node now) {
        int idx = 0;
        while(idx < now.hop) {
            if(dist[now.v][idx++] < now.w)
                return true;
        }
        return false;
    }
}

class Node implements Comparable<Node> {
    int v, w, hop;

    Node(int v, int w, int hop) {
        this.v = v;
        this.w = w;
        this.hop = hop;
    }

    @Override
    public int compareTo(Node o) {
        return this.w - o.w;
    }
}