import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 1_000_000_000;
    static List<List<Integer>> connection;
    static int[][] capacity, flow, cost;
    static int value, res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int size = N + M + 2;
        int src = 0;
        int sink = size - 1;

        connection = new ArrayList<>();
        for(int i = 0; i < size; i++)
            connection.add(new ArrayList<>());
        capacity = new int[size][size];
        flow = new int[size][size];
        cost = new int[size][size];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int A = Integer.parseInt(st.nextToken());
            capacity[i][sink] = A;
            connection.get(i).add(sink);
            connection.get(sink).add(i);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = N + 1; i < sink; i++) {
            int B = Integer.parseInt(st.nextToken());
            capacity[src][i] = B;
            connection.get(src).add(i);
            connection.get(i).add(src);
        }

        for(int i = N + 1; i < sink; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int C = Integer.parseInt(st.nextToken());
                capacity[i][j] = C;
                connection.get(i).add(j);
                connection.get(j).add(i);
            }
        }

        for(int i = N + 1; i < sink; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int D = Integer.parseInt(st.nextToken());
                cost[i][j] = D;
                cost[j][i] = -cost[i][j];
            }
        }

        MCMF(src, sink, size);

        System.out.println(value + "\n" + res);
    }

    static void MCMF(int S, int T, int N) {
        int[] prev = new int[N];
        int[] dist = new int[N];

        while(true) {
            Queue<Integer> queue = new LinkedList<>();
            boolean[] visit = new boolean[N];
            Arrays.fill(prev, -1);
            Arrays.fill(dist, INF);
            queue.offer(S);
            dist[S] = 0;
            visit[S] = true;

            while(!queue.isEmpty()){
                int now = queue.poll();
                visit[now] = false;

                for(int next : connection.get(now)){
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
            for(int i = T; i != S; i = prev[i]) {
                res += maxFlow * cost[prev[i]][i];
                flow[prev[i]][i] += maxFlow;
                flow[i][prev[i]] -= maxFlow;
            }
            value += maxFlow;
        }
    }
}