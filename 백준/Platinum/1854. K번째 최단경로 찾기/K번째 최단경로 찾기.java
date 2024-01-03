import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static List<Node>[] edge;
    static PriorityQueue<Integer>[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        edge = new ArrayList[n + 1];
        dist = new PriorityQueue[n + 1];
        for(int i = 0; i <= n; i++) {
            edge[i] = new ArrayList<>();
            dist[i] = new PriorityQueue<>(Collections.reverseOrder());
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edge[a].add(new Node(b, c));
        }

        dijkstra(1);
        for(int i = 1; i <= n; i++) {
            if(dist[i].size() == k)
                bw.write(dist[i].peek() + "\n");
            else
                bw.write(-1 + "\n");
        }
        br.close();
        bw.flush();
        bw.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start].offer(0);

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            for(Node next : edge[now.v]) {
                if(dist[next.v].size() < k) {
                    dist[next.v].offer(now.w + next.w);
                    pq.offer(new Node(next.v, now.w + next.w));
                } else if(dist[next.v].peek() > now.w + next.w) {
                    dist[next.v].poll();
                    dist[next.v].offer(now.w + next.w);
                    pq.offer(new Node(next.v, now.w + next.w));
                }
            }
        }
    }
}

class Node implements Comparable<Node> {
    int v, w;

    Node(int v, int w) {
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Node o) {
        return this.w - o.w;
    }
}