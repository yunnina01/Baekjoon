import java.io.*;
import java.util.*;

public class Main {
    static final double INF = 50_000_000;
    static ArrayList<Node>[] graph;
    static double[][] cost, burned;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for(int i = 0; i < N; i++)
            graph[i] = new ArrayList<>();
        burned = new double[3][M];

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken()) - 1;
            int E = Integer.parseInt(st.nextToken()) - 1;
            double L = Double.parseDouble(st.nextToken());

            graph[S].add(new Node(E, L));
            graph[E].add(new Node(S, L));
            burned[0][i] = S;
            burned[1][i] = E;
            burned[2][i] = L;
        }

        cost = new double[N][N];
        for(int i = 0; i < N; i++){
            Arrays.fill(cost[i], INF);
            dijkstra(i);
        }

        double res = INF;
        for(int i = 0; i < N; i++) {
            double sum = 0;
            for(int j = 0; j < M; j++) {
                int now = (int)burned[0][j];
                int next = (int)burned[1][j];
                double w = burned[2][j];
                sum = Math.max(cost[i][now] + cost[i][next] + w, sum);
            }
            res = Math.min(res, sum / 2);
        }
        bw.write(res + "\n");
        br.close();
        bw.flush();
        bw.close();
    }

    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        cost[start][start] = 0;

        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(cost[start][now.v] < now.w)
                continue;
            for(Node next : graph[now.v]) {
                if(cost[start][next.v] > cost[start][now.v] + next.w) {
                    cost[start][next.v] = cost[start][now.v] + next.w;
                    pq.offer(new Node(next.v, cost[start][next.v]));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int v;
    double w;

    Node(int v, double w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Node o) {
        return this.w < o.w ? -1 : 1;
    }
}