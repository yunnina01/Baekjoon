import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static List<List<Edge>> edges;

    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken()) - 1;
        int B = Integer.parseInt(st.nextToken()) - 1;
        int C = Integer.parseInt(st.nextToken()) - 1;

        edges = new ArrayList<>();
        for(int i = 0; i < N; i++)
            edges.add(new ArrayList<>());

        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken()) - 1;
            int E = Integer.parseInt(st.nextToken()) - 1;
            int L = Integer.parseInt(st.nextToken());

            edges.get(D).add(new Edge(E, L));
            edges.get(E).add(new Edge(D, L));
        }

        int[] distA = new int[N];
        int[] distB = new int[N];
        int[] distC = new int[N];
        dijkstra(distA, A);
        dijkstra(distB, B);
        dijkstra(distC, C);

        int res = -1;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            distA[i] =  Math.min(Math.min(i == B ? INF : distB[i], i == C ? INF : distC[i]), i == A ? INF : distA[i]);
            if(distA[i] > max) {
                res = i;
                max = distA[i];
            }
        }
        System.out.println(res + 1);
    }

    static void dijkstra(int[] dist, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(dist, INF);
        pq.offer(new Edge(start, 0));
        dist[start] = 0;
        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            if(now.cost > dist[now.node])
                continue;
            for(Edge next : edges.get(now.node)) {
                if(now.cost + next.cost < dist[next.node]) {
                    dist[next.node] = now.cost + next.cost;
                    pq.offer(new Edge(next.node, dist[next.node]));
                }
            }
        }
    }
}

class Edge implements Comparable<Edge> {
    int node, cost;

    Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}