import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    static long[][] dp;
    static long[] sum;
    static int[][] cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        List<List<Node>> forward = new ArrayList<>();
        List<List<Node>> backward = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            forward.add(new ArrayList<>());
            backward.add(new ArrayList<>());
        }

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            forward.get(u).add(new Node(v, l));
            backward.get(v).add(new Node(u, l));
        }

        int[][] dist = new int[3][n + 1];
        Arrays.fill(dist[1], INF);
        Arrays.fill(dist[2], INF);
        dist[1][b + 1] = dist[2][b + 1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.w - o2.w;
            }
        });
        pq.offer(new Node(b + 1, 0));
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(now.w > dist[1][now.v])
                continue;
            for(Node next : forward.get(now.v)) {
                if(now.w + next.w >= dist[1][next.v])
                    continue;
                dist[1][next.v] = now.w + next.w;
                pq.offer(new Node(next.v, dist[1][next.v] = now.w + next.w));
            }
        }
        pq.clear();

        pq.offer(new Node(b + 1, 0));
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(now.w > dist[2][now.v])
                continue;
            for(Node next : backward.get(now.v)) {
                if(now.w + next.w >= dist[2][next.v])
                    continue;
                dist[2][next.v] = now.w + next.w;
                pq.offer(new Node(next.v, dist[2][next.v] = now.w + next.w));
            }
        }

        for(int i = 1; i <= b; i++)
            dist[0][i] = dist[1][i] + dist[2][i];
        Arrays.sort(dist[0], 1, b + 1);

        dp = new long[s + 1][b + 1];
        cost = new int[s + 1][b + 1];
        sum = new long[b + 1];
        for(int i = 1; i <= b; i++) {
            dp[1][i] = (sum[i] += sum[i - 1] + dist[0][i]) * (i - 1);
            cost[1][i] = 1;
        }

        for(int i = 2; i <= s; i++)
            recur(i, i, b, 0, b);
        System.out.println(dp[s][b]);
    }

    static void recur(int idx, int start, int end, int left, int right){
        if(start > end)
            return;
        int mid = (start + end) >> 1;
        dp[idx][mid] = cost[idx][mid] = -1;
        for(int i = left; i <= Math.min(mid - 1, right); i++) {
            long tmp = dp[idx - 1][i] + (sum[mid] - sum[i]) * (mid - i - 1);
            if(dp[idx][mid] != -1 && dp[idx][mid] <= tmp)
                continue;
            dp[idx][mid] = tmp;
            cost[idx][mid] = i;
        }
        recur(idx, start, mid - 1, left, cost[idx][mid]);
        recur(idx, mid + 1, end, cost[idx][mid], right);
    }
}

class Node {
    int v, w;

    Node(int v, int w) {
        this.v = v;
        this.w = w;
    }
}