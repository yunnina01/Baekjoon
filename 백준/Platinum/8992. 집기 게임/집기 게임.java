import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    static List<List<Integer>> edges;
    static int[][] cost, capacity, flow;
    static int source, sink, size, half;
    static int maxFlow, maxGrade;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            half = n + m;
            size = (half << 1) + 2;

            ArrayList<Node> horizontal = new ArrayList<>();
            for(int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                st.nextToken();
                int w = Integer.parseInt(st.nextToken());

                horizontal.add(new Node(i, Math.min(x1, x2), Math.max(x1, x2), y, w));
            }

            ArrayList<Node> vertical = new ArrayList<>();
            for(int i = 1; i <= m; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                st.nextToken();
                int y2 = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());

                vertical.add(new Node(n + i, Math.min(y1, y2), Math.max(y1, y2), x, w));
            }

            graphModeling(horizontal, vertical);

            maxFlow = 0;
            maxGrade = 0;
            MCMF();
            sb.append(maxFlow + " " + maxGrade + "\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static void graphModeling(ArrayList<Node> horizontal, ArrayList<Node> vertical) {
        source = 0;
        sink = size - 1;

        edges = new ArrayList<>();
        for(int i = 0; i < size; i++)
            edges.add(new ArrayList<>());

        capacity = new int[size][size];
        flow = new int[size][size];
        cost = new int[size][size];
        for(int i = 1; i <= half; i++) {
            linker(source, i, 0, 1);
            linker(i + half, sink, 0, 1);
        }

        for(Node h: horizontal) {
            for(Node v: vertical) {
                if(outOfRange(h, v) || outOfRange(v, h))
                    continue;
                linker(h.idx, v.idx + half, -h.cost * v.cost, 1);
            }
        }
    }

    static void linker(int from, int to, int c, int cap) {
        edges.get(from).add(to);
        edges.get(to).add(from);

        capacity[from][to] = cap;
        cost[from][to] = c;
        cost[to][from] = -c;
    }

    static boolean outOfRange(Node o1, Node o2) {
        return o1.from > o2.fix || o1.to < o2.fix;
    }

    static void MCMF() {
        int[] parent = new int[size];
        int[] dist = new int[size];

        while(true) {
            Arrays.fill(parent, -1);
            Arrays.fill(dist, INF);

            Queue<Integer> queue = new LinkedList<>();
            boolean[] visit = new boolean[size];
            queue.offer(source);
            dist[source] = 0;
            visit[source] = true;

            while(!queue.isEmpty()) {
                int now = queue.poll();
                visit[now] = false;

                for(int next: edges.get(now)) {
                    if(dist[next] <= dist[now] + cost[now][next])
                        continue;
                    if(capacity[now][next] <= flow[now][next])
                        continue;
                    dist[next] = dist[now] + cost[now][next];
                    parent[next] = now;

                    if(visit[next])
                        continue;
                    queue.offer(next);
                    visit[next] = true;
                }
            }
            if(parent[sink] == -1)
                break;

            int minFlow = INF;
            for(int i = sink; i != source; i = parent[i])
                minFlow = Math.min(minFlow, capacity[parent[i]][i] - flow[parent[i]][i]);
            for(int i = sink; i != source; i = parent[i]) {
                maxGrade -= cost[parent[i]][i];
                flow[parent[i]][i] += minFlow;
                flow[i][parent[i]] -= minFlow;
            }
            maxFlow += minFlow;
        }
    }
}

class Node {
    int idx, from, to, fix, cost;

    Node(int idx, int from, int to, int fix, int cost) {
        this.idx = idx;
        this.from = from;
        this.to = to;
        this.fix = fix;
        this.cost = cost;
    }
}