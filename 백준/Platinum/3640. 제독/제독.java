import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    static List<List<Integer>> connection;
    static int[][] capacity, flow, cost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String input;
        while((input = br.readLine()) != null) {
            st = new StringTokenizer(input);
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int size = v * 2 + 2;
            int src = 0;
            int sink = size - 1;

            connection = new ArrayList<>();
            for(int i = 0; i < size; i++)
                connection.add(new ArrayList<>());
            capacity = new int[size][size];
            flow = new int[size][size];
            cost = new int[size][size];

            while(e-- > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                linker(a + v, b, c, 1);
            }

            linker(1, v + 1, 0, 2);
            linker(v, v + v, 0, 2);

            for (int i = 2; i < v; i++)
                linker(i, i + v, 0, 1);

            linker(src, 1, 0, 2);
            linker(v, sink, 0, 2);

            sb.append(MCMF(src, sink, size) + "\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static void linker(int from, int to, int c, int cap) {
        connection.get(from).add(to);
        connection.get(to).add(from);

        capacity[from][to] = cap;

        cost[from][to] = c;
        cost[to][from] = -c;
    }

    static int MCMF(int S, int T, int N) {
        int[] prev = new int[N];
        int[] dist = new int[N];
        int res = 0;

        while(true) {
            Queue<Integer> queue = new LinkedList<>();
            boolean[] visit = new boolean[N];
            Arrays.fill(prev, -1);
            Arrays.fill(dist, INF);
            queue.offer(S);
            dist[S] = 0;
            visit[S] = true;

            while(!queue.isEmpty()) {
                int now = queue.poll();
                visit[now] = false;

                for(int next : connection.get(now)) {
                    if(dist[next] <= dist[now] + cost[now][next])
                        continue;
                    if(capacity[now][next] <= flow[now][next])
                        continue;
                    dist[next] = dist[now] + cost[now][next];
                    prev[next] = now;

                    if(!visit[next]) {
                        queue.offer(next);
                        visit[next] = true;
                    }
                }
            }
            if(prev[T] == -1)
                break;

            int maxFlow = INF;
            for(int i = T; i != S; i = prev[i])
                maxFlow = Math.min(maxFlow, capacity[prev[i]][i] - flow[prev[i]][i]);
            for(int i = T; i != S; i = prev[i]){
                res += maxFlow * cost[prev[i]][i];
                flow[prev[i]][i] += maxFlow;
                flow[i][prev[i]] -= maxFlow;
            }
        }
        return res;
    }
}