import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static Node[] graph;
    static int[] prev, weight;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new Node[N + 1];
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            graph[A] = new Node(B, C, graph[A]);
            graph[B] = new Node(A, C, graph[B]);
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        prev = new int[N + 1];
        weight = new int[N + 1];
        Arrays.fill(prev, INF);
        Arrays.fill(weight, INF);
        dijkstra(E);
        int goDist = weight[S];

        Arrays.fill(weight, INF);
        int trace = S;
        while(trace != 0) {
            weight[trace] = -1;
            trace = prev[trace];
        }

        Arrays.fill(prev, INF);
        weight[S] = weight[E] = INF;
        dijkstra(S);

        System.out.println(goDist + weight[E]);
    }

    static void dijkstra(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(s, 0, null));
        prev[s] = 0;
        weight[s] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if(now.weight < weight[now.idx])
                continue;

            for(Node node = graph[now.idx]; node != null; node = node.next) {
                int nextWeight = now.weight + node.weight;
                if(nextWeight < weight[node.idx] || (nextWeight == weight[node.idx] &&now.idx < prev[node.idx])) {
                    prev[node.idx] = now.idx;
                    weight[node.idx] = nextWeight;
                    pq.offer(new Node(node.idx, nextWeight, null));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int idx, weight;
    Node next;

    Node(int idx, int weight, Node next) {
        this.idx = idx;
        this.weight = weight;
        this.next = next;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}