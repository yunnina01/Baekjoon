import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static List<List<Integer>> edges;
    static int[][] capacity, flow;
    static int[] level, work, check;
    static int source, sink;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
 
        int N = Integer.parseInt(br.readLine());
        source = 0;
        sink = N + 1;

        edges = new ArrayList<>();
        for(int i = 0; i <= sink; i++)
            edges.add(new ArrayList<>());
        capacity = new int[sink + 1][sink + 1];
        flow = new int[sink + 1][sink + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if(n == 1) {
                edges.get(source).add(i);
                edges.get(i).add(source);
                capacity[source][i] = INF;
            } else if(n == 2) {
                edges.get(i).add(sink);
                edges.get(sink).add(i);
                capacity[i][sink] = INF;
            }
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                capacity[i][j] = Integer.parseInt(st.nextToken());
                if(i != j)
                    edges.get(i).add(j);
            }
        }

        int res = 0;
        while(BFS()) {
            work = new int[sink + 1];
            while(true) {
                int minFlow = DFS(source, INF);
                if(minFlow == 0)
                    break;
                res += minFlow;
            }
        }
        sb.append(res + "\n");
        
        decom();

        List<Integer> aTeam = new ArrayList<>();
        List<Integer> bTeam = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if(check[i] == 1)
                aTeam.add(i);
            else
                bTeam.add(i);
        }

        for(int a : aTeam)
            sb.append(a + " ");
        sb.append("\n");
        for(int b : bTeam)
            sb.append(b + " ");
        sb.append("\n");

        bw.write(sb.toString());
        br.close();
        bw.flush();
        bw.close();
    }

    static boolean BFS() {
        Queue<Integer> queue = new LinkedList<>();
        level = new int[sink + 1];
        Arrays.fill(level, -1);
        level[source] = 0;
        queue.offer(source);

        while(!queue.isEmpty()) {
            int now = queue.poll();
            for(int next : edges.get(now)) {
                if(level[next] == -1 && capacity[now][next] - flow[now][next] > 0) {
                    level[next] = level[now] + 1;
                    queue.offer(next);
                }
            }
        }
        return level[sink] != -1;
    }

    static int DFS(int now, int tot) {
        if(now == sink)
            return tot;
        for(; work[now] < edges.get(now).size(); work[now]++) {
            int next = edges.get(now).get(work[now]);
            if(level[next] == level[now] + 1 && capacity[now][next] - flow[now][next] > 0) {
                int minFlow = DFS(next, Math.min(tot, capacity[now][next] - flow[now][next]));
                if(minFlow > 0) {
                    flow[now][next] += minFlow;
                    flow[next][now] -= minFlow;
                    return minFlow;
                }
            }
        }
        return 0;
    }

    static void decom() {
        Queue<Integer> queue = new LinkedList<>();
        check = new int[sink + 1];
        check[source] = 1;
        queue.offer(source);

        while(!queue.isEmpty()) {
            int now = queue.poll();
            for(int next : edges.get(now)) {
                if(check[next] == 0 && capacity[now][next] - flow[now][next] > 0) {
                    check[next] = 1;
                    queue.offer(next);
                }
            }
        }
    }
}