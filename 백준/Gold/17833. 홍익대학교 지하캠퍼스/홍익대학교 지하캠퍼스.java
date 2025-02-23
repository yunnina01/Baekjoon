import java.io.*;
import java.util.*;

public class Main {
    static final long INF = 10_000_000_000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        List<List<Node>> buildings = new ArrayList<>();
        for(int i = 0; i <= N; i++)
            buildings.add(new ArrayList<>());

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            int E1 = Integer.parseInt(st.nextToken());
            int E2 = Integer.parseInt(st.nextToken());

            for(int j = N - H; j >=0 ; j--) {
                buildings.get(E1 + j).add(new Node(E2 + j, T));
                buildings.get(E2 + j).add(new Node(E1 + j, T));
            }
        }

        long[] dist = new long[N + 1];
        boolean[] visit = new boolean[N + 1];
        Arrays.fill(dist, INF);
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Long.compare(dist[o1], dist[o2]);
            }
        });

        dist[R] = 0;
        pq.offer(R);
        while(!pq.isEmpty()) {
            int now = pq.poll();
            if(now == D)
                break;
            if(visit[now])
                continue;
            visit[now] = true;
            for(int i = buildings.get(now).size() - 1; i >= 0; i--) {
                Node next = buildings.get(now).get(i);
                if(!visit[next.v] && next.t + dist[now] < dist[next.v]) {
                    dist[next.v] = next.t + dist[now];
                    pq.add(next.v);
                }
            }
        }
        System.out.println(dist[D] == INF ? -1 : dist[D]);
    }
}

class Node {
    int v, t;

    Node(int v, int t) {
        this.v = v;
        this.t = t;
    }
}